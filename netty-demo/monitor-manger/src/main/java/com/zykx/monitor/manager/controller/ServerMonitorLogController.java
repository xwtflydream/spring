package com.zykx.monitor.manager.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zykx.monitor.manager.entity.ServerInfo;
import com.zykx.monitor.manager.entity.ServerMonitorLog;
import com.zykx.monitor.manager.service.ServerInfoService;
import com.zykx.monitor.manager.service.ServerMonitorLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 服务状态监控记录Controller类
 */
@RestController
@RequestMapping("/server_status_log")
public class ServerMonitorLogController {

    @Autowired
    ServerMonitorLogService serverMonitorLogService;

    @GetMapping("/page/{current}/{size}")
    public Page<ServerMonitorLog> page(@PathVariable("current") int current, @PathVariable("size") int size) {
        return serverMonitorLogService.page(current, size);
    }

    @PostMapping("/batch_insert")
    public int batchInsert(@RequestBody List<ServerMonitorLog> logList) throws Exception {
        return serverMonitorLogService.batchInsert(logList);
    }

}
