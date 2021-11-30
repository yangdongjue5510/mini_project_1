package com.fastcampus.jblog.biz.category;

import com.fastcampus.jblog.biz.blog.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByBlog(Blog blog);
}
