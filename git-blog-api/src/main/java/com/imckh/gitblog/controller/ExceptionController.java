package com.imckh.gitblog.controller;

import com.imckh.gitblog.model.http.ResultMap;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.CredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>Description: </p>
 *
 * @author Cui Kaihui
 * @date 2019/5/14 23:30
 */
@RestControllerAdvice
@Log4j2
@Api(tags = "异常捕获相关接口", value = "异常捕获相关接口")
public class ExceptionController {
    private final ResultMap resultMap;

    @Autowired
    public ExceptionController(ResultMap resultMap) {
        this.resultMap = resultMap;
    }

    /**
     * 捕捉 ShiroRealm 抛出的异常
     */
    @ExceptionHandler({AccountException.class, CredentialsException.class})
    public ResultMap handleShiroException(Exception ex) {
        log.debug(ex.getMessage());
        return resultMap.fail().message(ex.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public ResultMap handleException(Exception ex) {
        log.debug(ex.getMessage());
        return resultMap.fail().message("系统错误: " + ex.getMessage());
    }
}