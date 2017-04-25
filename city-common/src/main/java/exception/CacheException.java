package exception;

import model.ResultCode;

public class CacheException extends RuntimeException {

    private static final long serialVersionUID = -2678203134198782909L;

    public static final String MESSAGE = "缓存访问出错！";

    protected int code = ResultCode.CACHE_ERROR;

    public CacheException() {
        super(MESSAGE);
    }

    public CacheException(String message) {
        super(message);
    }

    public CacheException(int code, String message) {
        super(message);
        this.code = code;
    }

    public CacheException(String message, Throwable cause) {
        super(message, cause);
    }

    public CacheException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public CacheException(Throwable cause) {
        super(cause);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
