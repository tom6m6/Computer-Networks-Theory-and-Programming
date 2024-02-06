import java.io.Serializable;
public class RpcRequest implements Serializable {
    public static final byte serializeCode = (byte) 0;
    public static final byte length = 3;
    private short headerLength;
    private byte serializeType;
    private String methodName;
    private Class<?>[] parameterTypes;
    private Object[] arguments;
    public RpcRequest(short headerLength, byte serializeType, String methodName,
                      Class<?>[] parameterTypes, Object[] arguments) {
        this.headerLength = headerLength;
        this.serializeType = serializeType;
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.arguments = arguments;
    }
    public Object[] getArguments() {
        return arguments;
    }
    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }
    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }
    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }
    public short getHeaderLength() {
        return this.headerLength;
    }
    public void setHeaderLength(short headerLength) {
        this.headerLength = headerLength;
    }
    public byte getSerializeType() {
        return this.serializeType;
    }
    public void setSerializeType(byte serializeType) {
        this.serializeType = serializeType;
    }
    public String getMethodName() {
        return this.methodName;
    }
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}