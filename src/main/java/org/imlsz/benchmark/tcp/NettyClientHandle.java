package org.imlsz.benchmark.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by lsz on 2017/3/9.
 */
public class NettyClientHandle extends ChannelInboundHandlerAdapter {
    private TcpBenchmarkClient client;
    public NettyClientHandle(TcpBenchmarkClient client){
        this.client = client;
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        ByteBuf buf = (ByteBuf)msg;
        int size = buf.readableBytes();
        client.getSummary().incrRespBytes(size);
        //buf.skipBytes(size);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        client.getSummary().incrErrorTimes();
    }

}
