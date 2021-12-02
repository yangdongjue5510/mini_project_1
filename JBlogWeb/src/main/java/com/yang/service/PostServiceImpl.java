package com.yang.service;

import com.yang.db.PostDAO;
import com.yang.domain.CategoryVO;
import com.yang.domain.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    PostDAO postDAO;

    @Override
    public void insertPost(PostVO vo) {
        postDAO.insertPost(vo);
    }

    @Override
    public List<PostVO> getPostByCategoryId(int categoryId) {
        return postDAO.getPostByCategoryId(categoryId);
    }

    @Override
    public List<PostVO> getPost(int blogId) {
        return postDAO.getPost(blogId);
    }

    @Override
    public void updatePost(PostVO post) {
        postDAO.updatePost(post);
    }

    @Override
    public void deletePost(int postId) {
        postDAO.deletePost(postId);
    }

    @Override
    public List<PostVO> getPostListBySearch(int blogId, CategoryVO category) {
        List<PostVO> postList = null;
        if (category == null) {
            postList = postDAO.getPost(blogId);
        }
        else if (category.getCategoryName() != null) {
            postList = postDAO.getPostByCategoryId(category.getCategoryId());
        }
        return postList;
    }
}
