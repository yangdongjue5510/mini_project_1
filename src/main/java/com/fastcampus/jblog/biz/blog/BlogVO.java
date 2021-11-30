package com.fastcampus.jblog.biz.blog;

import com.fastcampus.jblog.biz.user.UserVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogVO {
	 private int blogId;
	 private String title;
	 private String tag;
	 private int cntDisplayPost;
	 private String status;
	 private UserVO user;

	 public static BlogVO entityToVO(Blog blog) {
		 return BlogVO.builder()
				 .blogId(blog.getBlogId())
				 .title(blog.getTitle())
				 .tag(blog.getTag())
				 .cntDisplayPost(blog.getCntDisplayPost())
				 .status(blog.getStatus())
				 .user(UserVO.entityToVO(blog.getUser()))
				 .build();
	 }

	 public static List<BlogVO> entityToVOList(List<Blog> blogs) {
		 List<BlogVO> blogVOList = new ArrayList<>();

		 for(Blog blog : blogs) {
			 BlogVO blogVO = BlogVO.builder()
							 .blogId(blog.getBlogId())
							 .title(blog.getTitle())
							 .tag(blog.getTag())
							 .cntDisplayPost(blog.getCntDisplayPost())
							 .status(blog.getStatus())
							 .user(UserVO.entityToVO(blog.getUser()))
							 .build();
			 blogVOList.add(blogVO);
		 }

		 return blogVOList;
	 }

	 public static Blog voToEntity(BlogVO blogVO) {
		return Blog.builder()
				.blogId(blogVO.getBlogId())
				.user(UserVO.voToEntity(blogVO.getUser()))
				.title(blogVO.getTitle())
				.tag(blogVO.getTag())
				.cntDisplayPost(blogVO.getCntDisplayPost())
				.status(blogVO.getStatus())
				.build();
	 }
}
