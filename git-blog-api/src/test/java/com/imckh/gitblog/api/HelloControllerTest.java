package com.imckh.gitblog.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@SpringBootTest
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public class HelloControllerTest {

    private MockMvc mockMvc;

    /**
     * 配置 Spring REST Docs
     * 1. 在 @ExtendWith 中增加 RestDocumentationExtension (JUnit5 的 Extension 相当于 JUnit4 中的 Rule)
     * 2. 将 MockMvc 由直接注入改为手动构建，增加 documentationConfiguration(restDocumentation) 配置
     * 3. 在执行测试的最后，调用 andDo(document("hello")) 给测试调用所生成的文档命名
     *
     * @param webApplicationContext
     * @param restDocumentation
     */
    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    public void hello() throws Exception {
        mockMvc.perform(get("/hello").param("name", "ScienJus"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("msg", "Hello ScienJus!").exists())
                .andDo(document("hello"));
    }
}
