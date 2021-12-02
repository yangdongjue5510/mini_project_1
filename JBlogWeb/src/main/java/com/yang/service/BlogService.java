package com.yang.service;

import com.yang.domain.BlogVO;
import com.yang.domain.UserVO;
import org.springframework.ui.Model;

import java.util.List;

public interface BlogService {
    List<BlogVO> getBlogList();

    BlogVO getBlog(int userId);

    void insertBlog(String blogTitle, UserVO vo);

    List<BlogVO> searchBlog(String searchCondition, String searchKeyword);

    List<BlogVO> searchBlogByUserName(String searchKeyword);

    void deleteBlog(int blogId);

    void updateBlog(BlogVO vo);

    void forwardBlogView(int blogId, Model model);
}
