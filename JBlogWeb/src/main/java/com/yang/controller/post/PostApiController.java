package com.yang.controller.post;

import com.yang.domain.BlogVO;
import com.yang.domain.CategoryVO;
import com.yang.domain.PostVO;
import com.yang.service.BlogService;
import com.yang.service.CategoryService;
import com.yang.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class PostApiController {

    @Autowired
    BlogService blogService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    PostService postService;

    @GetMapping("/blogAdminPost/{blogId}")
    public String blogAdminPost(@PathVariable int blogId, Model model) {

        BlogVO blog = blogService.getBlog(blogId);
        model.addAttribute("blog", blog);
        model.addAttribute("categoryList", categoryService.getCategoryList(blog));
        return "forward:/blogAdminPostView";
    }

    @GetMapping("/blogMain")
    public String blogMainByCategory(@RequestParam int blogId, @RequestParam int categoryId, Model model) {
        List<PostVO> postList = postService.getPostByCategoryId(categoryId);
        model.addAttribute("category", categoryService.getCategory(categoryId));
        blogService.forwardBlogView(blogId, model);
        return "forward:/blogMainView";
    }

    @PostMapping("/insertPost/{blogId}")
    public String insertPost(@ModelAttribute PostVO post, @PathVariable int blogId,
                             Model model) {
        postService.insertPost(post);
        blogService.forwardBlogView(blogId, model);
        return "forward:/blogMainView";
    }

    @GetMapping("/deletePost")
    public String deletePost(@RequestParam int postId, @RequestParam int blogId, @RequestParam int categoryId,
                             Model model) {
        postService.deletePost(postId);
        model.addAttribute("category", categoryService.getCategory(categoryId));
        blogService.forwardBlogView(blogId, model);
        return "forward:/blogMainView";
    }

    @GetMapping("/updatePost")
    public String updatePost(@RequestParam int postId, @RequestParam int blogId,
                             Model model) {

        BlogVO blog = blogService.getBlog(blogId);
        model.addAttribute("blog", blog);
        model.addAttribute("categoryList", categoryService.getCategoryList(blog));

        PostVO postVO = postService.getPost(blogId).stream()
                .filter(post -> post.getPostId() == postId).findAny().get();
        model.addAttribute("post", postVO);

        return "forward:/blogAdminPostView";
    }

    @PostMapping("/updatePost/{blogId}/{postId}")
    public String postUpdatePost(@ModelAttribute PostVO post, @PathVariable int blogId,
                                 @PathVariable int postId, Model model) {
        post.setPostId(postId);
        if(post.getContent() == "" || post.getTitle() == "") {
            return "redirect:/updatePost?blogId="+blogId+"&postId="+postId;
        }
        postService.updatePost(post);
        blogService.forwardBlogView(blogId, model);
        return "forward:/blogMainView";
    }
}
