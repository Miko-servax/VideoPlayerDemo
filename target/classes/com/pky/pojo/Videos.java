package com.pky.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//电影信息实体类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Videos {

    private int videoID;

    private String videoName;


}
