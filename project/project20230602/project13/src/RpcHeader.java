import java.io.Serializable;
public class RpcHeader implements Serializable {
    private short magic;
    private short headerLength;
    private byte messageType;
    private byte serializerType;
    public RpcHeader(){};
    public RpcHeader(short magic, short headerLength, byte messageType,byte serializerType) {
        this.magic = magic;
        this.headerLength = headerLength;
        this.messageType = messageType;
        this.serializerType = serializerType;
    }
    public short getMagic() {
        return magic;
    }
    public void setMagic(short magic) {
        this.magic = magic;
    }
    public short getHeaderLength() {
        return headerLength;
    }
    public void setHeaderLength(short headerLength) {
        this.headerLength = headerLength;
    }
    public byte getMessageType() {
        return messageType;
    }
    public void setMessageType(byte messageType) {
        this.messageType = messageType;
    }
    public byte getSerializerType() {
        return serializerType;
    }
    public void setSerializerType(byte serializerType) {
        this.serializerType = serializerType;
    }
}
