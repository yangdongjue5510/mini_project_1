package com.yang.controller.blog;

import com.yang.domain.BlogVO;
import com.yang.domain.UserVO;
import com.yang.service.BlogService;
import com.yang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;

    @Autowired
    UserService userService;

    @RequestMapping("/indexView")
    public String indexView(HttpSession session, Model model){
        UserVO user = (UserVO) session.getAttribute("user");
        if (user != null) {
            BlogVO blog = blogService.getBlog(user.getUserId());
            if(blog != null) {
                model.addAttribute("blogId", blog.getBlogId());
            }
        }
        return "index";
    }

    @RequestMapping("/blogCreateView")
    public String blogCreateView(HttpSession session) {
        return "blogcreate";
    }

    @RequestMapping("/blogMainView")
    public String blogMainView() {
        return "blogmain";
    }

    @RequestMapping("/blogAdminView")
    public String blogAdminView() {
        return "blogadmin_basic";
    }
}
