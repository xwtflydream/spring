package com.zykx.monitor.server.mybatis;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zykx.monitor.server.entity.ServerMonitorLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 服务监控记录持久化接口
 */
public interface ServerMonitorLogMapper extends BaseMapper<ServerMonitorLog> {

    /**
     * 更新监控数据的同步状态值
     * @param idList
     */
    void updateSyncStatus(@Param("idList") List<Long> idList, @Param("status") Integer status);
}
