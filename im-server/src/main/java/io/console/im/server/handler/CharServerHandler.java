package io.console.im.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xuejian.sun
 * @date 2018/11/12 16:18
 */
@Slf4j
public class CharServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info("channelActive ...");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        log.info("channelRead ..");
        ByteBuf byteBuf = (ByteBuf) msg;
        String message = byteBuf.toString(CharsetUtil.UTF_8);
        log.info("received -> {}", message);

        ByteBuf buffer = ctx.alloc().buffer();
        byte[] response = "welcome console char!".getBytes(CharsetUtil.UTF_8);
        buffer.writeBytes(response);
        ctx.channel().writeAndFlush(buffer);
    }
}
