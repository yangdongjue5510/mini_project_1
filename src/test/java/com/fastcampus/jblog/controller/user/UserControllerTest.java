package com.fastcampus.jblog.controller.user;

import com.fastcampus.jblog.biz.user.UserService;
import com.fastcampus.jblog.biz.user.UserVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    void init() {
        UserVO user = UserVO.builder().userId(1).build();
        Mockito.when(userService.getUser(user)).thenReturn(user);
    }

    @Test
    void loginView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/loginView"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("bloglogin"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void login() throws Exception {
        UserVO user = UserVO.builder().userId(1).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/login").param("userId", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("forward:/"))
                .andExpect(MockMvcResultMatchers.model().attribute("userVO", user))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void logout() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/logout"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andDo(MockMvcResultHandlers.print());
    }
}