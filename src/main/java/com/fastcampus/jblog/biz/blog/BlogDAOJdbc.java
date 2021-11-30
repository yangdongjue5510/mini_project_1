package com.fastcampus.jblog.biz.blog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fastcampus.jblog.common.JDBCUtil;
import com.fastcampus.jblog.biz.user.UserVO;
import org.springframework.stereotype.Repository;

public class BlogDAOJdbc implements BlogDAO {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	private static final String USER_BLOG = "SELECT BLOG.*, USER.* FROM BLOG_USER USER INNER JOIN BLOG BLOG ON USER.USER_ID = BLOG.USER_ID WHERE BLOG.USER_ID = ?";
	private static final String BLOG_LIST = "SELECT BLOG.*, USER.* FROM BLOG_USER USER INNER JOIN BLOG BLOG ON USER.USER_ID = BLOG.USER_ID";
	private static final String BLOG = "SELECT BLOG.*, USER.* FROM BLOG_USER USER INNER JOIN BLOG BLOG ON USER.USER_ID = BLOG.USER_ID WHERE BLOG.BLOG_ID = ?";
	private static final String SEARCH_BLOG_LIST_TITLE = "SELECT BLOG.*, USER.* FROM BLOG_USER USER INNER JOIN BLOG BLOG ON USER.USER_ID = BLOG.USER_ID WHERE BLOG.TITLE LIKE '%' || ? || '%'";
	private static final String SEARCH_BLOG_LIST_TAG = "SELECT BLOG.*, USER.* FROM BLOG_USER USER INNER JOIN BLOG BLOG ON USER.USER_ID = BLOG.USER_ID WHERE BLOG.TAG LIKE '%' || ? || '%'";
	private static final String SEARCH_BLOG_LIST_USER = "SELECT BLOG.*, USER.* FROM BLOG_USER USER INNER JOIN BLOG BLOG ON USER.USER_ID = BLOG.USER_ID WHERE USER.USER_NAME LIKE '%' || ? || '%'";
	private static final String CREATE_BLOG = "INSERT INTO BLOG(USER_ID, TITLE, TAG, CNT_DISPLAY_POST, STATUS) VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE_BLOG = "UPDATE BLOG SET TITLE = ?, TAG = ? WHERE BLOG_ID = ?";
	private static final String DELETE_BLOG = "DELETE FROM BLOG WHERE BLOG_ID = ?";
	private static final String DELETE_REQUEST_BLOG = "UPDATE BLOG SET STATUS = '삭제요청' WHERE BLOG_ID = ?";

