package com.imckh.gitblog.api;

import com.imckh.gitblog.model.http.ResultMap;
import com.imckh.gitblog.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>Description: </p>
 *
 * @author Cui Kaihui
 * @date 2019/5/14 23:28
 */
@RestController
@RequestMapping("/user")
@Log4j2
@Api(tags = "用户(已登录)相关接口", value = "用户(已登录)相关接口")
public class UserController {
    private final ResultMap resultMap;
    @Autowired
    private UserService userService;

    @Autowired
    public UserController(ResultMap resultMap) {
        this.resultMap = resultMap;
    }

    @GetMapping(value = "/getMessage")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    public ResultMap getMessage() {
        return resultMap.success().message("您拥有用户权限，可以获得该接口的信息！");
    }

    @PostMapping("/updatePassword")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    public ResultMap updatePassword(String username, String oldPassword, String newPassword) {
        String dataBasePassword = userService.getByUserName(username).getPassword();
        if (dataBasePassword.equals(oldPassword)) {
            //userService.updatePassword(username, newPassword);
        } else {
            return resultMap.fail().message("密码错误！");
        }
        return resultMap.success().code(200).message("成功获得信息！");
    }

    /**
     * 拥有 vip 权限可以访问该页面
     */
    @GetMapping("/getVipMessage")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    @RequiresPermissions("vip")
    public ResultMap getVipMessage() {
        return resultMap.success().code(200).message("成功获得 vip 信息！");
    }
}