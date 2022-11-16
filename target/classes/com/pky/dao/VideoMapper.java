package com.pky.dao;

import com.pky.pojo.Videos;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//电影信息持久层
@Mapper
@Repository
public interface VideoMapper {

    Videos getVideo(int videoID);

}
