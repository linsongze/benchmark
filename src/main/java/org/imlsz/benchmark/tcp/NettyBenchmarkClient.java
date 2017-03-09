package org.imlsz.benchmark.tcp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by lsz on 2017/3/9.
 */
public class NettyBenchmarkClient implements TcpBenchmarkClient {
    private TcpBenchmarkSummary tcpBenchmarkSummary;
    private String host;
    private int port;
    private ChannelFuture channelFuture;
    public NettyBenchmarkClient(String host,int port){
        this.host = host;
        this.port = port;
    }
    public void send(byte[] bytes) {
        channelFuture.channel().writeAndFlush(bytes);
        int size =bytes.length;
        this.tcpBenchmarkSummary.incrReqBytes(size);
    }

    public void connect() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new NettyClientHandle(NettyBenchmarkClient.this));
                    }
                });
        channelFuture =  bootstrap.connect(host, port).sync();
        System.out.println(Thread.currentThread().getName()+"-client started!");
    }
    public TcpBenchmarkClient setSummary(TcpBenchmarkSummary tcpBenchmarkSummary) {
        this.tcpBenchmarkSummary =tcpBenchmarkSummary;
        return this;
    }
    public TcpBenchmarkSummary getSummary(){
        return this.tcpBenchmarkSummary;
    }
}
