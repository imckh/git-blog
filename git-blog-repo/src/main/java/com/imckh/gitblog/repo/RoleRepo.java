package com.imckh.gitblog.repo;

import com.imckh.gitblog.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

/**
 * <p>Description: </p>
 *
 * @author Cui Kaihui
 * @date 2019/5/16 23:14
 */
public interface RoleRepo extends JpaRepository<Role, BigDecimal> {
}
