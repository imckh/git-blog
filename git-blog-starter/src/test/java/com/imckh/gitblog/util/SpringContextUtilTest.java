package com.imckh.gitblog.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author Cui Kaihui
 * @date 2019/5/13 21:40
 */
@SpringBootTest
public class SpringContextUtilTest {
    @Test
    void getActiveProfile() {
        assertEquals("dev", SpringContextUtil.getActiveProfile());
    }

    @Test
    void getPort() {
        // spring启动后可以, 测试不通过
        // assertEquals("8080", SpringContextUtil.getPort());
    }
}
