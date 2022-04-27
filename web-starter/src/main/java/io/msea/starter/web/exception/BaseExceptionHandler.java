package io.msea.starter.web.exception;

import io.msea.starter.web.vo.BaseResponse;
import io.msea.starter.web.vo.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import javax.servlet.http.HttpServletRequest;

/**
 * @author msea-admin
 * @create 2022-01-25 15:30
 */
@RestControllerAdvice
public class BaseExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(BaseExceptionHandler.class);

    public BaseExceptionHandler() {
    }

    @ExceptionHandler({Throwable.class})
    @ResponseBody
    public BaseResponse exceptionHandler(HttpServletRequest req, Throwable throwable) throws Exception {
        throwable.printStackTrace();
        this.logger.info("BaseExceptionHandler.exceptionHandler");
        BaseResponse result = new BaseResponse();
        result.setResult(ResultCode.COMMON_SYS_ERROR);
        this.logger.error("========[code:" + ResultCode.COMMON_SYS_ERROR.code + "]======", throwable);
        return result;
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public BaseResponse exceptionHandler(HttpServletRequest req, Exception exception) throws Exception {
        exception.printStackTrace();
        this.logger.info("BaseExceptionHandler.exceptionHandler");
        BaseResponse result = new BaseResponse();
        result.setResult(ResultCode.COMMON_SYS_ERROR);
        this.logger.error("========[code:" + ResultCode.COMMON_SYS_ERROR.code + "]======", exception);
        return result;
    }

    @ExceptionHandler({MseaApiException.class})
    @ResponseBody
    public BaseResponse exceptionHandler(HttpServletRequest req, MseaApiException exception) throws Exception {
        exception.printStackTrace();
        this.logger.info("BaseExceptionHandler.exceptionHandler");
        BaseResponse result = new BaseResponse();
        result.setCode(exception.getErrorCode());
        result.setMsg(exception.getMessage());
        result.setMsgEn(exception.getMsgEn());
        this.logger.error("========[code:" + result.getCode() + "]" + result.getMsg() + "======", exception);
        return result;
    }

    @ExceptionHandler({ArithmeticException.class})
    @ResponseBody
    public BaseResponse exceptionHandler(HttpServletRequest req, ArithmeticException exception) throws Exception {
        exception.printStackTrace();
        this.logger.info("BaseExceptionHandler.exceptionHandler");
        BaseResponse result = new BaseResponse();
        result.setResult(ResultCode.ARITHMETIC_EXCEPTION);
        this.logger.error("========[code:" + ResultCode.ARITHMETIC_EXCEPTION.code + "]======", exception);
        return result;
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    public BaseResponse exceptionHandler(HttpServletRequest req, HttpRequestMethodNotSupportedException exception) throws Exception {
        exception.printStackTrace();
        this.logger.info("BaseExceptionHandler.exceptionHandler");
        BaseResponse result = new BaseResponse();
        result.setResult(ResultCode.COMMON_REQUEST_METHOD_NOT_SUPPORT);
        this.logger.error("========[code:" + ResultCode.COMMON_REQUEST_METHOD_NOT_SUPPORT.code + "]======", exception);
        return result;
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public BaseResponse exceptionHandler(HttpServletRequest req, MissingServletRequestParameterException exception) throws Exception {
        exception.printStackTrace();
        this.logger.info("BaseExceptionHandler.exceptionHandler");
        BaseResponse result = new BaseResponse();
        result.setResult(ResultCode.COMMON_REQUEST_PARAMETER_MISSING);
        this.logger.error("========[code:" + ResultCode.COMMON_REQUEST_PARAMETER_MISSING.code + "]======", exception);
        return result;
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseBody
    public BaseResponse exceptionHandler(HttpServletRequest req, HttpMessageNotReadableException exception) throws Exception {
        exception.printStackTrace();
        this.logger.info("BaseExceptionHandler.exceptionHandler");
        BaseResponse result = new BaseResponse();
        result.setResult(ResultCode.COMMON_SYS_PARAMETER_CONVERT_ERROR);
        this.logger.error("========[code:" + ResultCode.COMMON_SYS_PARAMETER_CONVERT_ERROR.code + "]======", exception);
        return result;
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    @ResponseBody
    public BaseResponse exceptionHandler(HttpServletRequest req, MethodArgumentTypeMismatchException exception) throws Exception {
        exception.printStackTrace();
        this.logger.info("BaseExceptionHandler.exceptionHandler");
        BaseResponse result = new BaseResponse();
        result.setResult(ResultCode.COMMON_SYS_PARAMETER_CONVERT_ERROR);
        this.logger.error("========[code:" + ResultCode.COMMON_SYS_PARAMETER_CONVERT_ERROR.code + "]======", exception);
        return result;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public BaseResponse exceptionHandler(HttpServletRequest req, MethodArgumentNotValidException exception) throws Exception {
        exception.printStackTrace();
        this.logger.info("BaseExceptionHandler.exceptionHandler");
        BaseResponse result = new BaseResponse();
        result.setResult(ResultCode.PARAMETER_VALID_EXCEPTION);
        result.setMsg(exception.getBindingResult().getFieldError().getDefaultMessage());
        this.logger.error("========[code:" + ResultCode.PARAMETER_VALID_EXCEPTION.code + "]======", exception);
        return result;
    }
}
