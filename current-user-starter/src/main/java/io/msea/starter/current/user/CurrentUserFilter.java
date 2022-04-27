package io.msea.starter.current.user;

import cn.hutool.core.util.StrUtil;
import io.msea.starter.current.user.constant.Constant;
import io.msea.starter.current.user.util.TokenUtil;
import io.msea.starter.web.exception.ExceptionConstants;
import io.msea.starter.web.exception.MseaApiException;
import io.msea.starter.web.vo.ResultCode;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Order(1)
@WebFilter(urlPatterns = "/*", filterName = "currentUserFilter")
@AllArgsConstructor
public class CurrentUserFilter implements Filter {

    private AuthProperties authProperties;

    private static final PathMatcher PATH_MATCHER = new AntPathMatcher();

    private static Set<String> defaultPublicResources = Sets.newHashSet(
            "/swagger-resources/**",
            "/doc.html",
            "/v2/api-docs",
            "/v2/api-docs-ext",
            "/webjars/**",
            "/**/**.js",
            "/**/**.css",
            "/favicon.ico"
    );

    private static Set<String> skipResources = new HashSet<>();

    @PostConstruct
    public void post() {
        skipResources.addAll(defaultPublicResources.stream().map(it -> authProperties.getPrefix() + it).collect(Collectors.toSet()));
        if (authProperties.getPublicResources() != null && !authProperties.getPublicResources().isEmpty()) {
            skipResources.addAll(authProperties.getPublicResources().stream().map(it -> authProperties.getPrefix() + it).collect(Collectors.toSet()));
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String url = request.getRequestURI();

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            System.out.println(name+ ": " + request.getHeader(name));
        }

        boolean shouldSkip = skipResources.stream().anyMatch(path -> PATH_MATCHER.match(path, url));
        boolean isInnerRequest = "true".equalsIgnoreCase(request.getHeader(Constant.INNER_REQUEST));

        if (!shouldSkip && !isInnerRequest) {
            try {
                String authHeader = request.getHeader(Constant.HEADER_AUTHORIZATION);
                if (authHeader == null) {
                    log.error("Missing Header: Authorization, url:" + url);
                    throw new ServletException("Missing Header: Authorization ");
                }
                if (!authHeader.startsWith(Constant.AUTHORIZATION_PREFIX)) {
                    log.error("Invalid Authorization, url:" + url);
                    throw new ServletException("Invalid Authorization");
                }
                String token = authHeader.substring(7);
                if (StrUtil.isBlank(token)){
                    log.error("Invalid Authorization, url:" + url);
                    throw new ServletException("Invalid Authorization");
                }
                boolean verify = TokenUtil.verify(token);
                if (!verify) {
                    log.error("Invalid Authorization, url" + url + " isInnerRequest: " + isInnerRequest);
                    throw new ServletException("Invalid Authorization");
                }

                CurrentUser currentUser = TokenUtil.getClaim(token, CurrentUser.class);
                CurrentUserHelper.setCurrentUser(currentUser);
                CurrentUserHelper.setAuthorization(authHeader);
            } catch (Exception e) {
                servletRequest.setAttribute(ExceptionConstants.FILTER_EXCEPTION, new MseaApiException(ResultCode.AUTHORIZATION_FAILED));
                servletRequest.getRequestDispatcher(ExceptionConstants.ERROR_CONTROLLER_URL).forward(servletRequest, servletResponse);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
