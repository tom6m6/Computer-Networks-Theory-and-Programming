import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
public class NIOClient {
    private SocketChannel channel;
    public void start(String ip, int port) throws IOException {
        this.channel = SocketChannel.open();
        channel.connect(new InetSocketAddress(ip,port));
        channel.write(ByteBuffer.wrap("Hello Server".getBytes()));
        ByteBuffer buffer = ByteBuffer.allocate(64);
        System.out.println(new String(buffer.array(),0, channel.read(buffer), StandardCharsets.UTF_8));
        channel.close();
    }
    public static void main(String[] args) {
        NIOClient NIOclient = new NIOClient();
        try {
            NIOclient.start("127.0.0.1",9091);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
