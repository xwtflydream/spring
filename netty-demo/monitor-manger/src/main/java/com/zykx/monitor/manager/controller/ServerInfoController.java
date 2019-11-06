package com.zykx.monitor.manager.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zykx.monitor.manager.entity.ServerInfo;
import com.zykx.monitor.manager.service.ServerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 服务信息Controller类
 */
@RestController
@RequestMapping("/server_info")
public class ServerInfoController {

    @Autowired
    ServerInfoService serverInfoService;

    @GetMapping("/page/{current}/{size}")
    public Page<ServerInfo> page(@PathVariable("current") int current, @PathVariable("size") int size) {
        return serverInfoService.page(current, size);
    }

    @PostMapping("/")
    public int save(@RequestBody ServerInfo info) {
        return serverInfoService.save(info);
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id) {
        return serverInfoService.delete(id);
    }
}
