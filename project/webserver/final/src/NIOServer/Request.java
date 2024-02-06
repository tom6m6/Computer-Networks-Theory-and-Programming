package NIOServer;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
public class Request {
    private String requestContext;
    private SocketChannel socketChannel;
    private String url;
    Request(SocketChannel s) {
        socketChannel = s;
    }
    public void handle() throws Exception{
        parseRequestContext();
        parseRequestUrl();
    }
    public String getContext() { return requestContext; }
    public String getUrl() { return url; }
    private void parseRequestContext() throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        StringBuilder stringBuilder = new StringBuilder();

        int length = socketChannel.read(byteBuffer);
        stringBuilder.append(new String(byteBuffer.array(), 0, length));
        requestContext = stringBuilder.toString();
    }
    private void parseRequestUrl() {
        int idx1, idx2;
        idx1 = requestContext.indexOf(' ');
        url = "/null";
        if (idx1 != -1) {
            idx2 = requestContext.indexOf(' ', idx1 + 1);
            if (idx2 != -1) url = requestContext.substring(idx1 + 1, idx2);
        }
    }
}