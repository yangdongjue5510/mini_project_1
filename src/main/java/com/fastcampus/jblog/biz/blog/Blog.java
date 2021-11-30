package com.fastcampus.jblog.biz.blog;

import com.fastcampus.jblog.biz.category.Category;
import com.fastcampus.jblog.biz.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Blog {
    @Id
    @GeneratedValue
    private Integer blogId;
    private String title;
    private String tag;
    private Integer cntDisplayPost;
    private String status;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
