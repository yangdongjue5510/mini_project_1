package com.yang.service;

import com.yang.domain.CategoryVO;
import com.yang.domain.PostVO;

import java.util.List;

public interface PostService {
    void insertPost(PostVO vo);

    List<PostVO> getPostByCategoryId(int categoryId);

    List<PostVO> getPost(int blogId);

    void updatePost(PostVO post);

    void deletePost(int postId);

    List<PostVO> getPostListBySearch(int blogId, CategoryVO category);
}
