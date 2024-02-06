import java.io.*;
import java.util.Random;
import java.net.Socket;

public class TCPFileSender {
    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter("checksum.txt");
        Random r = new Random(2023);
        for (int i = 0; i < 1e8 ; i++) {
            fileWriter.write(r.nextInt());
        }

        File file = new File("checksum.txt");
        System.out.println("发送⽂件⽣成完毕");
        System.out.println("发送⽂件的md5为: " + MD5Util.getMD5(file));

        Socket clientSocket = new Socket("localhost", 9091);
        FileInputStream fis = new FileInputStream(file);
        OutputStream outputStream = clientSocket.getOutputStream();

        byte[] bytes = new byte[1024];
        int len;
        while ((len = fis.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
        }

        byte[] a = new byte[0];
        outputStream.write(a);
        System.out.println("文件发送成功!");
        outputStream.close();
        fis.close();
        clientSocket.close();
    }
}
