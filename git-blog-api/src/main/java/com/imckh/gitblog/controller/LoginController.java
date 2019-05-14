package com.imckh.gitblog.controller;

import com.imckh.gitblog.model.User;
import com.imckh.gitblog.model.http.ResponseBo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(tags = "登录相关接口(not REST api)", value = "登录相关接口(这个不是REST api)")
public class LoginController {

    @ApiOperation(value="用户登录", notes="跳转到用户登录 Action")
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @ApiOperation(value="用户登录", notes="用户登录")
    @PostMapping("/login")
    @ResponseBody
    public ResponseBo login(String username, String password) {
        // FIXME 2019-05-14 18:03:57 暂时明文密码
        // password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return ResponseBo.ok();
        } catch (UnknownAccountException e) {
            return ResponseBo.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return ResponseBo.error(e.getMessage());
        } catch (LockedAccountException e) {
            return ResponseBo.error(e.getMessage());
        } catch (AuthenticationException e) {
            return ResponseBo.error("认证失败！");
        }
    }

    @ApiOperation(value="默认跳转到主页", notes="默认跳转到主页")
    @RequestMapping("/")
    public String redirectIndex() {
        return "redirect:/index";
    }

    @ApiOperation(value="登录后跳转到主页", notes="登录后跳转到主页")
    @RequestMapping("/index")
    public String index(Model model) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user", user);
        return "index";
    }
}