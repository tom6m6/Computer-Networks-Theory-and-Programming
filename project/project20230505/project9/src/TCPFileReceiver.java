import java.io.*;
import java.net.*;

public class TCPFileReceiver {
    public static void main(String[] args) throws IOException {
        File file = new File("checksum_recv.txt");
        FileOutputStream output = new FileOutputStream(file);
        ServerSocket serverSocket = new ServerSocket(9091);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        byte[] data = new byte[1024];

        int len;
        while ((len = inputStream.read(data)) != -1) {
            if (len == 0) break;
            output.write(data,0,len);
        }
        System.out.println("文件接受完成,它的MD5:" + MD5Util.getMD5(file));
        inputStream.close();
        output.close();
        socket.close();
        serverSocket.close();
    }
}
