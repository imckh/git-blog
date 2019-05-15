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
 * @date 2019/5/14 23:29
 */
@RestController
@RequestMapping("/admin")
@Log4j2
@Api(tags = "admin管理员相关接口", value = "admin管理员相关接口")
public class AdminController {
    private final ResultMap resultMap;

    @Autowired
    public AdminController(ResultMap resultMap) {
        this.resultMap = resultMap;
    }
    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public ResultMap getMessage() {
        return resultMap.success().message("您拥有管理员权限，可以获得该接口的信息！");
    }
}
