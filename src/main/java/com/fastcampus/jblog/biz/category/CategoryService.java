package com.fastcampus.jblog.biz.category;

import com.fastcampus.jblog.biz.blog.BlogVO;

import java.util.List;

public interface CategoryService {
    List<CategoryVO> getCategorys(BlogVO blog);
    CategoryVO getCategory(CategoryVO category);
    void insertCategory(CategoryVO category, BlogVO blog);
    void deleteCategory(CategoryVO category);
    void updateCategory(CategoryVO category);
}
