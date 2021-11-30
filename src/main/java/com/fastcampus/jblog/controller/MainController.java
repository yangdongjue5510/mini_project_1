package com.fastcampus.jblog.controller;

import com.fastcampus.jblog.biz.blog.BlogService;
import com.fastcampus.jblog.biz.blog.BlogVO;
import com.fastcampus.jblog.biz.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("/")
    public String index(HttpSession session, Model model) {
        if(session.getAttribute("user") != null) {
            BlogVO userBlog = blogService.getUserBlog((UserVO)session.getAttribute("user"));
            session.setAttribute("userBlog", userBlog);
        }

        List<BlogVO> blogs = blogService.getBlogs();
        model.addAttribute("blogs", blogs);

        return "forward:/index.jsp";
    }
}
