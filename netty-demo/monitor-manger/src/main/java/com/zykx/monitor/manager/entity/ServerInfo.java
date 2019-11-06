package com.zykx.monitor.manager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 服务信息实体类（管理端）
 */
@Data
@TableName("mm_server_info")
public class ServerInfo implements Serializable {
    private static final long serialVersionUID = 680341546547360906L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;                         // ID,服务ID
    private Integer areaCode;                   // 区域编号（1、2、3）
    private String name;                        // 服务名称
    private String description;                 // 服务描述
    private String appId;                       // 服务应用唯一标识ID，例如：tomcat等
    private String ip;                          // 服务IP地址
    private Integer port;                       // 服务端口（可有可无）
    private String loginUser;                   // 登录用户
    private String loginPassword;               // 登录密码
    private Date createTime = new Date();       // 创建时间
    private Date lastModifyTime;                // 最后修改时间
    private String startCommand;                // 启动命令
    private String stopCommand;                 // 停止命令
    private String restartCommand;              // 重启命令
    private String statusCommand;               // 状态命令
    @TableLogic
    private Integer deleted = 0;                // 是否已删除(0：否，1：是)

}
