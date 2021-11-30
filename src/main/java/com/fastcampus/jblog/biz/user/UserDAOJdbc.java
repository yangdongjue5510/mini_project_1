package com.fastcampus.jblog.biz.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.fastcampus.jblog.common.JDBCUtil;

public class UserDAOJdbc implements UserDAO {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	private String USER_GET = "select * from blog_user where id = ? and password = ?";
	
	@Override
	public UserVO getUser(UserVO user) {
		UserVO findUser = new UserVO();

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_GET);
			stmt.setString(1, user.getId());
			stmt.setString(2, user.getPassword());
			rs = stmt.executeQuery();
			if(rs.next()) {
				findUser.setUserId(rs.getInt("USER_ID"));
				findUser.setPassword(rs.getString("PASSWORD"));
				findUser.setUserName(rs.getString("USER_NAME"));
				findUser.setRole(rs.getString("ROLE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}

		return findUser;
	}
}
