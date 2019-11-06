package com.zykx.monitor.client.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * netty客户端
 */
public class NettyClient {

    private String host = "192.168.2.52";
    private int port = 8082;

    private Bootstrap bootstrap;
    private int times = 0;

    public NettyClient(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
        NettyClient client = this;
        bootstrap.group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new NettyClientInitializer(client));
    }

    /**
     * 连接方法
     */
    public void connect() {
        ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress(host, port));
        channelFuture.addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("connect to server success");
            } else {
                System.out.println("connect to server failed,try times:" + ++times);
                connect();
            }
        });
    }

}
