package com.imckh.gitblog.service;

import com.imckh.gitblog.model.Role;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author Cui Kaihui
 * @date 2019/5/16 23:18
 */
public interface RoleService {
    void save(Role role);

    void delete(BigDecimal id);

    Role getById(BigDecimal id);

    List<Role> getAll();

    Role getByRoleName(String roleName);
}
