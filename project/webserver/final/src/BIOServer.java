import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;
// 采用BIO方式实现Web Server
public class BIOServer implements Runnable{
    static ServerSocket serverSocket;
    Socket socket;
    static int isShut;
    BIOServer(Socket s) {
        socket = s;
    }
    static String messageWrapper(int statusCode, String message) {
        // 包装好响应报文
        return "HTTP/1.1 " + statusCode + "\r\n" +
                "Content-Type:text/html;charset=utf-8" +"\r\n" +
                "\r\n" +    // 数据部和首部行之间的空行不能忘
                message;
    }
    static String getURL(String message) {
        int idx1, idx2;
        idx1 = message.indexOf(' ');
        if (idx1 != -1) {
            idx2 = message.indexOf(' ', idx1 + 1);
            if (idx2 != -1) return message.substring(idx1 + 1, idx2);
        }
        return null;
    }

    @Override
    public void run() {
        try {
            System.out.println("Client:" + socket.getInetAddress().getLocalHost() + " has connected to the Server");
            // 准备工作
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            InputStreamReader isr = new InputStreamReader(is);
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedReader br = new BufferedReader(isr);
            BufferedWriter bw = new BufferedWriter(osw);
            // 读取客户端发送来的消息
            String message = br.readLine();
            System.out.println("Client: " + message);
            String url = getURL(message);
            if (url == null || url.equals("/")) url = "/index.html";
            System.out.println("URL :" + url);
            if (url.equals("/shutdown")) {
                System.out.println("shutting down the server...");
                socket.close();
                this.isShut = 1;
                return;
            } else {
                File file = new File("src" + url);
                if (file.exists()) {
                    Long fileLength = file.length();
                    byte[] fileContent = new byte[fileLength.intValue()];
                    FileInputStream fis = new FileInputStream(file);
                    fis.read(fileContent);
                    fis.close();
                    String page = new String(fileContent);
                    bw.write(messageWrapper(200, page));
                } else{
                    file = new File("src/404.html");
                    Long fileLength = file.length();
                    byte[] fileContent = new byte[fileLength.intValue()];
                    FileInputStream fis = new FileInputStream(file);
                    fis.read(fileContent);
                    fis.close();
                    String page = new String(fileContent);
                    bw.write(messageWrapper(404, page));
                }
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("shutting down the Thread...");
        }
    }
    public static void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(8081);
        // 多线程的频繁创建和销毁很花费时间
        // 利用线程池我们可以回收那些本应该被销毁的线程，在需要的时候继续使用，这样可以提升运行效率
        int poolSize = 20;
        isShut = 0;
        ThreadPoolExecutor pool = new ThreadPoolExecutor(poolSize, poolSize, 120L, TimeUnit.SECONDS,
                new ArrayBlockingQueue(poolSize),
                new ThreadPoolExecutor.DiscardPolicy());

        while(isShut == 0) {
            System.out.println("Waiting for Threads' connection...");
            Socket s = serverSocket.accept();
            if(isShut == 1){
                s.close();
                continue;
            }
            pool.execute(new BIOServer(s));
        }
        serverSocket.close();
        pool.shutdown();
    }
}