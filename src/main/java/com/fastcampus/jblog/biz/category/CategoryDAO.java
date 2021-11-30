package com.fastcampus.jblog.biz.category;

import com.fastcampus.jblog.biz.blog.BlogVO;
import com.fastcampus.jblog.biz.user.UserVO;

import java.util.List;

public interface CategoryDAO {
    List<CategoryVO> getCategorys(BlogVO blog);
    CategoryVO getCategory(CategoryVO category);
    void insertCategory(CategoryVO category);
    void deleteCategory(CategoryVO category);
    void updateCategory(CategoryVO category);
}
