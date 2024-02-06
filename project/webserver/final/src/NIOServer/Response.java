package NIOServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
public class Response {
    private Request request;
    private SocketChannel socketChannel;
    private ServerSocketChannel serverSocketChannel;
    Response(Request request_, SocketChannel socketChannel_, ServerSocketChannel serverSocketChannel_) {
        request = request_;
        socketChannel = socketChannel_;
        serverSocketChannel = serverSocketChannel_;
    }
    static String messageWrapper(int statusCode, String message) {
        return "HTTP/1.1 " + statusCode + "\r\n" +
                "Content-Type:text/html;charset=utf-8" +"\r\n" + "\r\n" + message;
    }
    public void handle() throws IOException {
        if (request.getUrl().equals("/shutdown")) {
            System.out.println("shutting down the server...");
            serverSocketChannel.close();
            return;
        }
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        File file = new File("src/", request.getUrl());
        if (file.exists()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            FileChannel fileChannel = fileInputStream.getChannel();
            String message = messageWrapper(200, "");
            byteBuffer.put(message.getBytes());
            // 将 buffer 的内容写入对应的目标对象，都需要先调用 flip 方法
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            fileChannel.transferTo(0, fileChannel.size(), socketChannel);
            fileInputStream.close();
        } else {
            file = new File("src/404.html");
            FileInputStream fileInputStream = new FileInputStream(file);
            FileChannel fileChannel = fileInputStream.getChannel();
            String message = messageWrapper(404, "");
            byteBuffer.put(message.getBytes());
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            fileChannel.transferTo(0, fileChannel.size(), socketChannel);
            fileInputStream.close();
        }
    }
}