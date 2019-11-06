package com.zykx.monitor.manager.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zykx.monitor.manager.entity.ServerMonitorLog;

import java.util.List;

/**
 * 服务状态监控记录Service接口
 */
public interface ServerMonitorLogService {
    /**
     * 分页查询
     * @param pageNo 当前页码
     * @param pageSize 分页尺寸
     * @return
     */
    Page<ServerMonitorLog> page(int pageNo, int pageSize);

    /**
     * 批量插入监控记录
     * @param logList 服务状态监控记录
     */
    int batchInsert(List<ServerMonitorLog> logList);

}
