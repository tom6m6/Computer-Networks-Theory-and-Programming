import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
public class UDPFileReceiver {
    public static void main(String[] args) throws IOException {
        File file = new File("checksum_recv.txt"); //要接收的⽂件存放路径
        FileOutputStream output = new FileOutputStream(file);
        byte[] data=new byte[1024];
        DatagramSocket ds=new DatagramSocket(9091);
        DatagramPacket dp=new DatagramPacket(data, data.length);

        while(true){
            ds.receive(dp);
            int len = dp.getLength();
            if(len == 0)break;
            output.write(data,0,len);
        }

        output.close();
        ds.close();
        System.out.println("接收来⾃"+dp.getAddress().toString()+"的⽂件已完成！对⽅所使⽤的端⼝号为："+dp.getPort());
        file = new File("checksum_recv.txt");
        System.out.println("接收⽂件的md5为: " + MD5Util.getMD5(file));
    }
}