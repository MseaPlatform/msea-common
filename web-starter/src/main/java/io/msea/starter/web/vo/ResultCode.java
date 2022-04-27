package io.msea.starter.web.vo;

import io.msea.starter.web.exception.IResponseCode;
import lombok.Getter;

/**
 * @author msea-admin
 * @create 2022-01-25 15:30
 */
@Getter
public enum ResultCode implements IResponseCode {
    SUCCESS("200", "Success", "Success"),
    TOKEN_AUTHENTIC_FAILED("401", "Token Verify Failed", ""),
    BAD_PARAMETER("460", "Params Lost", ""),
    COMMON_SYS_ERROR("000001", "System Error", "System Error"),
    COMMON_REQUEST_METHOD_NOT_SUPPORT("000002", "Method Not Support", ""),
    COMMON_REQUEST_PARAMETER_MISSING("000003", "", ""),
    COMMON_SYS_PARAMETER_CONVERT_ERROR("000004", "", ""),
    COMMON_DATA_NOT_FOUND("000005", "", ""),
    COMMON_UNSUPPORTED_PARAMETER("000006", "Params Not Support", ""),
    AUTHORIZATION_FAILED("000007", "", ""),
    AUTHORIZATION_EXPIRED("000008", "", ""),
    ARITHMETIC_EXCEPTION("000009","","Arithmetic Exception"),
    PARAMETER_VALID_EXCEPTION("000010","","Parameter Valid Exception"),
    ;

    public String code;
    public String msg;
    public String msgEn;

    private ResultCode(String code, String msg, String msgEn) {
        this.code = code;
        this.msg = msg;
        this.msgEn = msgEn;
    }

}
