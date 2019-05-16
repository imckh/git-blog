package com.imckh.gitblog.repo;

import com.imckh.gitblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;

/**
 * <p>Description: </p>
 *
 * @author Cui Kaihui
 * @date 2019/5/16 22:45
 */
public interface UserRepo extends JpaRepository<User, BigDecimal> {
}
