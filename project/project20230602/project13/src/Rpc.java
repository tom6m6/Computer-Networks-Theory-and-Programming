import java.io.Serializable;
public class Rpc implements Serializable {
    private RpcHeader rpcHeader;
    private Object rpcData;
    public Rpc(RpcHeader rpcHeader, Object rpcData) {
        this.rpcHeader = rpcHeader;
        this.rpcData = rpcData;
    }
    public RpcHeader getRpcHeader() {
        return rpcHeader;
    }
    public void setRpcHeader(RpcHeader rpcHeader) {
        this.rpcHeader = rpcHeader;
    }
    public Object getRpcData() {
        return rpcData;
    }
    public void setRpcData(Object rpcData) {
        this.rpcData = rpcData;
    }
}
