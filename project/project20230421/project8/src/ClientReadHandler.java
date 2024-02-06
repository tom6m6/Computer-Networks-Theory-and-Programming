// 处理从客户端读数据的线程
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

class ClientReadHandler extends Thread {
    private final BufferedReader bufferedReader;
    ClientReadHandler(InputStream inputStream) {
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream,
                StandardCharsets.UTF_8));
    }
    @Override
    public void run() {
        try {
            while (true) {
                // 拿到客户端⼀条数据
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