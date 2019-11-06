package com.zykx.monitor.manager.mybatis;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zykx.monitor.manager.entity.ServerMonitorLog;

import java.util.List;

/**
 * 服务监控记录持久化接口
 */
public interface ServerMonitorLogMapper extends BaseMapper<ServerMonitorLog> {

    /**
     * 批量插入服务状态监控记录
     * @param logList
     */
    void batchInsert(List<ServerMonitorLog> logList);

}
