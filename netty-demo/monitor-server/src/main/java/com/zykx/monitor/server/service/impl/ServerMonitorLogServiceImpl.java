package com.zykx.monitor.server.service.impl;

import com.zykx.monitor.server.mybatis.ServerMonitorLogMapper;
import com.zykx.monitor.server.service.ServerMonitorLogService;
import org.springframework.beans.factory.annotation.Autowired;

public class ServerMonitorLogServiceImpl implements ServerMonitorLogService {

    @Autowired
    ServerMonitorLogMapper serverMonitorLogMapper;

}
