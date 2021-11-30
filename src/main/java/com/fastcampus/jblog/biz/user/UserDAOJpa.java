package com.fastcampus.jblog.biz.user;

import org.springframework.beans.factory.annotation.Autowired;

public class UserDAOJpa implements UserDAO {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserVO getUser(UserVO vo) {
        return UserVO.entityToVO(userRepository.findByIdAndPassword(vo.getId(), vo.getPassword()));
    }
}
