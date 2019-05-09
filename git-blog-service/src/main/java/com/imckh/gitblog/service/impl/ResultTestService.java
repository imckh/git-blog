package com.imckh.gitblog.service.impl;

import com.imckh.gitblog.dao.IResultTestDao;
import com.imckh.gitblog.model.Result;
import com.imckh.gitblog.service.IResultTestService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ResultTestService implements IResultTestService {
    @Autowired
    IResultTestDao resultTestDao;

    @Override
    public Result testResult(String name) {
        return resultTestDao.testResult(name);
    }
}
