package io.msea.starter.web.vo;


import io.msea.starter.web.exception.IResponseCode;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author msea-admin
 * @create 2022-01-25 15:30
 */
public class BaseResponse<T> {
    private String code;
    private String msg;
    private String msgEn;
    private T data;

    public static <T> BaseResponse<T> create(IResponseCode resultCode, T data) {
        BaseResponse<T> baseResponse = new BaseResponse();
        baseResponse.setResult(resultCode);
        baseResponse.setData(data);
        return baseResponse;
    }

    public static <T> BaseResponse<T> create(String code, String msg, String msgEn,  T data) {
        BaseResponse<T> baseResponse = new BaseResponse();
        baseResponse.setCode(code);
        baseResponse.setMsg(msg);
        baseResponse.setMsgEn(msgEn);
        baseResponse.setData(data);
        return baseResponse;
    }

    public static <T> BaseResponse<T> create(String code, String msg, String msgEn) {
        BaseResponse<T> baseResponse = new BaseResponse();
        baseResponse.setCode(code);
        baseResponse.setMsg(msg);
        baseResponse.setMsgEn(msgEn);
        baseResponse.setData((T) null);
        return baseResponse;
    }

    public static <T> BaseResponse<T> create(IResponseCode resultCode) {
        BaseResponse<T> baseResponse = new BaseResponse();
        baseResponse.setResult(resultCode);
        baseResponse.setData((T) null);
        return baseResponse;
    }

    public static <T> BaseResponse<T> success(T data) {
        return create(ResultCode.SUCCESS, data);
    }

    public static <Void> BaseResponse<Void> success() {
        return create(ResultCode.SUCCESS, null);
    }

    public BaseResponse() {
        this.setResult(ResultCode.SUCCESS);
    }

    public BaseResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResponse(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgEn() {
        return this.msgEn;
    }

    public void setMsgEn(String msgEn) {
        this.msgEn = msgEn;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setResult(IResponseCode resultInfo) {
        this.code = resultInfo.getCode();
        this.msg = resultInfo.getMsg();
        this.msgEn = resultInfo.getMsgEn();
    }
}
