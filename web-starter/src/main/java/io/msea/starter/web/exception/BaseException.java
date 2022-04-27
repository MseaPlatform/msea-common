package io.msea.starter.web.exception;

/**
 * @author msea-admin
 * @create 2022-01-25 15:30
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 770716188662234652L;
    private String code;
    private String msgEn;

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String code, String message, String msgEn) {
        super(message);
        this.code = code;
        this.msgEn = msgEn;
    }

    public String getCode() {
        return this.code;
    }
    public String getMsgEn() {
        return this.msgEn;
    }
}