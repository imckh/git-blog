package com.imckh.gitblog.api;

import com.imckh.gitblog.constant.ISysConst;
import com.imckh.gitblog.model.User;
import com.imckh.gitblog.model.http.ResultMap;
import com.imckh.gitblog.service.UserService;
import com.imckh.gitblog.util.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@Api(tags = "用户登录相关接口", value = "用户登录相关接口")
@RestController
public class LoginController {
    private final ResultMap resultMap;
    @Autowired
    private UserService userService;

    @Autowired
    public LoginController(ResultMap resultMap) {
        this.resultMap = resultMap;
    }

    /**
     * 登陆
     *
     * @param username 用户名
     * @param password 密码
     */
    @ApiParam(name = "用户登录", value = "用户登录value")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultMap login(String username, String password) {
        User user = userService.getByUserName(username);

        if (user == null) {
            return resultMap.fail().code(401).message("用户名错误");
        } else if (!password.equals(user.getPassword())) {
            return resultMap.fail().code(401).message("密码错误");
        } if (ISysConst.IND_YES.equals(user.getBan())) {
            return resultMap.fail().code(401).message("该用户已被封号！");
        } else {
            return resultMap.success().code(200).message(JWTUtil.createToken(username));
        }
    }

    @RequestMapping(path = "/unauthorized/{message}")
    public ResultMap unauthorized(@PathVariable String message) throws UnsupportedEncodingException {
        return resultMap.success().code(401).message(message);
    }
}