import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import java.util.Scanner;

public class TCPServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;

    public void start(int port) throws IOException {
// 1. 创建⼀个服务器端Socket，即ServerSocket，监听指定端⼝
        serverSocket = new ServerSocket(port);
// 2. 调⽤accept()⽅法开始监听，阻塞等待客户端的连接
        int k=0;
        while(true){
            System.out.println("阻塞等待客户端连接中...");
            clientSocket = serverSocket.accept();
            ClientHandler tcpServerThread = new ClientHandler(clientSocket,k);
            tcpServerThread.start();
            k++;
        }
    }

    public class ClientHandler extends Thread {
        int threadnum;
        private Socket clientSocket;
        PrintWriter out;
        BufferedReader in;

        public ClientHandler(Socket clientSocket,int k){
            this.clientSocket=clientSocket;
            this.threadnum=k;
        }
        @Override
        public void run(){
            try{
                out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream(),
                        StandardCharsets.UTF_8), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(),
                        StandardCharsets.UTF_8));
                Scanner sc = new Scanner(in);
                String str;
                while(sc.hasNext()){
                    str=sc.next();
                    System.out.println("我是服务端，客户端"+String.valueOf(threadnum)+"说: "+ str);
                    out.println("服务端转换后发给" +String.valueOf(threadnum)+": "+ str.toUpperCase());
                }

            }catch(IOException e){
                System.out.println("连接异常断开");
            }
        }
        public void STOP(){
            try {
                if(in!=null) in.close();
                if(out!=null) out.close();
                if(clientSocket!=null) clientSocket.close();
                if(serverSocket!=null) serverSocket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        public void main(String[] args){
            try{
                this.run();
            }finally {
                this.STOP();
            }
        }
    }

    public static void main(String[] args) {
        int port = 9091;
        TCPServer server=new TCPServer();
        try {
            server.start(port);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}