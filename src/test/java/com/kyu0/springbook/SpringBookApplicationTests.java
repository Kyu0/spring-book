package com.kyu0.springbook;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kyu0.springbook.web.HelloController;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HelloController.class)
public class SpringBookApplicationTests {

    @Autowired
    private MockMvc mvc;    //Controller와 ControllerAdvice 등 외부 활동과 관련된 부분만 활성화 된다.
                            //JPA 작동 X

    @Test
    public void returns_hello() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
            .andExpect(status().isOk())
            .andExpect(content().string(hello));
    }

    @Test
    public void returns_helloDto() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
            .param("name", name)
            .param("amount", String.valueOf(amount)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is(name)))
            .andExpect(jsonPath("$.amount", is(amount)));
    }
}
