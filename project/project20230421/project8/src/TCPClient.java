import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TCPClient {
    private Socket clientSocket;

    public void start(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        ServerHandler serverHandler = new ServerHandler(clientSocket);
        serverHandler.start();
    }

    public static void main(String[] args) {
        int port = 9091;
        TCPClient client = new TCPClient();
        try {
            client.start("127.0.0.1", port);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}