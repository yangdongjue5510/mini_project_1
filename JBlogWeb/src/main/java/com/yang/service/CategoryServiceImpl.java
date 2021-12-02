package com.yang.service;

import com.yang.db.CategoryDAO;
import com.yang.db.PostDAO;
import com.yang.domain.BlogVO;
import com.yang.domain.CategoryVO;
import com.yang.domain.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryDAO categoryDAO;

    @Autowired
    PostDAO postDAO;

    @Override
    public void insertCategory(CategoryVO vo) {
        categoryDAO.insertCategory(vo);
    }

    @Override
    public List<CategoryVO> getCategoryList(BlogVO vo) {
        return categoryDAO.getCategoryList(vo);
    }

    @Override
    public void deleteCategory(int categoryId) {
        categoryDAO.deleteCategory(categoryId);
    }

    @Override
    public void updateCategory(CategoryVO vo) {
        categoryDAO.updateCategory(vo);
    }

    @Override
    public CategoryVO getCategory(int categoryId) {
        return categoryDAO.getCategory(categoryId);
    }


}
