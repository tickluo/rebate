package exception;


import model.ResultCode;

public class AuthorityException extends RuntimeException {

    private static final long serialVersionUID = -2678203134198782909L;

    public static final String MESSAGE = "未授权！";

    protected int code = ResultCode.SSO_PERMISSION_ERROR;

    public AuthorityException() {
        super(MESSAGE);
    }

    public AuthorityException(String message) {
        super(message);
    }

    public AuthorityException(int code, String message) {
        super(message);
        this.code = code;
    }

    public AuthorityException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorityException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public AuthorityException(Throwable cause) {
        super(cause);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
