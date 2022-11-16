package com.pky.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//用户信息实体类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private int ID;
    private String userName;
    private int userAge;
    private int userType;
    private int gender;
    private String personalID;
    private String email;
    private String password;
    private String adultUser;
}
