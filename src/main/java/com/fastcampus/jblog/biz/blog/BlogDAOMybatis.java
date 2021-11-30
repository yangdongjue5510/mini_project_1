package com.fastcampus.jblog.biz.blog;

import com.fastcampus.jblog.biz.user.UserVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BlogDAOMybatis implements BlogDAO {

    @Autowired
    private SqlSessionTemplate mybatis;

    @Override
    public BlogVO getUserBlog(UserVO user) {
        return mybatis.selectOne("BlogDAO.getUserBlog", user);
    }

    @Override
    public List<BlogVO> getBlogs() {
        return mybatis.selectList("BlogDAO.getBlogs");
    }

    @Override
    public BlogVO getBlog(BlogVO blog) {
        return mybatis.selectOne("BlogDAO.getBlog", blog);
    }

    @Override
    public List<BlogVO> searchBlogByTitle(SearchVO search) {
        return mybatis.selectList("BlogDAO.searchBlogByTitle", search);
    }

    @Override
    public List<BlogVO> searchBlogByTag(SearchVO search) {
        return mybatis.selectList("BlogDAO.searchBlogByTag", search);
    }

    @Override
    public List<BlogVO> searchBlogByUser(SearchVO search) {
        return mybatis.selectList("BlogDAO.searchBlogByUser", search);
    }

    @Override
    public void createBlog(BlogVO blog) {
        mybatis.insert("BlogDAO.createBlog", blog);
    }

    @Override
    public void updateBlog(BlogVO blog) {
        mybatis.update("BlogDAO.updateBlog", blog);
    }

    @Override
    public void deleteBlog(BlogVO blog) {
        mybatis.delete("BlogDAO.deleteBlog", blog);
    }

    @Override
    public void deleteRequest(BlogVO blog) {
        mybatis.delete("BlogDAO.deleteRequest", blog);
    }
}
