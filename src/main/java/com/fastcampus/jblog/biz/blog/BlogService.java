package com.fastcampus.jblog.biz.blog;

import java.util.List;

import com.fastcampus.jblog.biz.user.UserVO;

public interface BlogService {
    BlogVO getUserBlog(UserVO user);
	List<BlogVO> getBlogs();
    BlogVO getBlog(BlogVO blog);
    List<BlogVO> searchBlogs(SearchVO search);
    void createDefaultBlog(BlogVO blog);
    void updateBlog(BlogVO blog);
    void deleteBlog(BlogVO blog);
    void deleteRequest(BlogVO blog);
}
