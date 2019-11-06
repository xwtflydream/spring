package com.zykx.monitor.server.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zykx.monitor.server.entity.ServerInfo;
import com.zykx.monitor.server.mybatis.ServerInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class Receiver {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ServerInfoMapper serverInfoMapper;

    @RabbitListener(queues = "${mq.topic.queue.name}")
    public void receiverMsg(String message) {
        List<ServerInfo> list = JSON.parseArray(message, ServerInfo.class);
        if(list != null && !list.isEmpty()) {
            QueryWrapper<ServerInfo> queryWrapper;
            UpdateWrapper<ServerInfo> updateWrapper;
            for (ServerInfo info : list) {
                info.setServerId(info.getId());
                info.setId(null);

                queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("server_id", info.getServerId());
                if (serverInfoMapper.selectCount(queryWrapper) > 0) {
                    updateWrapper = new UpdateWrapper<>();
                    updateWrapper.eq("server_id", info.getServerId());
                    serverInfoMapper.update(info, updateWrapper);
                    logger.info("服务信息：更新数据1条");
                }else {
                    serverInfoMapper.insert(info);
                    logger.info("服务信息：新增数据1条");
                }
            }
        }
    }

}
