package io.msea.starter.current.user;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Data
public class AuthProperties {

    private List<String> publicResources;

    @Value("${server.servlet.context-path:}")
    private String prefix;
}
