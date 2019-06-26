package com.imckh.gitblog.shiro;

import com.imckh.gitblog.constant.ISysConst;
import com.imckh.gitblog.model.Role;
import com.imckh.gitblog.model.User;
import com.imckh.gitblog.service.RoleService;
import com.imckh.gitblog.service.UserService;
import com.imckh.gitblog.util.JWTUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @author imckh
 */
@Log4j2
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    /**
     * 必须重写此方法，不然会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 获取用户角色和权限, 获取授权信息
     *
     * 只有在需要权限认证时才会进去，
     * 比如前面配置类中配置了
     * filterChainDefinitionMap.put("/admin/**", "roles[admin]"); 的管理员角色，
     * 这时进入 /admin 时就会进入 doGetAuthorizationInfo 方法来检查权限
     *
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     *
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        String username = JWTUtil.getUsername(principal.toString());
        log.info("用户" + username + "权限认证-----ShiroRealm.doGetAuthorizationInfo");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 从数据库获得该用户角色
        Role role = userService.getRoleByUserName(username);
        String roleName = role.getRole();

        // 角色默认权限和用户自定义权限
        // 每个角色拥有默认的权限
        String rolePermission = role.getPermission();

        // 每个用户可以设置新的权限
        // FIXME 2019-05-20 23:02:59 需要数据库表的支持
        String permission = "normal";

        Set<String> roleSet = new HashSet<>();
        Set<String> permissionSet = new HashSet<>();
        //需要将 role, permission 封装到 Set 作为 info.setRoles(), info.setStringPermissions() 的参数
        roleSet.add(roleName);
        //设置该用户拥有的角色
        permissionSet.add(rolePermission);
        permissionSet.add(permission);

        info.setRoles(roleSet);
        info.setStringPermissions(permissionSet);
        return info;
    }

    /**
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * doGetAuthenticationInfo 方法则是需要身份认证时（比如前面的 Subject.login() 方法）
     *
     *  默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     * @param authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//        String userName = (String) token.getPrincipal();
//        String password = new String((char[]) token.getCredentials());

        String token = (String) authenticationToken.getCredentials();
        // 解密获得username, 用于数据库对比
        String userName = JWTUtil.getUsername(token);
        log.info("用户" + userName + "身份认证-----ShiroRealm.doGetAuthenticationInfo");

        if (userName == null || !JWTUtil.verify(token, userName)) {
            throw new AuthenticationException("token认证失败！");
        }
        // 从数据库获取对应用户名密码的用户
        User user = userService.getByUserName(userName);

        if (user == null) {
            throw new AuthenticationException("用户不存在！");
        }

        /*if (ISysConst.IND_YES.equals(user.getBan())) {
            throw new IncorrectCredentialsException("该用户已被封号！");
        }*/

        return new SimpleAuthenticationInfo(token, token, getName());
    }
}