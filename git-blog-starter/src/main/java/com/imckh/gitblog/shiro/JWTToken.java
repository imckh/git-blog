package com.imckh.gitblog.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * <p>Description: </p>
 *
 * @author Cui Kaihui
 * @date 2019/5/20 22:22
 */
public class JWTToken implements AuthenticationToken {
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
