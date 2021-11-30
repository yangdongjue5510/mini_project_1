package com.fastcampus.jblog.controller.blog;

import com.fastcampus.jblog.biz.blog.Blog;
import com.fastcampus.jblog.biz.blog.BlogService;
import com.fastcampus.jblog.biz.blog.BlogVO;
import com.fastcampus.jblog.biz.blog.SearchVO;
import com.fastcampus.jblog.biz.user.UserVO;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class BlogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BlogService blogService;

    private MockHttpSession session;
    private BlogVO blog;

    @BeforeEach
    void init() {
        UserVO user = UserVO.builder()
                            .userId(1)
                            .userName("YoungJoon")
                            .id("Tester")
                            .password("1234")
                            .role("ADMIN")
                            .build();

        session = new MockHttpSession();
        session.setAttribute("user", user);

        blog = BlogVO.builder()
                     .blogId(1)
                     .title("blog")
                     .title("AWS 강의 블로그")
                     .status("운영")
                     .build();
        blog.setUser(user);
    }

    @Test
    void createBlogView() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/createBlogView").session(session))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("blogcreate"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void createBlog() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/createBlog").session(session).requestAttr("blog", blog))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void searchBlog() throws Exception {
        SearchVO search = SearchVO.builder().searchType("title").searchValue("").build();
        List<BlogVO> blogs = new ArrayList<>();
        blogs.add(BlogVO.builder().blogId(1).title("AWS 강의 블로그").status("운영").build());
        blogs.add(BlogVO.builder().blogId(2).title("Java/Spring으로 만드는 웹서비스").status("운영").build());

        Mockito.when(blogService.searchBlogs(search)).thenReturn(blogs);

        mockMvc.perform(MockMvcRequestBuilders.get("/searchBlog").param("searchType", "title").param("searchValue", ""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("blogs", blogs))
                .andExpect(MockMvcResultMatchers.view().name("forward:/index.jsp"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void blogMain() {
        
    }

    @Test
    void searchPostByCategory() {
    }

    @Test
    void blogAdmin() {
    }

    @Test
    void updateBlog() {
    }

    @Test
    void deleteBlog() {
    }

    @Test
    void blogDeleteRequest() {
    }
}