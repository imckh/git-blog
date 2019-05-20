package com.imckh.gitblog.service.impl;

import com.imckh.gitblog.model.Role;
import com.imckh.gitblog.model.User;
import com.imckh.gitblog.repo.RoleRepo;
import com.imckh.gitblog.service.RoleService;
import com.imckh.gitblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author Cui Kaihui
 * @date 2019/5/16 23:18
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private UserService userService;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Role role) {
        roleRepo.save(role);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(BigDecimal id) {
        roleRepo.deleteById(id);
    }

    @Override
    public Role getById(BigDecimal id) {
        return roleRepo.findById(id).orElse(null);
    }

    @Override
    public List<Role> getAll() {
        return roleRepo.findAll();
    }

    @Override
    public Role getByRoleName(String roleName) {
        return roleRepo.findOne(Example.of(Role.builder().role(roleName).build())).orElse(null);
    }
}
