package com.imckh.gitblog.shiro;

import com.imckh.gitblog.model.User;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/**
 * @author imckh
 */
@Log4j2
public class ShiroRealm extends AuthorizingRealm {
    private User testUser = User.builder().userName("imckh").password("0000").build();
    /**
     * 获取用户角色和权限, 获取授权信息
     *
     * 只有在需要权限认证时才会进去，
     * 比如前面配置类中配置了
     * filterChainDefinitionMap.put("/admin/**", "roles[admin]"); 的管理员角色，
     * 这时进入 /admin 时就会进入 doGetAuthorizationInfo 方法来检查权限
     *
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 从数据库获得该用户角色
        String role = username.startsWith("i") ? "admin" : "user";

        Set<String> set = new HashSet<>();
        //需要将 role 封装到 Set 作为 info.setRoles() 的参数
        set.add(role);
        //设置该用户拥有的角色
        info.setRoles(set);
        return info;
    }

    /**
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * doGetAuthenticationInfo 方法则是需要身份认证时（比如前面的 Subject.login() 方法）
     *
     * @param authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        log.info("用户" + userName + "认证-----ShiroRealm.doGetAuthenticationInfo");

        // 从数据库获取对应用户名密码的用户
        User user = User.builder().userName(userName).password(password).build();

        if (user == null) {
            throw new UnknownAccountException("用户名或密码错误！");
        }
        if (!user.getPassword().equals(testUser.getPassword())) {
            throw new IncorrectCredentialsException("用户名或密码错误！");
        }

        return new SimpleAuthenticationInfo(token.getPrincipal(), password, getName());
    }
}