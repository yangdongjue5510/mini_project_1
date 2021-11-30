package com.fastcampus.jblog.biz.post;

import com.fastcampus.jblog.biz.blog.BlogVO;
import com.fastcampus.jblog.biz.category.Category;
import com.fastcampus.jblog.biz.category.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostDAOJpa implements PostDAO {

    @Autowired
    private PostRepository postRepository;

    @Override
    public PostVO getPost(PostVO post) {
        return PostVO.entityToVO(postRepository.findById(post.getPostId()).get());
    }

    @Override
    public List<PostVO> getPosts(BlogVO blog) {
        return PostVO.entityToVOList(postRepository.findByBlog(blog.getBlogId()));
    }

    @Override
    public void insertPost(PostVO postVO) {
        Post post = PostVO.voToEntity(postVO);
        postRepository.save(post);
    }

    @Override
    public void updatePost(PostVO postVO) {
        Post post = PostVO.voToEntity(postVO);
        postRepository.save(post);
    }

    @Override
    public void deletePost(PostVO post) {
        postRepository.deleteById(post.getPostId());
    }

    @Override
    public List<PostVO> searchPostByCategory(CategoryVO categoryVO) {
        return PostVO.entityToVOList(postRepository.findByCategory(CategoryVO.voToEntity(categoryVO)));
    }
}
