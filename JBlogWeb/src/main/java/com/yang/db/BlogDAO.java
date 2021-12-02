package com.yang.db;

import com.yang.common.JDBCUtil;
import com.yang.domain.BlogVO;
import com.yang.domain.UserVO;
import com.yang.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Repository
public class BlogDAO {
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    private final String GET_BLOG_LIST = "SELECT * FROM BLOG";
    private final String GET_BLOG_BY_USER_ID = "SELECT * FROM BLOG WHERE USER_ID = ?";
    private final String INSERT_BLOG = "INSERT INTO BLOG (BLOG_ID, TITLE, TAG, CNT_DISPLAY_POST, STATUS, USER_ID)"
            + "VALUES (?, ?, ?, ?, ?, ?)";
    private final String SEARCH_BLOG_WHERE = "SELECT * FROM BLOG WHERE ";
    private final String SEARCH_BLOG_ILIKE = " ILIKE ?";
    private final String DELETE_BLOG = "DELETE FROM BLOG WHERE BLOG_ID = ?";
    private final String UPDATE_BLOG = "UPDATE BLOG SET TITLE = ?, TAG = ?, CNT_DISPLAY_POST = ?  "
            + "WHERE BLOG_ID = ?";

    public List<BlogVO> getBlogList() {
        List<BlogVO> list = new ArrayList<>();
        conn = JDBCUtil.getConnection();
        try {
            stmt = conn.prepareStatement(GET_BLOG_LIST);
            rs = stmt.executeQuery();
            while (rs.next()){
                BlogVO blog = setBlogVO();
                list.add(blog);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
        return list;
    }

    public BlogVO getBlog(int userId) {
        BlogVO blog = null;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(GET_BLOG_BY_USER_ID);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();

            while (rs.next()){
                blog = setBlogVO();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
        return blog;
    }

    public void insertBlog(String blogTitle, UserVO vo) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(INSERT_BLOG);
            stmt.setInt(1, vo.getUserId());
            stmt.setString(2, blogTitle);
            stmt.setString(3, "Welcome to JBlog.");
            stmt.setInt(4, 5);
            stmt.setString(5, "OPEN");  //운영 대신 OPEN.
            stmt.setInt(6, vo.getUserId());
            int affectedRows = stmt.executeUpdate();
            log.info("insertBlog query execute. {} rows affected", affectedRows);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    public List<BlogVO> searchBlog(String searchCondition, String searchKeyword) {
        String SEARCH_BLOG = SEARCH_BLOG_WHERE + searchCondition +SEARCH_BLOG_ILIKE;
        List<BlogVO> blogList = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(SEARCH_BLOG);
            stmt.setString(1, "%"+searchKeyword+"%");
            rs = stmt.executeQuery();
            while (rs.next()){
                BlogVO blog = setBlogVO();
                blogList.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
        return blogList;
    }

    public void deleteBlog(int blogId) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(DELETE_BLOG);
            stmt.setInt(1, blogId);
            int affectedRows = stmt.executeUpdate();
            log.info("deleteBlog executed. {} affected", affectedRows);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    public void updateBlog(BlogVO vo) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(UPDATE_BLOG);
            stmt.setString(1, vo.getTitle());
            stmt.setString(2, vo.getTag());
            stmt.setInt(3, vo.getCntDisplayPost());
            stmt.setInt(4, vo.getBlogId());
            int affectedRows = stmt.executeUpdate();
            log.info("updateBlog executed. {} rows affected", affectedRows);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    public BlogVO setBlogVO() throws SQLException {
        BlogVO blog = new BlogVO();
        blog.setBlogId(rs.getInt("BLOG_ID"));
        blog.setUserId(rs.getInt("USER_ID"));
        blog.setStatus(rs.getString("STATUS"));
        blog.setTag(rs.getString("TAG"));
        blog.setTitle(rs.getString("TITLE"));
        blog.setCntDisplayPost(rs.getInt("CNT_DISPLAY_POST"));
        return blog;
    }

}
