import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
public class RpcProvider {
    public static void main(String[] args) {
        Proxy2Impl proxy2Impl = new Proxy2Impl();
        try (ServerSocket serverSocket = new ServerSocket()) {
            serverSocket.bind(new InetSocketAddress(9091));
            try (Socket socket = serverSocket.accept()) {
// ObjectInputStream/ObjectOutStream 提供了将对象序列化和反序列化的功能
                ObjectInputStream is = new
                        ObjectInputStream(socket.getInputStream());


                // rpc提供⽅和调⽤⽅之间协商的报⽂格式和序列化规则
                Object rpcObj = is.readObject();
                Rpc rpc = (Rpc) rpcObj;
                RpcHeader rpcHeader = rpc.getRpcHeader();
                if (rpcHeader.getMagic() != 0)
                    return;
                if (rpcHeader.getMessageType() == 0 && rpcHeader.getSerializerType() == (byte) 0) {
                    RpcData content = (RpcData) rpc.getRpcData();
                    String methodName = content.getMethodName();
                    Class<?>[] parameterTypes = content.getParameterTypes();
                    Object[] arguments = content.getArgs();

                    // rpc提供⽅调⽤本地的对象的⽅法
                    Object result = Proxy2Impl.class.getMethod(methodName, parameterTypes).invoke(proxy2Impl, arguments);
                    // 将结果序列化并返回
                    new ObjectOutputStream(socket.getOutputStream()).writeObject(new Rpc(getHeader(), result));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static RpcHeader getHeader() {
        short magic = 0;
        short headerLength = 6;
        byte messageType = 1;
        byte serializeType = (byte) 0;
        return new RpcHeader(magic, headerLength, messageType, serializeType);
    }
}

