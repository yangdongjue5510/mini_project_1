package com.fastcampus.jblog.controller.post;

import com.fastcampus.jblog.biz.blog.BlogService;
import com.fastcampus.jblog.biz.blog.BlogVO;
import com.fastcampus.jblog.biz.category.CategoryService;
import com.fastcampus.jblog.biz.category.CategoryVO;
import com.fastcampus.jblog.biz.post.PostService;
import com.fastcampus.jblog.biz.post.PostVO;
import com.fastcampus.jblog.biz.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BlogService blogService;

    @RequestMapping("/blogAdminPost")
    public String blogAdminPost(BlogVO blog, Model model) {
        BlogVO findBlog = blogService.getUserBlog(blogService.getBlog(blog).getUser());
        List<CategoryVO> categorys = categoryService.getCategorys(findBlog);

        model.addAttribute("blog", findBlog);
        model.addAttribute("categorys", categorys);

        return "blogadmin_post";
    }

    @RequestMapping("/insertPost")
    public String insertPost(BlogVO blog, CategoryVO category, PostVO post, Model model) {
        blog.setUser(blogService.getBlog(blog).getUser());
        category.setBlog(blog);
        post.setCategory(category);
        postService.insertPost(post);

        model.addAttribute("blogId", blog.getBlogId());

        return "forward:blogMain";
    }

    @RequestMapping("/updatePostView")
    public String updatePostView(BlogVO blog, PostVO post, Model model) {
        PostVO updatePost = postService.getPost(post);
        BlogVO findBlog = blogService.getUserBlog(blogService.getBlog(blog).getUser());
        List<CategoryVO> categorys = categoryService.getCategorys(findBlog);

        model.addAttribute("updatePost", updatePost);
        model.addAttribute("blog", findBlog);
        model.addAttribute("categorys", categorys);

        return "blogadmin_post";
    }

    @RequestMapping("/updatePost")
    public String updatePost(BlogVO blog, CategoryVO category, PostVO post, Model model) {
        blog.setUser(blogService.getBlog(blog).getUser());
        category.setBlog(blog);
        post.setCategory(category);
        postService.updatePost(post);

        model.addAttribute("blogId", blog.getBlogId());

        return "forward:blogMain";
    }

    @RequestMapping("/deletePost")
    public String deletePost(BlogVO blog, CategoryVO category, PostVO post, Model model) {
        postService.deletePost(post);

        BlogVO findBlog = blogService.getBlog(blog);
        findBlog.setUser(blogService.getBlog(blog).getUser());
        List<CategoryVO> categorys = categoryService.getCategorys(findBlog);

        model.addAttribute("blogId", findBlog.getBlogId());
        List<PostVO> posts;
        if(category != null && category.getCategoryId() > 0) {
            model.addAttribute("categoryId", category.getCategoryId());
            posts = postService.searchPostByCategory(findBlog, category);
        } else {
            posts = postService.getPosts(findBlog);
        }

        model.addAttribute("posts", posts);
        model.addAttribute("blog", findBlog);
        model.addAttribute("categorys", categorys);

        return "blogmain";
    }
}
