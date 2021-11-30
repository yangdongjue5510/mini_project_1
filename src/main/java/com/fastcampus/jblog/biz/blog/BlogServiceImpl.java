package com.fastcampus.jblog.biz.blog;

import java.util.List;

import com.fastcampus.jblog.biz.category.CategoryDAO;
import com.fastcampus.jblog.biz.category.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;

import com.fastcampus.jblog.biz.user.UserVO;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogDAO blogDAO;

	@Autowired
	private CategoryDAO categoryDAO;

	@Override
	public BlogVO getUserBlog(UserVO user) { return blogDAO.getUserBlog(user); }

	@Override
	public List<BlogVO> getBlogs() {
		return blogDAO.getBlogs();
	}

	@Override
	public BlogVO getBlog(BlogVO blog) {
		return blogDAO.getBlog(blog);
	}

	@Override
	public List<BlogVO> searchBlogs(SearchVO search) {
		if("".equals(search.getSearchValue())) {
			return blogDAO.getBlogs();
		} else {
			if("title".equalsIgnoreCase(search.getSearchType())) {
				return blogDAO.searchBlogByTitle(search);
			} else if("tag".equalsIgnoreCase(search.getSearchType())) {
				return blogDAO.searchBlogByTag(search);
			} else if("blogger".equalsIgnoreCase(search.getSearchType())) {
				return blogDAO.searchBlogByUser(search);
			} else {
				return blogDAO.getBlogs();
			}
		}
	}

	@Override
	public void createDefaultBlog(BlogVO blog) {
		// 블로그 기본값 셋팅 및 생성
		blog.setTag("");
		blog.setCntDisplayPost(0);
		blog.setStatus("운영");
		blogDAO.createBlog(blog);
		BlogVO newBlog = getUserBlog(blog.getUser());

		// 카테고리 기본값 셋팅 및 생성
		CategoryVO newCategory = new CategoryVO();
		newCategory.setCategoryName("미분류");
		newCategory.setDisplayType("all");
		newCategory.setCntDisplayPost(0);
		newCategory.setDescription("모든 글을 등록할 수 있는 기본 카테고리입니다.");
		newCategory.setBlog(newBlog);
		categoryDAO.insertCategory(newCategory);
	}

	@Override
	public void updateBlog(BlogVO blog) {
		blogDAO.updateBlog(blog);
	}

	@Override
	public void deleteBlog(BlogVO blog) {
		blogDAO.deleteBlog(blog);
	}

	@Override
	public void deleteRequest(BlogVO blog) {
		blogDAO.deleteRequest(blog);
	}

}
