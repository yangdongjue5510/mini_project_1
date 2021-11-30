package com.fastcampus.jblog.config;

import com.fastcampus.jblog.aop.ControllerAdvice;
import com.fastcampus.jblog.aop.ServiceAdvice;
import com.fastcampus.jblog.biz.blog.BlogDAO;
import com.fastcampus.jblog.biz.blog.BlogDAOJdbc;
import com.fastcampus.jblog.biz.blog.BlogDAOJpa;
import com.fastcampus.jblog.biz.blog.BlogDAOMybatis;
import com.fastcampus.jblog.biz.category.CategoryDAO;
import com.fastcampus.jblog.biz.category.CategoryDAOJdbc;
import com.fastcampus.jblog.biz.category.CategoryDAOJpa;
import com.fastcampus.jblog.biz.category.CategoryDAOMybatis;
import com.fastcampus.jblog.biz.post.PostDAO;
import com.fastcampus.jblog.biz.post.PostDAOJdbc;
import com.fastcampus.jblog.biz.post.PostDAOJpa;
import com.fastcampus.jblog.biz.post.PostDAOMybatis;
import com.fastcampus.jblog.biz.user.UserDAO;
import com.fastcampus.jblog.biz.user.UserDAOJdbc;
import com.fastcampus.jblog.biz.user.UserDAOJpa;
import com.fastcampus.jblog.biz.user.UserDAOMybatis;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /* AOP Bean */
    @Bean
    public ControllerAdvice controllerAdvice() {
        return new ControllerAdvice();
    }

    @Bean
    public ServiceAdvice serviceAdvice() {
        return new ServiceAdvice();
    }

//    @Bean
//    public DaoAdvice daoAdvice() {
//        return new DaoAdvice();
//    }

    /* DAO Bean */

    @Bean
    public UserDAO userDAO() {
        return new UserDAOJdbc();
//        return new UserDAOMybatis();
//        return new UserDAOJpa();
    }

    @Bean
    public BlogDAO blogDAO() {
        return new BlogDAOJdbc();
//        return new BlogDAOMybatis();
//        return new BlogDAOJpa();
    }

    @Bean
    public CategoryDAO categoryDAO() {
        return new CategoryDAOJdbc();
//        return new CategoryDAOMybatis();
//        return new CategoryDAOJpa();
    }

    @Bean
    public PostDAO postDAO() {
        return new PostDAOJdbc();
//        return new PostDAOMybatis();
//        return new PostDAOJpa();
    }
}
