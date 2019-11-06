package com.zykx.monitor.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zykx.monitor.manager.entity.ServerInfo;
import com.zykx.monitor.manager.mybatis.ServerInfoMapper;
import com.zykx.monitor.manager.service.ServerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 服务信息维护Service实现类
 */
@Component
@Transactional
public class ServiceInfoServiceImpl implements ServerInfoService {

    @Autowired
    ServerInfoMapper serverInfoMapper;

    @Override
    public Page<ServerInfo> page(int pageNo, int pageSize) {
        Wrapper<ServerInfo> wrapper = new QueryWrapper<>();
        Page<ServerInfo> page = new Page<>(pageNo, pageSize);
        return (Page<ServerInfo>) serverInfoMapper.selectPage(page, wrapper);
    }

    @Override
    public int save(ServerInfo info) {
        int count;
        if (info.getId() == null) {
            count = serverInfoMapper.insert(info);
        }else {
            info.setLastModifyTime(new Date());
            count = serverInfoMapper.updateById(info);
        }
        return count;
    }

    @Override
    public int delete(int id) {
        return serverInfoMapper.deleteById(id);
    }
}
