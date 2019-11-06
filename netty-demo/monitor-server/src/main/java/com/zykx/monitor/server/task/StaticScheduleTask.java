package com.zykx.monitor.server.task;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zykx.monitor.server.entity.ServerMonitorLog;
import com.zykx.monitor.server.mybatis.ServerMonitorLogMapper;
import com.zykx.monitor.server.utils.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 静态定时任务类
 */
@Component
@Configuration
@EnableScheduling //开启定时任务
public class StaticScheduleTask {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ServerMonitorLogMapper serverMonitorLogMapper;

    /**
     * 服务状态监控记录定时同步任务
     */
    @Scheduled(cron = "${monitor.status.sync.cron}")
    private void serverStatusSyncTask() {
        String url = "http://localhost:8081/server_status_log/batch_insert";

        QueryWrapper<ServerMonitorLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("synced", 0);
        List<ServerMonitorLog> list = serverMonitorLogMapper.selectList(queryWrapper);
        if (list != null && !list.isEmpty()) {
            try {
                String result = HttpClientUtil.sendPostDataByJson(url, JSON.toJSONString(list), "utf-8");
                if (StringUtils.isNotBlank(result) && list.size() == Integer.parseInt(result)){
                    //同步成功后修改已同步数据的同步状态
                    List<Long> idList = list.stream().map(ServerMonitorLog::getId).collect(Collectors.toList());
                    serverMonitorLogMapper.updateSyncStatus(idList, 1);
                    logger.info("已同步数据{}条", list.size());
                }
            }catch (IOException e) {
                logger.error("服务状态监控记录定时同步接口异常，接口地址：{}", url);
            }
        }else {
            logger.info("没有待同步的数据");
        }
    }
}
