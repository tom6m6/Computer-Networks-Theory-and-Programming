import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class TCPServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private static int BYTE_LENGTH = 64;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("阻塞等待客户端连接中...");
        clientSocket = serverSocket.accept();
        InputStream is = clientSocket.getInputStream();
        for (;;) {
            byte[] lengthBytes = new byte[4];
            int bytesRead = is.read(lengthBytes, 0, 4);
            if (bytesRead < 0) break;
            int messageLength = byteArrayToInt(lengthBytes);
            byte[] messageBytes = new byte[messageLength];
            bytesRead = is.read(messageBytes, 0, messageLength);
            if (bytesRead < 0) break;
            String message = new String(messageBytes);
            System.out.println("服务端已收到消息:  " + message);
        }
    }

    public void stop() {
        try {
            if (clientSocket != null) clientSocket.close();
            if (serverSocket != null) serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int byteArrayToInt(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        return buffer.getInt();
    }

    public static void main(String[] args) {
        int port = 9091;
        TCPServer server = new TCPServer();
        try {
            server.start(port);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            server.stop();
        }
    }
}
