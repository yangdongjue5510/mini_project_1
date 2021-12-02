package com.yang.service;

import com.yang.db.BlogDAO;
import com.yang.db.CategoryDAO;
import com.yang.db.PostDAO;
import com.yang.db.UserDAO;
import com.yang.domain.BlogVO;
import com.yang.domain.CategoryVO;
import com.yang.domain.PostVO;
import com.yang.domain.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService{
    @Autowired
    BlogDAO blogDAO;

    @Autowired
    CategoryDAO categoryDAO;

    @Autowired
    PostDAO postDAO;

    @Autowired
    PostService postService;

    @Autowired
    UserDAO userDAO;



    @Override
    public List<BlogVO> getBlogList() {
        List<BlogVO> list = blogDAO.getBlogList();
        return list;
    }

    @Override
    public BlogVO getBlog(int userId) {
        return blogDAO.getBlog(userId);
    }

    @Transactional
    @Override
    public void insertBlog(String blogTitle, UserVO vo) {
        blogDAO.insertBlog(blogTitle, vo);
        int userId = vo.getUserId();
        CategoryVO category = new CategoryVO();
        category.setCategoryName("분류없음");
        category.setBlogId(userId);
        category.setDisplayType("제목+내용");
        category.setCntDisplayPost(5);
        categoryDAO.insertCategory(category);
    }

    @Override
    public List<BlogVO> searchBlog(String searchCondition, String searchKeyword) {
        return blogDAO.searchBlog(searchCondition, searchKeyword);

    }

    public List<BlogVO> searchBlogByUserName(String searchKeyword) {
        return userDAO.searchUserByUserName(searchKeyword).stream()
                .map(user -> blogDAO.getBlog(user.getUserId()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBlog(int blogId) {
        blogDAO.deleteBlog(blogId);
    }

    @Override
    public void updateBlog(BlogVO vo) {
        blogDAO.updateBlog(vo);
    }

    @Override
    public void forwardBlogView(int blogId, Model model) {
        BlogVO blog = getBlog(blogId);
        CategoryVO category = (CategoryVO) model.getAttribute("category");

        model.addAttribute("blogWriter", userDAO.getUserByBlogId(blogId));
        model.addAttribute("blog", blog);
        model.addAttribute("categoryList", categoryDAO.getCategoryList(blog));
        model.addAttribute("postList", postService.getPostListBySearch(blogId, category));
    }
}
