package com.pky.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

//用户观看信息实体类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserViewDetails {

    private int userID;
    private int videoID;
    private Timestamp StartWatch;
    private Timestamp EndWatch;

}
