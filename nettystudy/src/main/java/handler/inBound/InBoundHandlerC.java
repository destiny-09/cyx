package handler.inBound;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

public class InBoundHandlerC extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("InBoundHandlerC: " + ((ByteBuf)msg).toString(Charset.forName("utf-8")));
        System.out.println("InBoundHandlerC: 回复客户端");

        ByteBuf buffer = ctx.alloc().buffer();
        byte[] bytes = "你好，客户端!".getBytes(Charset.forName("utf-8"));
        buffer.writeBytes(bytes);

        ctx.channel().writeAndFlush(buffer);
    }
}
