package com.imckh.gitblog.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * <p>Description: </p>
 *
 * @author Cui Kaihui
 * @date 2019/5/20 21:46
 */
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith({SpringExtension.class})
public class UserControllerTest {
    private MockMvc mockMvc;

    @Test
    void getMessage() throws Exception {
        mockMvc.perform(get("/user/getMessage"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.result", "fail").exists());
    }
}