	@Override
	public BlogVO getUserBlog(UserVO user) {
		BlogVO blog = null;

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(USER_BLOG);
			stmt.setInt(1, user.getUserId());
			rs = stmt.executeQuery();
			while(rs.next()) {
				blog = new BlogVO();
				UserVO blogUser = new UserVO();
				blog.setBlogId(rs.getInt("BLOG_ID"));
				blog.setTitle(rs.getString("TITLE"));
				blog.setTag(rs.getString("TAG"));
				blog.setCntDisplayPost(rs.getInt("CNT_DISPLAY_POST"));
				blog.setStatus(rs.getString("STATUS"));
				blogUser.setUserId(rs.getInt("USER_ID"));
				blogUser.setUserName(rs.getString("USER_NAME"));
				blog.setUser(blogUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}

		return blog;
	}

	@Override
	public List<BlogVO> getBlogs() {
		List<BlogVO> list = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BLOG_LIST);
			rs = stmt.executeQuery();
			while(rs.next()) {
				BlogVO blog = new BlogVO();
				UserVO blogUser = new UserVO();
				blog.setBlogId(rs.getInt("BLOG_ID"));
				blog.setTitle(rs.getString("TITLE"));
				blog.setTag(rs.getString("TAG"));
				blog.setStatus(rs.getString("STATUS"));
				blogUser.setUserId(rs.getInt("USER_ID"));
				blogUser.setUserName(rs.getString("USER_NAME"));
				blog.setUser(blogUser);
				list.add(blog);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		
		return list;
	}

	@Override
	public BlogVO getBlog(BlogVO blog) {
		BlogVO findBlog = null;

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BLOG);
			stmt.setInt(1, blog.getBlogId());
			rs = stmt.executeQuery();
			while(rs.next()) {
				findBlog = new BlogVO();
				UserVO blogUser = new UserVO();
				findBlog.setBlogId(rs.getInt("BLOG_ID"));
				findBlog.setTitle(rs.getString("TITLE"));
				findBlog.setTag(rs.getString("TAG"));
				findBlog.setCntDisplayPost(rs.getInt("CNT_DISPLAY_POST"));
				findBlog.setStatus(rs.getString("STATUS"));
				blogUser.setUserId(rs.getInt("USER_ID"));
				blogUser.setUserName(rs.getString("USER_NAME"));
				findBlog.setUser(blogUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}

		return findBlog;
	}

	@Override
	public List<BlogVO> searchBlogByTitle(SearchVO search) {
		List<BlogVO> list = new ArrayList<>();

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(SEARCH_BLOG_LIST_TITLE);
			stmt.setString(1, search.getSearchValue());
			rs = stmt.executeQuery();
			while(rs.next()) {
				BlogVO blog = new BlogVO();
				UserVO blogUser = new UserVO();
				blog.setBlogId(rs.getInt("BLOG_ID"));
				blog.setTitle(rs.getString("TITLE"));
				blog.setTag(rs.getString("TAG"));
				blog.setStatus(rs.getString("STATUS"));
				blogUser.setUserId(rs.getInt("USER_ID"));
				blogUser.setUserName(rs.getString("USER_NAME"));
				blog.setUser(blogUser);
				list.add(blog);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}

		return list;
	}

	@Override
	public List<BlogVO> searchBlogByTag(SearchVO search) {
		List<BlogVO> list = new ArrayList<>();

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(SEARCH_BLOG_LIST_TAG);
			stmt.setString(1, search.getSearchValue());
			rs = stmt.executeQuery();
			while(rs.next()) {
				BlogVO blog = new BlogVO();
				UserVO blogUser = new UserVO();
				blog.setBlogId(rs.getInt("BLOG_ID"));
				blog.setTitle(rs.getString("TITLE"));
				blog.setTag(rs.getString("TAG"));
				blog.setStatus(rs.getString("STATUS"));
				blogUser.setUserId(rs.getInt("USER_ID"));
				blogUser.setUserName(rs.getString("USER_NAME"));
				blog.setUser(blogUser);
				list.add(blog);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}

		return list;
	}

	@Override
	public List<BlogVO> searchBlogByUser(SearchVO search) {
		List<BlogVO> list = new ArrayList<>();

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(SEARCH_BLOG_LIST_USER);
			stmt.setString(1, search.getSearchValue());
			rs = stmt.executeQuery();
			while(rs.next()) {
				BlogVO blog = new BlogVO();
				UserVO blogUser = new UserVO();
				blog.setBlogId(rs.getInt("BLOG_ID"));
				blog.setTitle(rs.getString("TITLE"));
				blog.setTag(rs.getString("TAG"));
				blog.setStatus(rs.getString("STATUS"));
				blogUser.setUserId(rs.getInt("USER_ID"));
				blogUser.setUserName(rs.getString("USER_NAME"));
				blog.setUser(blogUser);
				list.add(blog);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}

		return list;
	}

	@Override
	public void createBlog(BlogVO blog) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(CREATE_BLOG);
			stmt.setInt(1, blog.getUser().getUserId());
			stmt.setString(2, blog.getTitle());
			stmt.setString(3, blog.getTag());
			stmt.setInt(4, blog.getCntDisplayPost());
			stmt.setString(5, blog.getStatus());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	@Override
	public void updateBlog(BlogVO blog) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(UPDATE_BLOG);
			stmt.setString(1, blog.getTitle());
			stmt.setString(2, blog.getTag());
			stmt.setInt(3, blog.getBlogId());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	@Override
	public void deleteBlog(BlogVO blog) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(DELETE_BLOG);
			stmt.setInt(1, blog.getBlogId());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	@Override
	public void deleteRequest(BlogVO blog) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(DELETE_REQUEST_BLOG);
			stmt.setInt(1, blog.getBlogId());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}


}
