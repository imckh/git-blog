package com.imckh.gitblog.api;

import com.imckh.gitblog.model.Result;
import com.imckh.gitblog.service.IResultTestService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class HelloController {
    @Autowired
    private IResultTestService resultTestService;

    @GetMapping("hello")
    public Result hello(@RequestParam("name") String name) {
        return resultTestService.testResult(name);
    }
}
