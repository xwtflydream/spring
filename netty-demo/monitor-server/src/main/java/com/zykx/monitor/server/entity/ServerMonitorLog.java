package com.zykx.monitor.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 服务监控记录实体类
 */
@Data
@TableName("ms_server_monitor_log")
public class ServerMonitorLog implements Serializable {
    private static final long serialVersionUID = 2391005151696361872L;

    private Long id;                        // 唯一标识ID
    private Integer serverId;               //服务ID
    private Date monitorTime = new Date();  //监控时间
    private Integer status;                 //运行状态（1：正常，2：停止）
    private Integer synced = 0;             // 是否已同步(0：否，1：是)

}
