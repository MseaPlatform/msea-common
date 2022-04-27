package io.msea.starter.current.user.config;

import io.msea.starter.current.user.AuthProperties;
import io.msea.starter.current.user.CurrentUserFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author msea-admin
 * @date 2020/7/31
 * @time 1:52
 */
@Slf4j
@Configuration
public class CurrentUserConfig {

    @Bean
    @ConfigurationProperties(prefix = "auth")
    public AuthProperties authProperties(){
        return new AuthProperties();
    }

    @Bean
    @ConditionalOnWebApplication
    public CurrentUserFilter currentUserFilter(AuthProperties authProperties) {
        log.info("load currentUserFilter...");
        return new CurrentUserFilter(authProperties);
    }
}
