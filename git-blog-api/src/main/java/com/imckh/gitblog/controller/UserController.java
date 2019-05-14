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
 * @date 2019/5/14 23:28
 */
@RestController
@RequestMapping("/user")
@Log4j2
@Api(tags = "用户(已登录)相关接口", value = "用户(已登录)相关接口")
public class UserController {
    private final ResultMap resultMap;

    @Autowired
    public UserController(ResultMap resultMap) {
        this.resultMap = resultMap;
    }

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public ResultMap getMessage() {
        return resultMap.success().message("您拥有用户权限，可以获得该接口的信息！");
    }
}