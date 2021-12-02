package com.yang.domain;

import lombok.Data;

@Data
public class BlogVO {

    private int blogId;
    private String title;
    private String tag;
    private int cntDisplayPost;
    private String status;
    private int userId;
}
