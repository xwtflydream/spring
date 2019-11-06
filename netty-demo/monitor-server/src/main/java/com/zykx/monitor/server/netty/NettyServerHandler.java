package com.zykx.monitor.server.netty;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zykx.monitor.server.entity.ServerInfo;
import com.zykx.monitor.server.entity.ServerMonitorLog;
import com.zykx.monitor.server.mybatis.ServerInfoMapper;
import com.zykx.monitor.server.mybatis.ServerMonitorLogMapper;
import com.zykx.monitor.server.utils.RemoteShellExecutor;
import com.zykx.monitor.server.utils.ShellExecResult;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

/**
 * 服务端处理器
 */
@Component
public class NettyServerHandler extends SimpleChannelInboundHandler<String> implements ApplicationContextAware {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private static ApplicationContext applicationContext;
    private ServerInfoMapper serverInfoMapper;
    private ServerMonitorLogMapper serverMonitorLogMapper;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        NettyServerHandler.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    // 连接超过N次未收到client的PING消息，则关闭该通道，等待client重连
    private static final int MAX_UN_REC_PING_TIMES = 3;
    private int failTimes = 0;
    // 收到一个client的PING消息的个数
    private int allPings = 0;

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.ALL_IDLE) {
                System.out.println("10秒内没有收到"+ctx.channel().remoteAddress()+" PING");
                // 失败计数器次数大于等于3次的时候，关闭链接，等待client重连
                if (failTimes >= MAX_UN_REC_PING_TIMES) {
                    System.out.println("30秒内没有收到"+ctx.channel().remoteAddress()+"PING ,即将关闭连接！");

                    // 插入服务状态监控数据
                    String ip = ((InetSocketAddress)ctx.channel().remoteAddress()).getHostString();
                    insertServerStatusLog(ip, null);

                    //关闭通道
                    ctx.close();
                }else {
                    // 失败计数器+1
                    failTimes++;
                }
            }else {
                super.userEventTriggered(ctx, evt);
            }
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        if (StringUtils.isNotBlank(msg)) {
            String ip = ((InetSocketAddress)ctx.channel().remoteAddress()).getHostString();
            logger.info("客户端[{}]第[{}]个PING", ip, ++allPings);
            logger.info("客户端[{}]第[{}]个PING", ctx.channel().remoteAddress(), ++allPings);
            ctx.writeAndFlush("PONG");

            // 插入服务状态监控数据
            insertServerStatusLog(ip, msg);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        initBean();
        super.channelActive(ctx);
        logger.info("[{}]客户端已连接", ctx.channel().remoteAddress());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        logger.info("[{}]客户端已断开", ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        if(cause instanceof IOException) {
            System.out.println("client "+ctx.channel().remoteAddress()+"强制关闭连接");
            logger.info("客户端[{}]强制关闭连接", ctx.channel().remoteAddress());
        }
        ctx.close();
    }

    /**
     * 初始化服务相关的持久化类
     */
    private void initBean() {
        serverInfoMapper = getApplicationContext().getBean(ServerInfoMapper.class);
        serverMonitorLogMapper = getApplicationContext().getBean(ServerMonitorLogMapper.class);
    }

    /**
     * 插入服务状态监控数据
     * @param ip
     * @param appId
     */
    private void insertServerStatusLog(String ip, String appId) {
        // 执行服务状态检测
        QueryWrapper<ServerInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("ip", ip);
        if (StringUtils.isNotBlank(appId)) {
            wrapper.eq("app_id", appId);
        }
        List<ServerInfo> list = serverInfoMapper.selectList(wrapper);
        if (list != null && !list.isEmpty()) {
            RemoteShellExecutor remoteShellExecutor;
            ServerMonitorLog monitorLog;
            ShellExecResult result;
            String statusMsg = "";
            for (ServerInfo info : list) {
                monitorLog = new ServerMonitorLog();
                monitorLog.setServerId(info.getServerId());
                if(StringUtils.isNotBlank(appId)) {
                    remoteShellExecutor = new RemoteShellExecutor(info.getIp(), info.getLoginUser(), info.getLoginPassword());
                    try {
                        result = remoteShellExecutor.execCommand(info.getStatusCommand());
                        if(0 == result.getCode()) {
                            statusMsg = result.getMessage();
                            if(statusMsg.contains("running")) {
                                monitorLog.setStatus(1); // 1：正常
                            }else if(statusMsg.contains("stopped")) {
                                monitorLog.setStatus(2); // 2：停止
                            }
                        }
                    }catch (Exception e) {
                        logger.info(e.getMessage());
                        monitorLog.setStatus(3); // 3：登录失败(SSH)
                    }
                }else {
                    monitorLog.setStatus(4); // 4：连接失败（监控客户端程序）
                }

                //保存监控信息
                serverMonitorLogMapper.insert(monitorLog);
            }
        }
    }
}
