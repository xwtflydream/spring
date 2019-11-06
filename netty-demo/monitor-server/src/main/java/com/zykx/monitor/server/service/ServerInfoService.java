package com.zykx.monitor.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zykx.monitor.server.entity.ServerInfo;

/**
 * 服务信息维护Service接口
 */
public interface ServerInfoService {

    /**
     * 分页查询
     * @param pageNo 当前页码
     * @param pageSize 分页尺寸
     * @return
     */
    Page<ServerInfo> page(int pageNo, int pageSize);

    /**
     * 保存服务信息（包括新增和更新）
     * @param info 服务信息
     */
    int save(ServerInfo info);

    /**
     * 根据ID删除服务信息
     * @param id 服务信息ID
     */
    int delete(int id);

}
