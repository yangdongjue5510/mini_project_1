package com.fastcampus.jblog.biz.category;

import com.fastcampus.jblog.biz.blog.BlogVO;
import com.fastcampus.jblog.common.JDBCUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOJdbc implements CategoryDAO {
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    private static final String CATEGORY_LIST = "SELECT * FROM CATEGORY CATE INNER JOIN BLOG BLOG ON CATE.BLOG_ID = BLOG.BLOG_ID WHERE CATE.BLOG_ID = ?";
    private static final String CATEGORY = "SELECT * FROM CATEGORY WHERE CATEGORY_ID = ?";
    private static final String INSERT_CATEGORY = "INSERT INTO CATEGORY(BLOG_ID, CATEGORY_NAME, DISPLAY_TYPE, CNT_DISPLAY_POST, DESCRIPTION, CREATED_DATE, MODIFIED_DATE) VALUES (?, ?, ?, ?, ?, SYSDATE, SYSDATE)";
    private static final String DELETE_CATEGORY = "DELETE FROM CATEGORY WHERE CATEGORY_ID = ?";
    private static final String UPDATE_CATEGORY = "UPDATE CATEGORY SET CATEGORY_NAME = ?, DISPLAY_TYPE = ?, CNT_DISPLAY_POST = ?, DESCRIPTION = ? WHERE CATEGORY_ID = ?";

    @Override
    public List<CategoryVO> getCategorys(BlogVO blog) {
        List<CategoryVO> categorys = new ArrayList<>();

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(CATEGORY_LIST);
            stmt.setInt(1, blog.getBlogId());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                CategoryVO category = new CategoryVO();
                BlogVO findBlog = new BlogVO();
                category.setCategoryId(rs.getInt("CATEGORY_ID"));
                findBlog.setBlogId(rs.getInt("BLOG_ID"));
                category.setBlog(findBlog);
                category.setCategoryName(rs.getString("CATEGORY_NAME"));
                category.setDisplayType(rs.getString("DISPLAY_TYPE"));
                category.setCntDisplayPost(rs.getInt("CNT_DISPLAY_POST"));
                category.setDescription(rs.getString("DESCRIPTION"));
                category.setCreatedDate(rs.getDate("CREATED_DATE"));
                category.setModifiedDate(rs.getDate("MODIFIED_DATE"));
                categorys.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }

        return categorys;
    }

    @Override
    public CategoryVO getCategory(CategoryVO category) {
        CategoryVO findCategory = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(CATEGORY);
            stmt.setInt(1, category.getCategoryId());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                findCategory = new CategoryVO();
                BlogVO blog = new BlogVO();
                findCategory.setCategoryId(rs.getInt("CATEGORY_ID"));
                blog.setBlogId(rs.getInt("BLOG_ID"));
                findCategory.setBlog(blog);
                findCategory.setCategoryName(rs.getString("CATEGORY_NAME"));
                findCategory.setDisplayType(rs.getString("DISPLAY_TYPE"));
                findCategory.setCntDisplayPost(rs.getInt("CNT_DISPLAY_POST"));
                findCategory.setDescription(rs.getString("DESCRIPTION"));
                findCategory.setCreatedDate(rs.getDate("CREATED_DATE"));
                findCategory.setModifiedDate(rs.getDate("MODIFIED_DATE"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }

        return findCategory;
    }

    @Override
    public void insertCategory(CategoryVO category) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(INSERT_CATEGORY);
            stmt.setInt(1, category.getBlog().getBlogId());
            stmt.setString(2, category.getCategoryName());
            stmt.setString(3, category.getDisplayType());
            stmt.setInt(4, category.getCntDisplayPost());
            stmt.setString(5, category.getDescription());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    @Override
    public void deleteCategory(CategoryVO category) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(DELETE_CATEGORY);
            stmt.setInt(1, category.getCategoryId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    @Override
    public void updateCategory(CategoryVO category) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(UPDATE_CATEGORY);
            stmt.setString(1, category.getCategoryName());
            stmt.setString(2, category.getDisplayType());
            stmt.setInt(3, category.getCntDisplayPost());
            stmt.setString(4, category.getDescription());
            stmt.setInt(5, category.getCategoryId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }
}
