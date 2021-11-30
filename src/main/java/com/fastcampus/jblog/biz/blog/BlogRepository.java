package com.fastcampus.jblog.biz.blog;

import com.fastcampus.jblog.biz.user.User;
import com.fastcampus.jblog.biz.user.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    @Query("select b, u from User u inner join Blog b on u.userId = b.user.userId where u.userId = :userId")
    Blog findByUserId(Integer userId);

    @Query("select b, u from User u inner join Blog b on u.userId = b.user.userId where b.title like %:searchValue%")
    List<Blog> findByTitle(String searchValue);

    @Query("select b, u from User u inner join Blog b on u.userId = b.user.userId where b.tag like %:searchValue%")
    List<Blog> findByTag(String searchValue);

    @Query("select b, u from User u inner join Blog b on u.userId = b.user.userId where u.userName like %:searchValue%")
    List<Blog> findByUser(String searchValue);
}
