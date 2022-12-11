package com.pky.listener;

import com.pky.Event.watchEvent;
import com.pky.Service.UserAOPAndListenerImpl;
import com.pky.dao.DetailsMapper;
import com.pky.dao.UserMapper;
import com.pky.pojo.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

//观察者模式，设置监听事件，即记录用户结束观影时间（未实现实时监控，以固定时长代替）

@Component
public class WatchEventListener implements ApplicationListener<watchEvent> {

    @Autowired
    private DetailsMapper detailsMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserAOPAndListenerImpl userAOPAndListener;

    @Override
    public void onApplicationEvent(watchEvent event) {
        String username = userAOPAndListener.findUser();
        Users currentUser = userMapper.getUser(username);

        try {
            Timestamp endTime = detailsMapper.queryDetails(currentUser.getID()).getStartWatch();
            Calendar c = Calendar.getInstance();
            c.setTime(endTime);
            c.add(12,20);
            Date end = c.getTime();
            endTime = new Timestamp(end.getTime());
            detailsMapper.addEndTime(currentUser.getID(),endTime);
        }catch (Exception e){
            e.printStackTrace();
        }

        // String inf = event.getUserName() + "用户观看了"+event.getVideoName()+"影片"+event.getTime()+"时间";
        // HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // request.getSession().setAttribute("currentUserWatchEvent",inf);
        // log.info("{}用户观看了{}影片{}时间",event.getUserName(),event.getVideoName(),event.getTime());

    }
}
