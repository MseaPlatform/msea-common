package io.msea.starter.current.user.util;


import cn.hutool.json.JSONUtil;
import io.msea.starter.current.user.constant.Constant;

import javax.servlet.ServletException;

public class TokenUtil {

    public static boolean verify(String token){
        return JwtUtil.verify(token);
    }

    public static <T> T getClaim(String token, Class<T> tClass) throws ServletException {
        String json = JwtUtil.getClaim(token, Constant.CLAIM_NAME_USER_INFO);
        return JSONUtil.toBean(json, tClass);
    }

    public static <T> String signToken(T claimValue) {
        return Constant.AUTHORIZATION_PREFIX + JwtUtil.sign(Constant.CLAIM_NAME_USER_INFO, JSONUtil.toJsonStr(claimValue));
    }
}
