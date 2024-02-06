import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
public class UDPProvider {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(9091);
        byte[] buf = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(buf, 0, buf.length);
        System.out.println("阻塞等待发送者的消息...");
        datagramSocket.receive(receivePacket);


        int len = receivePacket.getLength();
        String data = new String(receivePacket.getData(),0, len);
        int port = MessageUtil.parsePort(data);
        String ip = receivePacket.getAddress().getHostAddress();
        System.out.println("我是接受者，" + ip + ":" + port + " 的发送者说: "+ data);
        System.out.println("我是接收者，要回送的端口号是: " + port);

        String tag = UUID.randomUUID().toString();
        String SendBackData = MessageUtil.buildWithTag(tag);
        byte[] SendBackDataBytes = SendBackData.getBytes();
        DatagramPacket SendBackPacket = new DatagramPacket(SendBackDataBytes, 0 ,
                SendBackDataBytes.length, InetAddress.getLocalHost() , port);
        datagramSocket.send(SendBackPacket);

        datagramSocket.close();
    }
}