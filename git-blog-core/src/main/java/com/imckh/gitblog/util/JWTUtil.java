package com.imckh.gitblog.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * <p>Description: </p>
 * JWT 工具类
 * @author Cui Kaihui
 * @date 2019/5/20 22:29
 */
public class JWTUtil {
    /**
     * 过期24h * 7
     */
    private static final long EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;
    /**
     * 密钥
     */
    private static final String SECRET = "GIT-BLOG-BEST";

    /**
     * 使用usernamer字段 创建token
     * @param username
     * @return
     */
    public static String createToken(String username) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            // 附带username信息
            return JWT.create()
                    .withClaim("username", username)
                    // 到期时间
                    .withExpiresAt(date)
                    // 创建一个新的jwt, 并使用给定算法标记
                    .sign(algorithm);
        } catch (IllegalArgumentException | JWTCreationException e) {
            return null;
        }
    }

    /**
     * 校验token是否正确
     * @param token 密钥
     * @param username 用户名
     * @return 是否正确
     */
    public static boolean verify(String token, String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            // 在token中附带了 username 的信息
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();

            // 验证token
            verifier.verify(token);
            return true;
        } catch (IllegalArgumentException | JWTVerificationException e) {
            return false;
        }
    }

    /**
     * 得到token中的username字段的值
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
