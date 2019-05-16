package com.imckh.gitblog.service.impl;

import com.google.common.collect.Lists;
import com.imckh.gitblog.model.User;
import com.imckh.gitblog.repo.UserRepo;
import com.imckh.gitblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>Description: </p>
 * save,update,delete方法需要绑定事务
 * 使用@Transactional进行事务绑定
 * @author Cui Kaihui
 * @date 2019/5/16 22:48
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    /**
     * 保存用户
     * @param user
     */
    @Transactional(rollbackOn = Exception.class)
    @Override
    public void save(User user) {
        userRepo.save(user);
    }

    /**
     * 通过id删除用户
     * @param id
     */
    @Transactional(rollbackOn = Exception.class)
    @Override
    public void delete(BigDecimal id) {
        userRepo.deleteById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

    /**
     * 通过id查找用户
     * @param id
     * @return
     */
    @Override
    public User getById(BigDecimal id) {
        return userRepo.findById(id).orElse(null);
    }

    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    @Override
    public User getByUserName(String username) {
        return userRepo.findOne(Example.of(User.builder().username(username).build())).orElse(null);
    }
}
