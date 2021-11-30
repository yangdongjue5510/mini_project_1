package com.fastcampus.jblog.biz.blog;

import com.fastcampus.jblog.biz.user.UserVO;

import java.util.List;

public interface BlogDAO {
    BlogVO getUserBlog(UserVO user);
    List<BlogVO> getBlogs();
    BlogVO getBlog(BlogVO blog);
    List<BlogVO> searchBlogByTitle(SearchVO search);
    List<BlogVO> searchBlogByTag(SearchVO search);
    List<BlogVO> searchBlogByUser(SearchVO search);
    void createBlog(BlogVO blog);
    void updateBlog(BlogVO blog);
    void deleteBlog(BlogVO blog);
    void deleteRequest(BlogVO blog);
}
