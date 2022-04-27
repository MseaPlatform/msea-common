package io.msea.starter.current.user.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JwtUtil {

    public static final long DEFAULT_SESSION_TIMEOUT = 8 * 60 * 60 * 1000;
    public static final String DEFAULT_SECRET = "703decb9-a2e2-4466-8b4d-ceff71dfa8d4";

    public static String sign(String name, String value) {
        return sign(name, value, DEFAULT_SECRET);
    }

    public static String sign(String name, String value, String secret) {
        Date expiresAt = new Date(System.currentTimeMillis() + DEFAULT_SESSION_TIMEOUT);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create().withClaim(name, value)
                .withExpiresAt(expiresAt)
                .sign(algorithm);
    }

    public static boolean verify(String token) {
        return verify(token, DEFAULT_SECRET);
    }

    public static void check(String token) {
        if (!verify(token)) {
            throw new JWTVerificationException("Token expired");
        }
    }

    public static boolean verify(String token, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException e) {
            return false;
        }
        return true;
    }

    public static boolean verify(String token, String name, String value, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withClaim(name, value).build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException e) {
            return false;
        }
        return true;
    }

    public static String getClaim(String token, String name) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim(name).asString();
    }


    public static String verifyAndGet(String token, String name) {
        return verifyAndGet(token, name, DEFAULT_SECRET);
    }

    /**
     * @throws JWTVerificationException
     */
    public static String verifyAndGet(String token, String name, String secret) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim(name).asString();
    }

    public static <T> T getClaim(String token, String name, Class<T> tClass) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim(name).as(tClass);
    }

}
