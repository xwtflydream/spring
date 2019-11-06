package com.zykx.monitor.manager.utils;

import org.junit.jupiter.api.Test;

class RemoteShellExecutorTest {

    @Test
    void execCommand() {
        RemoteShellExecutor executor = new RemoteShellExecutor("192.168.2.113", "root", "111111");
        try {
            System.out.println(executor.execCommand("service tomcat start"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}