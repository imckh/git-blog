package com.imckh.gitblog.repo.impl;

import com.imckh.gitblog.repo.IResultTestDao;
import com.imckh.gitblog.model.Result;
import org.springframework.stereotype.Component;

@Component
public class ResultTestDao implements IResultTestDao {
    @Override
    public Result testResult(String name) {
        return new Result(200, String.format("Hello %s!", name));
    }
}
