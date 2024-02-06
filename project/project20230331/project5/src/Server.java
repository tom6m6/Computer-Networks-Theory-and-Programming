package IO;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;
public class Proxy implements Runnable{
    static ServerSocket ss_client;
    static String messageWrapper(int statusCode, String message) {
        return "HTTP/1.1 " + statusCode + "\r\n" +
                "Content-Type:text/html;charset=utf-8" +"\r\n" +
                "\r\n" +
                message;
    }
    static String parseURL(String mess) {
        int idx1, idx2;
        idx1 = mess.indexOf(' ');
        if (idx1 != -1) {
            idx2 = mess.indexOf(' ', idx1 + 1);
            if (idx2 != -1) return mess.substring(idx1 + 1, idx2);
        }
        return null;
    }
    Socket s_client;
    Proxy(Socket s_) {
        s_client = s_;
    }
    public void run() {
        try {
            Socket s_server = new Socket("127.0.0.1", 8081);
            // System.out.println("客户端:" + s_client.getInetAddress().getLocalHost() + ":" +  s_client.getPort() + "已连接到服务器");
            // System.out.println("服务端:" + s_server.getInetAddress().getLocalHost() + ":" +  s_server.getPort() + "已连接到服务器");
            InputStream is_client = s_client.getInputStream();
            OutputStream os_client = s_client.getOutputStream();
            InputStream is_server = s_server.getInputStream();
            OutputStream os_server = s_server.getOutputStream();
            InputStreamReader isr_client = new InputStreamReader(is_client);
            OutputStreamWriter osw_client = new OutputStreamWriter(os_client);
            InputStreamReader isr_server = new InputStreamReader(is_server);
            OutputStreamWriter osw_server = new OutputStreamWriter(os_server);
            BufferedReader br_client = new BufferedReader(isr_client);
            BufferedWriter bw_client = new BufferedWriter(osw_client);
            BufferedReader br_server = new BufferedReader(isr_server);
            BufferedWriter bw_server = new BufferedWriter(osw_server);
            //读取客户端发送来的消息
            String mess_client = br_client.readLine();
            // System.out.println(mess_client);
            //bw_server.write(mess_client);
            bw_server.write(mess_client + '\n');
            // bw_server.flush();
            bw_server.flush();
            // bw_server.close(); // 不能关 输出流关闭会造成socket被关闭 输入输出流都不可用
            String mess_server;
            String mess = "";
            while((mess_server = br_server.readLine()) != null) {
                mess += mess_server +'\n';
            }
            bw_client.write(mess);
            bw_client.close();
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        ss_client = new ServerSocket(8082);
        int poolSize = 30;
        ThreadPoolExecutor pool = new ThreadPoolExecutor(poolSize, poolSize,
                60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue(poolSize),
                new ThreadPoolExecutor.DiscardPolicy());
        while (true) {
            // System.out.println("线程等待连接...");
            Socket s = ss_client.accept();
            pool.execute(new Proxy(s));
        }
    }
}