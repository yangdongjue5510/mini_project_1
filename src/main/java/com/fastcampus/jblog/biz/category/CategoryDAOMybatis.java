package com.fastcampus.jblog.biz.category;

import com.fastcampus.jblog.biz.blog.BlogVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryDAOMybatis implements CategoryDAO {

    @Autowired
    private SqlSessionTemplate mybatis;

    @Override
    public List<CategoryVO> getCategorys(BlogVO blog) {
        return mybatis.selectList("categoryDAO.getCategorys", blog);
    }

    @Override
    public CategoryVO getCategory(CategoryVO category) {
        return mybatis.selectOne("categoryDAO.getCategory", category);
    }

    @Override
    public void insertCategory(CategoryVO category) {
        mybatis.insert("categoryDAO.insertCategory", category);
    }

    @Override
    public void deleteCategory(CategoryVO category) {
        mybatis.delete("categoryDAO.deleteCategory", category);
    }

    @Override
    public void updateCategory(CategoryVO category) {
        mybatis.update("categoryDAO.updateCategory", category);
    }
}
