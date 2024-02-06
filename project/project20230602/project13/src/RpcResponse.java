import java.io.Serializable;
public class RpcResponse implements Serializable {
    public static final boolean SUCCESS = true;
    public static final boolean FAILURE = false;
    private Object result;
    private Throwable error;
    private boolean isSuccess;
    public RpcResponse(Object result, Throwable error, boolean isSuccess) {
        this.result = result;
        this.error = error;
        this.isSuccess = isSuccess;
    }
    public Object getResult() {
        return result;
    }
    public void setResult(Object result) {
        this.result = result;
    }
    public Throwable getError() {
        return error;
    }
    public void setError(Throwable error) {
        this.error = error;
    }
    public boolean isSuccess() {
        return isSuccess;
    }
    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
