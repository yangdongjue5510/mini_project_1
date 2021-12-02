package com.yang.service;

import com.yang.domain.BlogVO;
import com.yang.domain.CategoryVO;
import com.yang.domain.PostVO;

import java.util.List;

public interface CategoryService {
    void insertCategory(CategoryVO vo);

    List<CategoryVO> getCategoryList(BlogVO vo);

    void deleteCategory(int categoryId);

    void updateCategory(CategoryVO vo);

    CategoryVO getCategory(int categoryId);


}
