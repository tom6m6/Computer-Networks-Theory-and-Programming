package NettyServer;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import java.io.File;
import java.io.RandomAccessFile;

public class NettyServerHandleAdapter extends SimpleChannelInboundHandler<FullHttpRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {
        String url = fullHttpRequest.uri();
        int not_found = 0;
        if(url.equalsIgnoreCase("/") || url.equalsIgnoreCase("/index.html")){
            url="/index.html"; //null
        }else if(url.equalsIgnoreCase("/shutdown")){
            System.exit(0);
        }else{
            url="/404.html";
            not_found = 1;
        }
        // 根据地址构建
        File file=new File("src"+url);
        // 构建http响应
        HttpResponse httpResponse=new DefaultHttpResponse(fullHttpRequest.protocolVersion(), HttpResponseStatus.OK);
        // 设置文件格式内容
        if(url.endsWith(".html")) httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");


        if(file.exists()&&not_found!=1){
            httpResponse.setStatus(HttpResponseStatus.OK);
        } else{
            httpResponse.setStatus(HttpResponseStatus.NOT_FOUND);
        }
        RandomAccessFile randomAccessFile=new RandomAccessFile(file,"r");

        // 设置HTTP头部信息
        httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, randomAccessFile.length());
        httpResponse.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);

        channelHandlerContext.write(httpResponse);// 写回HTTP响应报文
        /*
            正常情况下，Java的内存有堆内存、栈内存和字符串常量池等，其中堆内存是占用内存空间最大的一块，也是Java对象存放的地方。
            一般我们的数据如果需要从IO读取到堆内存，中间需要经过Socket缓冲区。
            一个数据会被拷贝两次才能到达他的的终点，如果数据量大就会造成不必要的资源浪费。
            但是NIO免去了将文件的内容从文件系统移动到网络栈的的繁琐步骤，具有零拷贝特性
        */
        // 从FileInputStream创建一个DefaultFileRegion，然后将其写入Channel，利用零拷贝性质来传输一个文件
        channelHandlerContext.write(new DefaultFileRegion(randomAccessFile.getChannel(), 0, file.length()));// 写回文件

        // 写入文件尾部
        channelHandlerContext.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
        randomAccessFile.close();// 关闭
    }
}
