package io.console.im.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xuejian.sun
 * @date 2018/11/12 16:51
 */
@Slf4j
public class CharClientHandler extends SimpleChannelInboundHandler {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
        log.info("channelRead0 ... ");
        log.info("received -> {}",((ByteBuf)msg).toString(CharsetUtil.UTF_8));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info("channelActive ... ");
        ByteBuf buffer = ctx.alloc().buffer();
        byte[] request = "hello console char !".getBytes(CharsetUtil.UTF_8);
        buffer.writeBytes(request);
        ctx.channel().writeAndFlush(buffer);
    }
}
