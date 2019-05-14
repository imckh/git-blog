package com.imckh.gitblog.model.http;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * <p>Description: </p>
 *
 * @author Cui Kaihui
 * @date 2019/5/14 23:26
 */
@Component
public class ResultMap  extends HashMap<String, Object> {
    public ResultMap() {
    }

    public ResultMap success() {
        this.put("result", "success");
        return this;
    }

    public ResultMap fail() {
        this.put("result", "fail");
        return this;
    }

    public ResultMap message(Object message) {
        this.put("message", message);
        return this;
    }
}
