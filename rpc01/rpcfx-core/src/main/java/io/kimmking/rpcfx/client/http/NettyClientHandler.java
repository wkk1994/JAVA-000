package io.kimmking.rpcfx.client.http;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpResponse;

import java.nio.charset.StandardCharsets;

/**
 * @Description
 * @Author Wangkunkun
 * @Date 2020/12/18 10:37
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<FullHttpResponse> {

    private ChannelHandlerContext ctx;

    private ChannelPromise channelPromise;

    private String data;
    private long readByte;
    private long contentLength;


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        this.ctx = ctx;
    }

    public ChannelPromise sendMessage(Object message) {
        while (ctx == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        channelPromise = ctx.writeAndFlush(message).channel().newPromise();
        return channelPromise;
    }

    public String getData() {
        return data;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpResponse msg) throws Exception {
        ByteBuf buf = msg.content();
        readByte += buf.readableBytes();
        System.out.println(buf.toString(StandardCharsets.UTF_8));
        if (data == null) {
            data = buf.toString(StandardCharsets.UTF_8);
        } else {
            data += buf.toString(StandardCharsets.UTF_8);
        }
        channelPromise.setSuccess();

    }
}
