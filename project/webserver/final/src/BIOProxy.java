import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;
public class BIOProxy implements Runnable{
    // serverSocket is for true client.
    static ServerSocket serverSocket;
    Socket for_client; // 面向真的客户端
    BIOProxy(Socket s) {
        for_client = s;
    }
    @Override
    public void run() {
        try {
            // 代理作为服务器
            System.out.println("True client:" + for_client.getInetAddress().getLocalHost() + ":" + for_client.getPort() + " has connected.");
            InputStream is_for_client = for_client.getInputStream();
            OutputStream os_for_client = for_client.getOutputStream();
            InputStreamReader isr_for_client = new InputStreamReader(is_for_client);
            OutputStreamWriter osw_for_client = new OutputStreamWriter(os_for_client);
            BufferedReader br_for_client = new BufferedReader(isr_for_client);
            BufferedWriter bw_for_client = new BufferedWriter(osw_for_client);

            // 首先，应该读取客户端发送来的消息
            String mess_from_client = br_for_client.readLine();
            System.out.println(mess_from_client);

            // 使用正则表达式作为分隔符
            // \s表示匹配空白字符 +表示匹配连续 合在一起就是标识匹配1个/多个连续空白字符
            // split将mess_from_client拆分一下，结果放到result数组
            String[] result = mess_from_client.split("\\s+");
            // 以 GET http://www.baidu.com/search/error.html HTTP/1.1 为例
            String method = result[0],url = result[1],version = result[2];


            String[] ans = url.split("://"); // split之后为 http和www.baidu.com/search/error.html
            String tmp = ans[1].split("/")[0]; // 获得www.baidu.com

            String host,port;
            // 有冒号说明主机名和端口号都被提取了
            // 我默认使用80端口，即HTTP默认的端口
            if(tmp.contains(":")){
                host = tmp.split(":")[0];
                port = tmp.split(":")[1];
            }else{
                host = tmp;
                port = "80";
            }

            if(host.equals("localhost"))host = "127.0.0.1";
            // 代理作为客户端
            Socket for_server = new Socket(host, Integer.valueOf(port));
            System.out.println("True server:" + for_server.getInetAddress().getLocalHost() + ":" + for_server.getPort() + " has connected.");

            InputStream is_for_server = for_server.getInputStream();
            OutputStream os_for_server = for_server.getOutputStream();
            InputStreamReader isr_for_server = new InputStreamReader(is_for_server);
            OutputStreamWriter osw_for_server = new OutputStreamWriter(os_for_server);
            BufferedReader br_for_server = new BufferedReader(isr_for_server);
            BufferedWriter bw_for_server = new BufferedWriter(osw_for_server);

            // 然后，向服务端发送报文
            // IP地址是127.0.0.1，表示本地主机,这样就要按照我第一问写Server时的链接规则去特判
            if(host.equals("127.0.0.1")) {
                String[] tmpurl = url.split("/");
                url = "/" + tmpurl[tmpurl.length-1];
            }

            // 发送请求报文给真的服务器
            String message_to_server = method + " " + url + " " + version + "\r\n" + "\r\n";

            bw_for_server.write(message_to_server);
            System.out.println(message_to_server);
            // 将缓冲区中的数据全部发送走，不要等缓冲区满
            bw_for_server.flush();
            // 如果关闭了输出流，socket会被关闭，这样输入流也不能用了
            // bw_server.close();

            // 最后当然是从真服务器收到响应报文，转交给真客户端
            byte[] data = new byte[10240];
            int len;
            while ((len = is_for_server.read(data)) > 0) {  // read from true server
                os_for_client.write(data, 0, len);  // write to true client
            }
            // 最后别忘了flush
            os_for_client.flush();
            os_for_client.close();
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(6666);// 防止端口冲突
        int poolSize = 20;
        ThreadPoolExecutor pool = new ThreadPoolExecutor(poolSize, poolSize, 120L, TimeUnit.SECONDS,
                new ArrayBlockingQueue(poolSize),
                new ThreadPoolExecutor.DiscardPolicy());
        while (true) {
            System.out.println("Waiting for Threads' connection...");
            Socket s = serverSocket.accept();
            System.out.println("111");
            pool.execute(new BIOProxy(s));
        }
    }
}