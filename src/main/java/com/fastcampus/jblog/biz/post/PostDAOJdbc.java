package com.fastcampus.jblog.biz.post;

import com.fastcampus.jblog.biz.blog.BlogVO;
import com.fastcampus.jblog.biz.category.CategoryVO;
import com.fastcampus.jblog.common.JDBCUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PostDAOJdbc implements PostDAO {

    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    private static final String POST = "SELECT * FROM POST WHERE POST_ID = ?";
    private static final String POST_LIST = "SELECT P.*, C.* FROM POST P INNER JOIN CATEGORY C ON P.CATEGORY_ID = C.CATEGORY_ID INNER JOIN BLOG B INNER JOIN BLOG_USER U ON U.USER_ID = B.USER_ID ON C.BLOG_ID = B.BLOG_ID WHERE B.BLOG_ID = ? ORDER BY P.CREATED_DATE DESC";
    private static final String INSERT_POST = "INSERT INTO POST(CATEGORY_ID, TITLE, CONTENT, CREATED_DATE) VALUES (?, ?, ?, SYSDATE)";
    private static final String UPDATE_POST = "UPDATE POST SET CATEGORY_ID = ?, TITLE = ?, CONTENT = ? WHERE POST_ID = ?";
    private static final String DELETE_POST = "DELETE FROM POST WHERE POST_ID = ?";
    private static final String SEARCH_POST_BY_CATEGORY = "SELECT P.*, C.* FROM POST P INNER JOIN CATEGORY C ON P.CATEGORY_ID = C.CATEGORY_ID INNER JOIN BLOG B INNER JOIN BLOG_USER U ON U.USER_ID = B.USER_ID ON C.BLOG_ID = B.BLOG_ID WHERE B.BLOG_ID = ? AND C.CATEGORY_ID = ? ORDER BY P.CREATED_DATE DESC";

    @Override
    public PostVO getPost(PostVO post) {
        PostVO findPost = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(POST);
            stmt.setInt(1, post.getPostId());
            rs = stmt.executeQuery();
            while(rs.next()) {
                findPost = new PostVO();
                CategoryVO category = new CategoryVO();
                category.setCategoryId(rs.getInt("CATEGORY_ID"));
                findPost.setPostId(rs.getInt("POST_ID"));
                findPost.setTitle(rs.getString("TITLE"));
                findPost.setContent(rs.getString("CONTENT"));
                findPost.setCreatedDate(rs.getDate("CREATED_DATE"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }

        return findPost;
    }

    @Override
    public List<PostVO> getPosts(BlogVO blog) {
        List<PostVO> list = new ArrayList<>();

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(POST_LIST);
            stmt.setInt(1, blog.getBlogId());
            rs = stmt.executeQuery();
            while(rs.next()) {
                PostVO post = new PostVO();
                CategoryVO category = new CategoryVO();
                category.setCategoryId(rs.getInt("CATEGORY_ID"));
                category.setDisplayType(rs.getString("DISPLAY_TYPE"));
                post.setPostId(rs.getInt("POST_ID"));
                post.setCategory(category);
                post.setTitle(rs.getString("TITLE"));
                post.setContent(rs.getString("CONTENT"));
                post.setCreatedDate(rs.getDate("CREATED_DATE"));
                post.setCategory(category);
                list.add(post);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }

        return list;
    }

    @Override
    public void insertPost(PostVO post) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(INSERT_POST);
            stmt.setInt(1, post.getCategory().getCategoryId());
            stmt.setString(2, post.getTitle());
            stmt.setString(3, post.getContent());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    @Override
    public void updatePost(PostVO post) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(UPDATE_POST);
            stmt.setInt(1, post.getCategory().getCategoryId());
            stmt.setString(2, post.getTitle());
            stmt.setString(3, post.getContent());
            stmt.setInt(4, post.getPostId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    @Override
    public void deletePost(PostVO post) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(DELETE_POST);
            stmt.setInt(1, post.getPostId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    @Override
    public List<PostVO> searchPostByCategory(CategoryVO category) {
        List<PostVO> list = new ArrayList<>();

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(SEARCH_POST_BY_CATEGORY);
            stmt.setInt(1, category.getBlog().getBlogId());
            stmt.setInt(2, category.getCategoryId());
            rs = stmt.executeQuery();
            while(rs.next()) {
                PostVO post = new PostVO();
                CategoryVO postCategory = new CategoryVO();
                postCategory.setCategoryId(rs.getInt("CATEGORY_ID"));
                postCategory.setDisplayType(rs.getString("DISPLAY_TYPE"));
                post.setPostId(rs.getInt("POST_ID"));
                post.setCategory(postCategory);
                post.setTitle(rs.getString("TITLE"));
                post.setContent(rs.getString("CONTENT"));
                post.setCreatedDate(rs.getDate("CREATED_DATE"));

                post.setCategory(postCategory);
                list.add(post);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }

        return list;
    }
}
