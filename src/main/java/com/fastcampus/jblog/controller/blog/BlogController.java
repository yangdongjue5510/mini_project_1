package com.fastcampus.jblog.controller.blog;

import com.fastcampus.jblog.biz.blog.BlogService;
import com.fastcampus.jblog.biz.blog.BlogVO;
import com.fastcampus.jblog.biz.blog.SearchVO;
import com.fastcampus.jblog.biz.category.CategoryService;
import com.fastcampus.jblog.biz.category.CategoryVO;
import com.fastcampus.jblog.biz.post.PostService;
import com.fastcampus.jblog.biz.post.PostVO;
import com.fastcampus.jblog.biz.user.UserService;
import com.fastcampus.jblog.biz.user.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private PostService postService;

	@RequestMapping("/createBlogView")
	public String createBlogView() {
		return "blogcreate";
	}

	@RequestMapping("/createBlog")
	public String createBlog(BlogVO blog, HttpSession session) {
		UserVO user =  (UserVO)session.getAttribute("user");
		blog.setUser(user);
		blogService.createDefaultBlog(blog);
		return "redirect:/";
	}

	@RequestMapping("/searchBlog")
	public String searchBlog(SearchVO search, Model model) {
		List<BlogVO> blogs = blogService.searchBlogs(search);
		model.addAttribute("blogs", blogs);
		return "forward:/index.jsp";
	}

	@RequestMapping("/blogMain")
	public String blogMain(BlogVO blog, Model model) {
		BlogVO findBlog = blogService.getBlog(blog);
		List<CategoryVO> categorys = categoryService.getCategorys(findBlog);
		List<PostVO> posts = postService.getPosts(findBlog);

		model.addAttribute("blog", findBlog);
		model.addAttribute("categorys", categorys);
		model.addAttribute("posts", posts);

		return "blogmain";
	}

	@RequestMapping("/searchPostByCategory")
	public String searchPostByCategory(BlogVO blog, CategoryVO category, Model model) {
		BlogVO findBlog = blogService.getUserBlog(blogService.getBlog(blog).getUser());
		List<CategoryVO> categorys = categoryService.getCategorys(findBlog);
		List<PostVO> posts = postService.searchPostByCategory(findBlog, category);

		model.addAttribute("blog", findBlog);
		model.addAttribute("categoryId", category.getCategoryId());
		model.addAttribute("categorys", categorys);
		model.addAttribute("posts", posts);

		return "blogmain";
	}

	@RequestMapping("/blogAdmin")
	public String blogAdmin(BlogVO blog, Model model) {
		BlogVO findBlog = blogService.getUserBlog(blogService.getBlog(blog).getUser());

		model.addAttribute("blog", findBlog);

		return "blogadmin_basic";
	}

	@RequestMapping("/updateBlog")
	public String updateBlog(BlogVO blog, Model model) {
		blog.setUser(blogService.getBlog(blog).getUser());
		blogService.updateBlog(blog);

		BlogVO findBlog = blogService.getBlog(blog);
		List<CategoryVO> categorys = categoryService.getCategorys(findBlog);
		List<PostVO> posts = postService.getPosts(findBlog);

		model.addAttribute("blog", findBlog);
		model.addAttribute("categorys", categorys);
		model.addAttribute("posts", posts);

		return "blogmain";
	}

	@RequestMapping("/deleteBlog")
	public String deleteBlog(BlogVO blog) {
		blogService.deleteBlog(blog);

		return "redirect:/";
	}

	@RequestMapping("blogDeleteRequest")
	public String blogDeleteRequest(BlogVO blog) {
		blog.setUser(blogService.getBlog(blog).getUser());
		blogService.deleteRequest(blog);

		return "redirect:/";
	}
}
