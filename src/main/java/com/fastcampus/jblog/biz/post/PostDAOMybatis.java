package com.fastcampus.jblog.biz.post;

import com.fastcampus.jblog.biz.blog.BlogVO;
import com.fastcampus.jblog.biz.category.CategoryVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostDAOMybatis implements PostDAO {

    @Autowired
    private SqlSessionTemplate mybatis;

    @Override
    public PostVO getPost(PostVO post) {
        return mybatis.selectOne("PostDAO.getPost", post);
    }

    @Override
    public List<PostVO> getPosts(BlogVO blog) {
        return mybatis.selectList("PostDAO.getPosts", blog);
    }

    @Override
    public void insertPost(PostVO post) {
        mybatis.insert("PostDAO.insertPost", post);
    }

    @Override
    public void updatePost(PostVO post) {
        mybatis.update("PostDAO.updatePost", post);
    }

    @Override
    public void deletePost(PostVO post) {
        mybatis.delete("PostDAO.deletePost", post);
    }

    @Override
    public List<PostVO> searchPostByCategory(CategoryVO category) {
        return mybatis.selectList("PostDAO.searchPostByCategory", category);
    }
}
