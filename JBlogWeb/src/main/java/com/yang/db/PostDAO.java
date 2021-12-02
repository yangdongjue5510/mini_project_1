package com.yang.db;

import com.yang.common.JDBCUtil;
import com.yang.domain.PostVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class PostDAO {
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    private String INSERT_POST = "INSERT INTO POST "
            + "(POST_ID, CATEGORY_ID, TITLE, CONTENT, CREATED_DATE)"
            + "VALUES ((select nvl(max(POST_ID), 0) +1 from POST), ?, ?, ?, CURRENT_TIMESTAMP())";
    private String GET_POST = "SELECT * FROM POST WHERE CATEGORY_ID = ? ORDER BY CREATED_DATE DESC";
    private String GET_POST_JOIN = "SELECT DISTINCT POST_ID, CONTENT, POST.CATEGORY_ID, TITLE, POST.CREATED_DATE " +
            "FROM POST JOIN (SELECT DISTINCT * FROM CATEGORY WHERE CATEGORY.BLOG_ID = ?) A" +
            " WHERE POST.CATEGORY_ID = A.CATEGORY_ID ORDER BY POST.CREATED_DATE DESC";
    private String DELETE_POST = "DELETE FROM POST WHERE POST_ID = ?";
    private String UPDATE_POST = "UPDATE POST SET TITLE = ?, CONTENT = ?, CATEGORY_ID = ? WHERE POST_ID = ?";

    public void insertPost(PostVO vo) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(INSERT_POST);
            stmt.setInt(1, vo.getCategoryId());
            stmt.setString(2, vo.getTitle());
            stmt.setString(3, vo.getContent());

            int affectedRows = stmt.executeUpdate();
            log.info("InsertPost executed. {} rows affected.", affectedRows);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    public void updatePost(PostVO vo) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(UPDATE_POST);
            stmt.setString(1, vo.getTitle());
            stmt.setString(2, vo.getContent());
            stmt.setInt(3, vo.getCategoryId());
            stmt.setInt(4, vo.getPostId());
            int affectedRows = stmt.executeUpdate();
            log.info("updatePost executed. {} rows affected.", affectedRows);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    public List<PostVO> getPost(int blogId) {
        List<PostVO> list = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(GET_POST_JOIN);
            stmt.setInt(1, blogId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(setPostVO());
            }
            log.info("getPost executed.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
        return list;
    }

    public List<PostVO> getPostByCategoryId(int categoryId) {
        List<PostVO> list = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(GET_POST);
            stmt.setInt(1, categoryId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(setPostVO());
            }
            log.info("getPostByCategoryId executed.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
        return list;
    }

    public void deletePost(int postId) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(DELETE_POST);
            stmt.setInt(1, postId);
            int affectedRows = stmt.executeUpdate();
            log.info("deletePost executed. {} rows affected.", affectedRows);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    public PostVO setPostVO() throws SQLException{
        PostVO post = new PostVO();
        post.setTitle(rs.getString("TITLE"));
        post.setPostId(rs.getInt("POST_ID"));
        post.setContent(rs.getString("CONTENT"));
        post.setCreatedDate(rs.getDate("CREATED_DATE"));
        post.setCategoryId(rs.getInt("CATEGORY_ID"));
        return post;
    }
}
