package server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyServer {

    private static final int PORT = 8000;

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                      @Override
                      protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {

                      }
                  }
                );

        bind(bootstrap,PORT);
    }

    private static void bind(ServerBootstrap bootstrap,int port){
        bootstrap.bind(port).addListener(future -> {
            if(future.isSuccess()){
                System.out.println("端口:"+port+"绑定成功");
            }else {
                System.out.println("端口:"+port+"绑定失败");
                bind(bootstrap,port+1);
            }
        });
    }

}
