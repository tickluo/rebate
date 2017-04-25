package exception;

import model.ResultCode;

public class DaoException extends RuntimeException {

    private static final long serialVersionUID = -2678203134198782909L;

    public static final String MESSAGE = "数据库访问出错！";

    protected int code = ResultCode.DAO_ERROR;

    public DaoException() {
        super(MESSAGE);
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(int code, String message) {
        super(message);
        this.code = code;
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
