package com.imckh.gitblog.service;

import com.imckh.gitblog.model.Role;
import com.imckh.gitblog.model.User;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author Cui Kaihui
 * @date 2019/5/16 22:47
 */
public interface UserService {
    void save(User user);

    void delete(BigDecimal id);

    List<User> getAll();

    User getById(BigDecimal id);

    User getByUserName(String username);

    Role getRoleByUserName(String userName);
}
