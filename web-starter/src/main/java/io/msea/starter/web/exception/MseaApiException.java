package io.msea.starter.web.exception;


import io.msea.starter.web.vo.ResultCode;

/**
 * @author msea-admin
 * @create 2022-01-25 15:30
 */
public class MseaApiException extends BaseException {
    private static final long serialVersionUID = -8080414991091331855L;
    private String errorCode;
    private String msgEn;

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getMsgEn(){
        return this.msgEn;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public MseaApiException(String errorCode, String message, String msgEn) {
        super(message);
        this.errorCode = errorCode;
        this.msgEn = msgEn;
    }

    public MseaApiException(IResponseCode resultCode) {
        super(resultCode.getMsg());
        this.errorCode = resultCode.getCode();
        this.msgEn = resultCode.getMsgEn();
    }

    public static MseaApiException create(ResultCode resultCode) {
        return new MseaApiException(resultCode);
    }

    public static MseaApiException create(String errorCode, String message, String msgEn) {
        return new MseaApiException(errorCode, message, msgEn);
    }

    public static MseaApiException create(IResponseCode responseCode) {
        return new MseaApiException(responseCode.getCode(), responseCode.getMsg(), responseCode.getMsgEn());
    }
}