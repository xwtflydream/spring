package com.zykx.monitor.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zykx.monitor.manager.entity.ServerMonitorLog;
import com.zykx.monitor.manager.mybatis.ServerMonitorLogMapper;
import com.zykx.monitor.manager.service.ServerMonitorLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 服务状态监控记录Service实现类
 */
@Component
@Transactional
public class ServerMonitorLogServiceImpl implements ServerMonitorLogService {

    @Autowired
    ServerMonitorLogMapper serverMonitorLogMapper;

    @Override
    public Page<ServerMonitorLog> page(int pageNo, int pageSize) {
        Wrapper<ServerMonitorLog> wrapper = new QueryWrapper<>();
        Page<ServerMonitorLog> page = new Page<>(pageNo, pageSize);
        return (Page<ServerMonitorLog>) serverMonitorLogMapper.selectPage(page, wrapper);
    }

    @Override
    public int batchInsert(List<ServerMonitorLog> logList) {
        if(logList == null || logList.isEmpty()) {
            return 0;
        }
        serverMonitorLogMapper.batchInsert(logList);
        return logList.size();
    }
}
