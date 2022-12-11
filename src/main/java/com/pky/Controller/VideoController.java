package com.pky.Controller;

import com.pky.Event.watchEvent;
import com.pky.Service.UserAOPAndListenerImpl;
import com.pky.Service.WorldCupForecast;
import com.pky.Service.addDetails;
import com.pky.dao.DetailsMapper;
import com.pky.dao.UserMapper;
import com.pky.dao.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class VideoController {

    @Autowired
    private UserAOPAndListenerImpl userAOPAndListener;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private com.pky.Service.addDetails addDetails;

    @Autowired
    private DetailsMapper detailsMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private WorldCupForecast worldCup;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/v/4")
    public String Video4(HttpServletRequest request){

        addDetails.addVideo(4);
        publisher.publishEvent(new watchEvent(this));
        if (userMapper.getUser(userAOPAndListener.findUser()).getUserAge() < 18){
            userAOPAndListener.sendEmail(request);
        }
        return "/others/single4";
    }

    @RequestMapping("/v/5")
    public String Video5(HttpServletRequest request){

        addDetails.addVideo(4);
        publisher.publishEvent(new watchEvent(this));
        if (userMapper.getUser(userAOPAndListener.findUser()).getUserAge() < 18){
            userAOPAndListener.sendEmail(request);
        }
        return "/others/single5";
    }

    @RequestMapping("/v/6")
    public String Video6(HttpServletRequest request){

        addDetails.addVideo(4);
        publisher.publishEvent(new watchEvent(this));
        if (userMapper.getUser(userAOPAndListener.findUser()).getUserAge() < 18){
            userAOPAndListener.sendEmail(request);
        }
        return "/others/single6";
    }

}
