package com.imckh.gitblog.controller;

import com.imckh.gitblog.model.Result;
import com.imckh.gitblog.service.IResultTestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/hello")
@Log4j2
@Api(tags = "测试相关接口", value = "提供测试相关的Rest API")
public class HelloController {
    @Autowired
    private IResultTestService resultTestService;

    @GetMapping("hello")
    public Result hello(@RequestParam("name") String name) {
        return resultTestService.testResult(name);
    }

    @ApiIgnore // 接口过滤
    @GetMapping("hi")
    public Result hi(@RequestParam("name") String name) {
        return resultTestService.testResult(name);
    }

    @ApiOperation(value="获取用户列表", notes="")
    @GetMapping("/find/{id}")
    public Result findById(@PathVariable("id") int id) {
        return resultTestService.testResult(id + "");
    }

    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "Result")
    @PostMapping("/add")
    public boolean addUser(@RequestBody Result user) {
        return false;
    }

    @PutMapping("/update")
    public boolean update(@RequestBody Result user) {
        return true;
    }

    @ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer")
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") int id) {
        return true;
    }
}
