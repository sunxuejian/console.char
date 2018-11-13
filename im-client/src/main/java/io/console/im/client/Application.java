package io.console.im.client;

import io.console.im.client.dto.Attribute;
import io.console.im.client.handler.CharClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.concurrent.*;

/**
 * @author xuejian.sun
 * @date 2018/11/12 16:44
 */
@Slf4j
public class Application {

    private static int MAX_RETRY = 5;

    public static void main(String[] args) throws Exception {
        new Application().start();
    }

    private void start() throws InterruptedException {
        Bootstrap client = new Bootstrap();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        client.group(worker)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new CharClientHandler());
                    }
                });

        connect(client, "127.0.0.1", 6666, MAX_RETRY).channel().closeFuture().sync();

    }

    private ChannelFuture connect(final Bootstrap bootstrap, final String ip, final int port, final int retry) {
        return bootstrap
                .connect(ip, port)
                .addListener(future -> {
                    if(future.isSuccess()) {
                        log.info("connect success -> {}:{}", ip, port);
                        Channel channel = ((ChannelFuture) future).channel();
                        channel.attr(Attribute.user).set("xjsun");
                        consoleOperation(channel);
                    } else if(retry == 0) {
                        log.warn("重连次数已经到达上限,退出系统 ...");
                    } else {
                        int currRetry = (MAX_RETRY - retry) + 1;
                        log.info("第 {} 次重连, 最大重连次数 -> {}", currRetry, MAX_RETRY);
                        int delay = 1 << currRetry;
                        bootstrap.config().group()
                                .schedule(() -> connect(bootstrap, ip, port, retry - 1), delay, TimeUnit.SECONDS);

                    }
                });
    }

    /**
     * 读取控制台输入指令
     *
     * @param channel socket channel
     */
    private void consoleOperation(Channel channel) {
        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                Scanner scanner = null;
                while(true) {
                    if(scanner == null) {
                        scanner = new Scanner(System.in);
                    }
                    System.out.print("[" + channel.attr(Attribute.user).get() + "@" + "127.0.0.1" + "]:");
                    if(scanner.hasNext()) {
                        log.info(scanner.next());
                    }
                }
            } catch (Exception e) {
                log.error("", e);
            }
        });
    }
}
