package com.imckh.gitblog.controller;

import com.imckh.gitblog.model.http.ResultMap;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: </p>
 *
 * @author Cui Kaihui
 * @date 2019/5/14 23:24
 */
@RestController
@RequestMapping("/guest")
@Log4j2
@Api(tags = "游客(未登录)相关接口", value = "游客(未登录)相关接口")
public class GuestController{
    private final ResultMap resultMap;

    @Autowired
    public GuestController(ResultMap resultMap) {
        this.resultMap = resultMap;
    }

    @RequestMapping(value = "/enter", method = RequestMethod.GET)
    public ResultMap login() {
        return resultMap.success().message("欢迎进入，您的身份是游客");
    }

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public ResultMap submitLogin() {
        return resultMap.success().message("您拥有获得该接口的信息的权限！");
    }
}
