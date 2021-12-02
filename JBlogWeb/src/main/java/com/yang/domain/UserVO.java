package com.yang.domain;

import lombok.Data;

import java.util.Date;

@Data
public class UserVO {
    private int userId;
    private String id;
    private String userName;
    private String password;
    private String role;
}