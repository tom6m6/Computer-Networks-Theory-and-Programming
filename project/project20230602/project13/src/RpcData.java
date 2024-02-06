import java.io.Serializable;
public class RpcData implements Serializable {
    private String methodName;
    private Class<?>[] parameterTypes;
    private Object[] args;
    public RpcData(String methodName, Class<?>[] parameterTypes, Object[]
            args) {
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.args = args;
    }
    public String getMethodName() {
        return methodName;
    }
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }
    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }
    public Object[] getArgs() {
        return args;
    }
    public void setArgs(Object[] args) {
        this.args = args;
    }
}
