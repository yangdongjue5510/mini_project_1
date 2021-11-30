package com.fastcampus.jblog.biz.post;

import com.fastcampus.jblog.biz.blog.BlogDAO;
import com.fastcampus.jblog.biz.blog.BlogVO;
import com.fastcampus.jblog.biz.category.CategoryDAO;
import com.fastcampus.jblog.biz.category.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDAO postDAO;

    @Override
    public PostVO getPost(PostVO post) {
        return postDAO.getPost(post);
    }

    @Override
    public List<PostVO> getPosts(BlogVO blog) {
        return postDAO.getPosts(blog);
    }

    @Override
    public void insertPost(PostVO post) {
        postDAO.insertPost(post);
    }

    @Override
    public void updatePost(PostVO post) {
        postDAO.updatePost(post);
    }

    @Override
    public void deletePost(PostVO post) {
        postDAO.deletePost(post);
    }

    @Override
    public List<PostVO> searchPostByCategory(BlogVO blog, CategoryVO category) {
        category.setBlog(blog);
        return postDAO.searchPostByCategory(category);
    }
}
