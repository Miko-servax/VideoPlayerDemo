package com.pky.Service;


import com.pky.dao.DetailsMapper;
import com.pky.dao.UserMapper;
import com.pky.dao.VideoMapper;
import com.pky.pojo.UserViewDetails;
import com.pky.pojo.Users;
import com.pky.pojo.Videos;
import com.pky.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collection;

//关键业务，此类分别对应dao层，有寻找电影，寻找用户功能，同时也设定了广播监听事件，作为监听者发送邮箱的功能
@Service
public class UserAOPAndListenerImpl {

    @Autowired
    UserMapper userMapper;

    @Autowired
    VideoMapper videoMapper;
    @Autowired
    private IMailServiceImpl iMailService;
    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private DetailsMapper detailsMapper;


    public String findVideo(int videoID){
        return videoMapper.getVideo(videoID).getVideoName();
    }

    public String findUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user!=null){
            String userName = user.getUsername();
            Users user1 = userMapper.getUser(userName);
            return user1.getUserName();
        }
        else return null;
    }

    public int findUserID(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user!=null){
            String userName = user.getUsername();
            Users user1 = userMapper.getUser(userName);
            return user1.getID();
        }
        else return 0;
    }




    public void sendEmail(HttpServletRequest request){

        String username = findUser();

        if (username!=null) {
            Users currentUser = userMapper.getUser(username);
            String currentAdultUser = currentUser.getAdultUser();
            String currentAdultUserEmail = null;
            String subject = "家长你好，这是您孩子的看片记录";
            if (currentAdultUser != null) {
                currentAdultUserEmail = userMapper.getUser(currentAdultUser).getEmail();
            }
            int stage = currentUser.getUserType();
            String cuwe = (String) request.getSession().getAttribute("currentUserWatchEvent");

            UserViewDetails userDaoDetails = detailsMapper.queryDetails(currentUser.getID());

            Videos myVideo = videoMapper.getVideo(userDaoDetails.getVideoID());
            String videoName = myVideo.getVideoName();
            long ms = (userDaoDetails.getEndWatch().getTime() - userDaoDetails.getStartWatch().getTime());
            String time = DateUtil.formatTime(ms);
            String details;
            switch (stage) {
                case 1:
                    // publisher.publishEvent(new watchEvent(this, username, "20分钟", "《原神》角色演示-「纳西妲：无垠无忧」"));
                    // cuwe = (String) request.getSession().getAttribute("currentUserWatchEvent");
                    details = "家长您好，您的孩子观看了 "+videoName+ "这部影片，从"+ userDaoDetails.getStartWatch() +"开始观看，共观看了"+ time +"时间";
                    iMailService.sendSimpleMail(currentAdultUserEmail, subject, details);
                    break;
                case 2:
                    // publisher.publishEvent(new watchEvent(this, username, "20分钟"));
                    // cuwe = (String) request.getSession().getAttribute("currentUserWatchEvent");
                    details = "家长您好，您的孩子从"+ userDaoDetails.getStartWatch() +"开始观看，共观看了"+ time +"钟电影";
                    iMailService.sendSimpleMail(currentAdultUserEmail, subject, details);
                    break;
                case 3:
                    break;
            }
        }
    }


}
