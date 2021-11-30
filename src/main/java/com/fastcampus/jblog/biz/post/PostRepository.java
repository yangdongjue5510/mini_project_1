package com.fastcampus.jblog.biz.post;

import com.fastcampus.jblog.biz.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query("SELECT P, C FROM Post P INNER JOIN Category C ON P.category.categoryId = C.categoryId INNER JOIN Blog B ON C.blog.blogId = B.blogId INNER JOIN User U ON U.userId = B.user.userId WHERE B.blogId = :blogId ORDER BY P.createdDate DESC")
    List<Post> findByBlog(int blogId);
    List<Post> findByCategory(Category category);
}
