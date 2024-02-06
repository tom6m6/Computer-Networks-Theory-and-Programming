package NettyServer;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline channelPipeline = socketChannel.pipeline();// pipeline里添加handler
        // 对http进行处理，将请求和应答消息编码或解码为HTTP消息
        channelPipeline.addLast(new HttpServerCodec());
        // 添加自定义处理器
        // 用POST方式请求服务器的时候，对应参数信息保存在message body中
        // 因为HttpServerCodec只能获取uri中的参数，如果用HttpServerCodec无法完全的解析Http POST请求
        // 所以这里需要加上HttpObjectAggregator类
        channelPipeline.addLast(new HttpObjectAggregator(65536)); // 64*1024

        // ChunkedWriteHandler进行大规模文件传输
        channelPipeline.addLast(new ChunkedWriteHandler());

        channelPipeline.addLast(new NettyServerHandleAdapter());
    }
}