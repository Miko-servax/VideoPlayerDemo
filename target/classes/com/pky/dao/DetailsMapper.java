package com.pky.dao;

import com.pky.pojo.UserViewDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

//观看信息持久层接口
@Mapper
@Repository
public interface DetailsMapper {

    Integer addUser(@Param("userID") int userID);
    // Integer findUser(@Param("userID") int userID);
    Integer addVideo(@Param("videoID") int videoID,@Param("userID") int userID);

    Integer addStartTime(int userID, Timestamp StartWatch);

    Integer addEndTime(int userID,Timestamp EndWatch);

    UserViewDetails queryDetails(@Param("userID") int userID);

}
