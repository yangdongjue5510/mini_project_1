package com.fastcampus.jblog.biz.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVO {
	private int userId;
	private String id;
	private String password;
	private String userName;
	private String role;

	public static UserVO entityToVO(User user) {
		return UserVO.builder()
				.userId(user.getUserId())
				.id(user.getId())
				.password(user.getPassword())
				.userName(user.getUserName())
				.role(user.getRole())
				.build();
	}

	public static User voToEntity(UserVO userVO) {
		return User.builder()
				.userId(userVO.getUserId())
				.id(userVO.getId())
				.password(userVO.getPassword())
				.userName(userVO.getUserName())
				.role(userVO.getRole())
				.build();
	}

	public static boolean isEmpty(UserVO userVO) {
		return Objects.isNull(userVO) || userVO.getUserId() == 0;
	}
}
