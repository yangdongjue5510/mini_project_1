package com.fastcampus.jblog.biz.user;

import com.fastcampus.jblog.biz.blog.Blog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "BLOG_USER")
public class User {
    @Id
    @GeneratedValue
    private int userId;

    private String id;

    private String password;

    private String userName;

    private String role;

    @OneToOne(mappedBy = "user")
    private Blog blog;
}
