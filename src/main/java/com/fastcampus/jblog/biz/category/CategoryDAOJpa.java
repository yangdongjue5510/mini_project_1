package com.fastcampus.jblog.biz.category;

import com.fastcampus.jblog.biz.blog.BlogVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryDAOJpa implements CategoryDAO {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryVO> getCategorys(BlogVO blogVO) {
        return CategoryVO.entityToVOList(categoryRepository.findByBlog(BlogVO.voToEntity(blogVO)));
    }

    @Override
    public CategoryVO getCategory(CategoryVO categoryVO) {
        return CategoryVO.entityToVO(categoryRepository.findById(categoryVO.getCategoryId()).get());
    }

    @Override
    public void insertCategory(CategoryVO categoryVO) {
        Category category = CategoryVO.voToEntity(categoryVO);
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(CategoryVO categoryVO) {
        categoryRepository.delete(CategoryVO.voToEntity(categoryVO));
    }

    @Override
    public void updateCategory(CategoryVO categoryVO) {
        Category category = CategoryVO.voToEntity(categoryVO);
        categoryRepository.save(category);
    }
}
