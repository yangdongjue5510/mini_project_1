package com.fastcampus.jblog.biz.user;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDAOMybatis implements UserDAO {

    @Autowired
    private SqlSessionTemplate mybatis;

    @Override
    public UserVO getUser(UserVO user) {
        return mybatis.selectOne("UserDAO.getUser", user);
    }
}
