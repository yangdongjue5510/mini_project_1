package com.fastcampus.jblog.biz.post;

import com.fastcampus.jblog.biz.blog.BlogVO;
import com.fastcampus.jblog.biz.category.CategoryVO;

import java.util.List;

public interface PostDAO {
    PostVO getPost(PostVO post);
    List<PostVO> getPosts(BlogVO blog);
    void insertPost(PostVO post);
    void updatePost(PostVO post);
    void deletePost(PostVO post);
    List<PostVO> searchPostByCategory(CategoryVO categoryVO);
}
