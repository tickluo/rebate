package exception;


import model.ResultCode;

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -2678203134198782909L;

    public static final String MESSAGE = "业务服务出错！";

    protected int code = ResultCode.SERVICE_ERROR;

    public ServiceException() {
        super(MESSAGE);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
