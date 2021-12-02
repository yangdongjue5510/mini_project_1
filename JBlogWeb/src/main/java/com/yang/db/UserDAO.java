package com.yang.db;

import com.yang.common.JDBCUtil;
import com.yang.domain.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAO {
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;
    private String USER_GET = "select * from blog_user where id = ? and password = ?";
    private String USER_SEARCH_BY_USERNAME = "select * from blog_user where user_name ilike ?";
    private String USER_GET_BY_BLOG_ID = "select * from blog_user where USER_ID = ?";
    public UserVO getUser(UserVO vo) {
        UserVO user = null;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(USER_GET);
            stmt.setString(1, vo.getId());
            stmt.setString(2, vo.getPassword());
            rs = stmt.executeQuery();

            while (rs.next()){
                user = setUserVO();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
        return user;
    }

    public List<UserVO> searchUserByUserName(String userName) {
        List<UserVO> list = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(USER_SEARCH_BY_USERNAME);
            stmt.setString(1, "%"+userName+"%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                UserVO user = setUserVO();
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
        return list;
    }

    public UserVO getUserByBlogId(int blogId) {
        UserVO user = null;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(USER_GET_BY_BLOG_ID);
            stmt.setInt(1, blogId);
            rs = stmt.executeQuery();

            while (rs.next()){
                user = setUserVO();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
        return user;
    }

    public UserVO setUserVO() throws SQLException {
        UserVO user = new UserVO();
        user.setId(rs.getString("ID"));
        user.setUserId(rs.getInt("USER_ID"));
        user.setPassword(rs.getString("PASSWORD"));
        user.setRole(rs.getString("ROLE"));
        user.setUserName(rs.getString("USER_NAME"));
        return user;
    }



//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    public List<UserVO> userVOList(){
//        String query = "SELECT * FROM USERS";
//        return jdbcTemplate.query(query, new BeanPropertyRowMapper<UserVO>(UserVO.class));
//    }
}
