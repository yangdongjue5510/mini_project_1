package com.yang.controller.blog;

import com.yang.domain.BlogVO;
import com.yang.domain.UserVO;
import com.yang.service.BlogService;
import com.yang.service.CategoryService;
import com.yang.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class BlogApiController {

    @Autowired
    BlogService blogService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String index(Model model) {
        if (model.getAttribute("blogList") == null){
            List<BlogVO> list = blogService.getBlogList();
            model.addAttribute("blogList", list);
        }
        List<BlogVO> blogList = (List<BlogVO>) model.getAttribute("blogList");
        List<UserVO> userList = blogList.stream().map(blog -> userService.getUserByBlogId(blog.getBlogId()))
                .collect(Collectors.toList());
        model.addAttribute("userList", userList);
        return "forward:/indexView";
    }

    @RequestMapping("/searchBlog")
    public String searchBlog(@RequestParam String searchCondition,
                             @RequestParam String searchKeyword,
                             Model model) {
        List<BlogVO> blogList = null;
        if (!searchCondition.equals("USER_NAME")) {
            blogList = blogService.searchBlog(searchCondition, searchKeyword);
        }
        else if (searchCondition.equals("USER_NAME")) {
            blogList = blogService.searchBlogByUserName(searchKeyword);
        }
        model.addAttribute("blogList", blogList);
        model.addAttribute("searchCondition", searchCondition);
        return "forward:/indexView";
    }


    @PostMapping("/blogCreate")
    public String blogCreate(@RequestParam String blogName,
                             HttpSession session) {
        UserVO user = (UserVO) session.getAttribute("user");
        BlogVO blog = blogService.getBlog(user.getUserId());
        if (blog == null) {
            blogService.insertBlog(blogName, user);
        }
        return "redirect:/";
    }

    @RequestMapping("/deleteBlog/{blogId}")
    public String blogDelete(@PathVariable int blogId) {
        blogService.deleteBlog(blogId);
        return "redirect:/";
    }

    @RequestMapping("/blogMain/{blogId}")
    public String blogMain(@PathVariable int blogId, Model model) {
        blogService.forwardBlogView(blogId, model);
        return "forward:/blogMainView";
    }

    @RequestMapping("/blogAdmin/{blogId}")
    public String blogAdmin(@PathVariable int blogId, Model model) {
        blogService.forwardBlogView(blogId, model);
        return "forward:/blogAdminView";
    }

    @RequestMapping("/blogAdminBasic/{blogId}")
    public String blogAdminBasic(@PathVariable int blogId,
                                 @ModelAttribute BlogVO blog) {
        blog.setBlogId(blogId);
        blogService.updateBlog(blog);
        return "forward:/blogMain/"+blogId;
    }
}
