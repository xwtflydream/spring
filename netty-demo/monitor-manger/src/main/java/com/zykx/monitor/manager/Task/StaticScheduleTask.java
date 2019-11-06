package com.zykx.monitor.manager.Task;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zykx.monitor.manager.entity.ServerInfo;
import com.zykx.monitor.manager.mybatis.ServerInfoMapper;
import com.zykx.monitor.manager.rabbitmq.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 静态定时任务类
 */
@Component
@Configuration
@EnableScheduling //开启定时任务
public class StaticScheduleTask {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ServerInfoMapper serverInfoMapper;
    @Autowired
    Sender sender;

    /**
     * 服务信息定时同步任务
     */
    @Scheduled(cron = "${monitor.server.sync.cron}")
    private void serverStatusSyncTask() {

        QueryWrapper<ServerInfo> queryWrapper = new QueryWrapper<>();
        List<ServerInfo> list = serverInfoMapper.selectList(queryWrapper);
        if (list != null && !list.isEmpty()) {
            sender.send(JSON.toJSONString(list));
            logger.info("同步服务信息数据已发送");
        }else {
            logger.info("没有待同步的服务信息数据");
        }
    }
}
