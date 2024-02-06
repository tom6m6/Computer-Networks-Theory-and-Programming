import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.nio.charset.StandardCharsets;

public class TCPServer {
    private ServerSocket serverSocket;
    private List<Socket> sockets = new ArrayList<Socket>();
    private List<ClientHandler> handlers = new ArrayList<ClientHandler>();

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while(true){
            System.out.println("阻塞等待客户端连接中...");
            Socket clientSocket = serverSocket.accept();
            sockets.add(clientSocket);
            ClientHandler clientHandler = new ClientHandler(clientSocket);
            handlers.add(clientHandler);
            clientHandler.start();
            String msg = getSocketAddresses();
            for (ClientHandler ch : handlers){
                ch.send(msg);
            }
        }

    }
    public String getSocketAddresses() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Socket socket : sockets) {
            stringBuilder.append(socket.getRemoteSocketAddress() + " ");
        }
        return stringBuilder.toString();
    }

    public void stop(){
// 关闭相关资源
        try {
            if(serverSocket!=null) serverSocket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        int port = 9091;
        TCPServer server=new TCPServer();
        try {
            server.start(port);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            server.stop();
        }
    }
}