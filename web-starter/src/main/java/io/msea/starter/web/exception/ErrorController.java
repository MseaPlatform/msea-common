package io.msea.starter.web.exception;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author msea-admin
 * @create 2022-01-25 15:30
 */
@RestController
@ApiIgnore
public class ErrorController {
    @Resource
    private HttpServletRequest request;

    @RequestMapping(ExceptionConstants.ERROR_CONTROLLER_URL)
    public void rethrow() throws Exception {
        throw ((Exception) request.getAttribute(ExceptionConstants.FILTER_EXCEPTION));
    }
}
