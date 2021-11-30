package com.fastcampus.jblog.biz.category;

import com.fastcampus.jblog.biz.blog.BlogVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryVO {
    private int categoryId;
    private BlogVO blog;
    private String categoryName;
    private String displayType;
    private int cntDisplayPost;
    private String description;
    private Date createdDate;
    private Date modifiedDate;

    public static CategoryVO entityToVO(Category category) {
        return CategoryVO.builder()
                .categoryId(category.getCategoryId())
                .blog(BlogVO.entityToVO(category.getBlog()))
                .categoryName(category.getCategoryName())
                .displayType(category.getDisplayType())
                .cntDisplayPost(category.getCntDisplayPost())
                .description(category.getDescription())
                .createdDate(category.getCreatedDate())
                .modifiedDate(category.getModifiedDate())
                .build();
    }

    public static List<CategoryVO> entityToVOList(List<Category> categories) {
        List<CategoryVO> categoryVOList = new ArrayList<>();

        for(Category category : categories) {
            CategoryVO categoryVO = CategoryVO.builder()
                                    .categoryId(category.getCategoryId())
                                    .blog(BlogVO.entityToVO(category.getBlog()))
                                    .categoryName(category.getCategoryName())
                                    .displayType(category.getDisplayType())
                                    .cntDisplayPost(category.getCntDisplayPost())
                                    .description(category.getDescription())
                                    .createdDate(category.getCreatedDate())
                                    .modifiedDate(category.getModifiedDate())
                                    .build();
            categoryVOList.add(categoryVO);
        }

        return categoryVOList;
    }

    public static Category voToEntity(CategoryVO categoryVO) {
        return Category.builder()
                .categoryId(categoryVO.getCategoryId())
                .blog(BlogVO.voToEntity(categoryVO.getBlog()))
                .categoryName(categoryVO.getCategoryName())
                .displayType(categoryVO.getDisplayType())
                .cntDisplayPost(categoryVO.getCntDisplayPost())
                .description(categoryVO.getDescription())
                .createdDate(categoryVO.getCreatedDate())
                .modifiedDate(categoryVO.getModifiedDate())
                .build();
    }
}
