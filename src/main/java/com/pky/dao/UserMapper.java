package com.pky.dao;

import com.pky.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//用户持久层接口
@Mapper
@Repository
public interface UserMapper {
    List<Users> queryAllUsers();

    Integer addUser(Users users);

    Integer updateUser(Users users);

    Users getUser(@Param("userName") String userName);

    // Integer deleteUser(int id);

}
