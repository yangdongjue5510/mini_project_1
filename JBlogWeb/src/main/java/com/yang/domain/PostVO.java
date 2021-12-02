package com.yang.domain;

import lombok.Data;

import java.util.Date;

@Data
public class PostVO {
    private int postId;
    private int categoryId;
    private String title;
    private String content;
    private Date createdDate;
}
