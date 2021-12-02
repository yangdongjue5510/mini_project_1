package com.yang.service;

import com.yang.domain.UserVO;

import java.util.List;

public interface UserService {

//    List<UserVO> getUserList();

    UserVO getUser(String id, String password);

    UserVO getUserByBlogId(int blogId);

    List<UserVO> searchUserByUserName(String userName);
}
