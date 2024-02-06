import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
public class UDPSearcher {
    public static void main(String[] args) throws IOException {
        int Port1 = 30000;
        String sendData = MessageUtil.buildWithPort(Port1);
        byte[] sendBytes = sendData.getBytes(StandardCharsets.UTF_8);
        DatagramSocket datagramSocket = new DatagramSocket(9092);

        DatagramPacket sendPacket = new DatagramPacket(sendBytes, 0, sendBytes.length,
                InetAddress.getByName("255.255.255.255"), 9091);
        datagramSocket.setBroadcast(true);
        datagramSocket.send(sendPacket);
        System.out.println("数据发送完毕...");

        DatagramSocket datagramSocket1 = new DatagramSocket(Port1);
        byte[] buf = new byte[1024];
        DatagramPacket receiveBackPacket = new DatagramPacket(buf, 0, buf.length);
        datagramSocket1.receive(receiveBackPacket);
        int Port2 = receiveBackPacket.getPort();
        int len = receiveBackPacket.getLength();
        String ip = receiveBackPacket.getAddress().getHostAddress();
        String receiveBackString = new String(receiveBackPacket.getData(), 0, len );
        System.out.println("我是发送者，我已经收到了来自"+ ip + ":" + Port2 + "回传的消息,Tag:" + MessageUtil.parseTag(receiveBackString));


        datagramSocket.close();
        datagramSocket1.close();
    }
}
