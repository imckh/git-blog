package com.imckh.gitblog.shiro;

import com.imckh.gitblog.model.User;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

@Log4j2
public class ShiroRealm extends AuthorizingRealm {
    private User testUser = User.builder().userName("imckh").password("0000").status("1").build();
    /**
     * 获取用户角色和权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        return null;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        log.info("用户" + userName + "认证-----ShiroRealm.doGetAuthenticationInfo");
        User user;

        if (!testUser.getUserName().equals(userName)) {

        } else if (!testUser.getPassword().equals(password)) {

        }

        if (!testUser.getUserName().equals(userName)) {
            throw new UnknownAccountException("用户名或密码错误！");
        }
        if (!password.equals(testUser.getPassword())) {
            throw new IncorrectCredentialsException("用户名或密码错误！");
        }
        if (testUser.getStatus().equals("0")) {
            throw new LockedAccountException("账号已被锁定,请联系管理员！");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(testUser, password, getName());

        return info;
    }
}