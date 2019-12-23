package client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;


public class FirstClientHandler extends ChannelInboundHandlerAdapter {



    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive:客户端连接建立成功，发送消息给服务端");
        // 1. 获取数据
        for (int i=0;i<200;i++) {
            ByteBuf byteBuf = getByteBuf(ctx,i);
            // 2. 写数据
            ctx.channel().writeAndFlush(byteBuf);
        }
    }


    private ByteBuf getByteBuf(ChannelHandlerContext ctx,int i) {
        // 1. 获取二进制抽象 ByteBuf
        ByteBuf buffer = ctx.alloc().buffer();

        // 2. 准备数据，指定字符串的字符集为 utf-8
        String message = "你好，服务端!请问什么是粘包什么又是拆包那?" + i;
        byte[] bytes = message.getBytes(Charset.forName("utf-8"));

        // 3. 填充数据到 ByteBuf
//        buffer.writeInt(bytes.length);
        buffer.writeBytes(bytes);

        return buffer;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf)msg;

        System.out.println("channelRead:收到服务端消息:"+byteBuf.toString(Charset.forName("utf-8")));
    }



}
