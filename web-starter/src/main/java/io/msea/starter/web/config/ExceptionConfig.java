package io.msea.starter.web.config;

import io.msea.starter.web.exception.BaseExceptionHandler;
import io.msea.starter.web.exception.ErrorController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author msea-admin
 * @create 2022-01-25 15:30
 */
@Slf4j
@ConditionalOnWebApplication
@Configuration
public class ExceptionConfig {

    @Bean
    public BaseExceptionHandler globalExceptionHandler() {
        return new BaseExceptionHandler();
    }

    @Bean
    @ConditionalOnWebApplication
    public ErrorController errorController() {
        return new ErrorController();
    }


}
