package com.zykx.monitor.manager.mybatis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zykx.monitor.manager.MonitorMangerApplication;
import com.zykx.monitor.manager.entity.ServerInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = MonitorMangerApplication.class)
class ServerInfoMapperTest {

    @Autowired
    ServerInfoMapper serverInfoMapper;

    @Test
    void insertNewData() {
        ServerInfo server = new ServerInfo();
        server.setAreaCode(1);
        server.setName("监控服务");
        server.setDescription("监控管理服务");
        server.setAppId("tomcat");
        server.setIp("192.168.2.113");
        server.setPort(22);
        server.setStartCommand("service tomcat start");
        server.setStopCommand("service tomcat stop");
        server.setRestartCommand("service tomcat restart");
        server.setStatusCommand("service tomcat status");
        serverInfoMapper.insert(server);
        System.out.println("新服务信息插入成功！");
    }

    @Test
    void selectList() {
        Wrapper<ServerInfo> wrapper = new QueryWrapper<>();
        List<ServerInfo> serverInfoList = serverInfoMapper.selectList(wrapper);
        System.out.println(JSON.toJSONString(serverInfoList, SerializerFeature.WRITE_MAP_NULL_FEATURES));
    }
}