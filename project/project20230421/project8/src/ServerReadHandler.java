// 处理从服务器读数据的线程
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
class ServerReadHandler extends Thread {
    private final BufferedReader bufferedReader;
    ServerReadHandler(InputStream inputStream) {
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream,
                StandardCharsets.UTF_8));
    }
    @Override
    public void run() {
        try {
            while (true) {
                // 拿到服务器⼀条数据
                String str = bufferedReader.readLine();
                if (str == null) {
                    System.out.println("读到的数据为空");
                    break;
                } else {
                    System.out.println("读到的数据为：" + str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
