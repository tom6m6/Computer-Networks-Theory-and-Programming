import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;

class iProxy2Handler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws
            Throwable {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(9091));
        ObjectOutputStream os = new
                ObjectOutputStream(socket.getOutputStream());



        // rpc提供⽅和调⽤⽅之间协商的报⽂格式和序列化规则
        RpcHeader header = getHeader();
        RpcData content = new RpcData(method.getName(),method.getParameterTypes(),args);
        os.writeObject(new Rpc(header,content));


        Object result = new ObjectInputStream(socket.getInputStream()).readObject();
        Rpc rpc = (Rpc) result;
        return rpc.getRpcData();
    }
    private static RpcHeader getHeader() {
        short magic = 0;
        short headerLength = 6;
        byte messageType = 0;
        byte serializeType = (byte) 0;
        return new RpcHeader(magic, headerLength, messageType, serializeType);
    }
}