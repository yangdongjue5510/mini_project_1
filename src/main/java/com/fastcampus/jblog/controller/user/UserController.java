package com.fastcampus.jblog.controller.user;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fastcampus.jblog.biz.blog.BlogService;
import com.fastcampus.jblog.biz.blog.BlogVO;
import com.fastcampus.jblog.biz.user.UserService;
import com.fastcampus.jblog.biz.user.UserVO;

@Controller
public class UserController {
	
	@Autowired
	private UserService userSerivce;
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping("/loginView")
	public String loginView() {
		return "bloglogin";
	}
	
	@RequestMapping("/login")
	public String login(UserVO user, HttpSession session, Model model) {
		UserVO findUser = userSerivce.getUser(user);
		if(!UserVO.isEmpty(findUser)) {
			// 로그인 성공한 경우 세션에 사용자 정보를 저장한다.
			session.setAttribute("user", findUser);
			
			// 로그인 성공한 사용자가 블로그를 소유한 사용자인지 조회하여 Model에 등록한다.
			BlogVO userBlog = blogService.getUserBlog(findUser);
			model.addAttribute("userBlog", userBlog);

			// 블로그 리스트 조회
			List<BlogVO> blogs = blogService.getBlogs();
			model.addAttribute("blogs", blogs);
		}
		return "forward:/";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();

		return "redirect:/";
	}
}
