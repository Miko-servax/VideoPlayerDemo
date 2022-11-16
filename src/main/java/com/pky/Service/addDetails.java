package com.pky.Service;

import com.pky.dao.DetailsMapper;
import com.pky.dao.UserMapper;
import com.pky.dao.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

//使用AOP横切业务，在点击事件前进行用户观影信息增加
@Service
public class addDetails {

    @Autowired
    private UserAOPAndListenerImpl userAOPAndListener;

    @Autowired
    private DetailsMapper detailsMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VideoMapper videoMapper;

    public void addVideo(int videoID){
        // User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int id = userMapper.getUser(userAOPAndListener.findUser()).getID();
        detailsMapper.addVideo(videoID,id);
    }

}
