package com.pky.Controller;

import com.pky.Event.watchEvent;
import com.pky.Service.UserAOPAndListenerImpl;
import com.pky.Service.WorldCupForecast;
import com.pky.Service.addDetails;
import com.pky.dao.DetailsMapper;
import com.pky.dao.UserMapper;
import com.pky.dao.VideoMapper;
import com.pky.pojo.UserViewDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

//配置路由相关Controller，控制用户访问页面对应的行为
@Controller
public class RouterController {
    // sendEmail send = new sendEmail();

    @Autowired
    private UserAOPAndListenerImpl userAOPAndListener;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private addDetails addDetails;

    @Autowired
    private DetailsMapper detailsMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private WorldCupForecast worldCup;

    @Autowired
    private UserMapper userMapper;


    @RequestMapping({"/", "/index"})
    public String index(){
        return "index";
    }

    @RequestMapping("/single.html")
    public String watchView(){
        return "single";
    }

    @RequestMapping("/child/1")
    public String childView(HttpServletRequest request){

        addDetails.addVideo(1);
        publisher.publishEvent(new watchEvent(this));
        if (userMapper.getUser(userAOPAndListener.findUser()).getUserAge() < 18){
            userAOPAndListener.sendEmail(request);
        }
        return "/level1/single";
    }

    @RequestMapping("/teenager/1")
    public String teenagerView(HttpServletRequest request){
        publisher.publishEvent(new watchEvent(this));
        addDetails.addVideo(2);

        // userAOPAndListener.sendEmail(request);
        return "/level2/single";
    }

    @RequestMapping("/adult/1")
    public String adultView(HttpServletRequest request){
        addDetails.addVideo(3);
        publisher.publishEvent(new watchEvent(this));
        // userAOPAndListener.sendEmail(request);
        return "/level3/single";
    }

    @RequestMapping("/history")
    public String history(Model model){
        UserViewDetails details = detailsMapper.queryDetails(userAOPAndListener.findUserID());
        String title = videoMapper.getVideo(details.getVideoID()).getVideoName();
        String content = "暂无数据！";
        if (details!=null&&title!=null){
            content = "您于"+ details.getStartWatch() +"观看了影片"+ title +"，从"+ details.getEndWatch() +"结束观看。";
        }
        model.addAttribute("title",title);
        model.addAttribute("content",content);
        return "history";
    }

    @RequestMapping("/chouka")
    public String Chouka(){
        return "Chouka";
    }

    @RequestMapping("/others/WorldCup")
    public String worldCup(){
        return "worldCup";
    }

    @PostMapping("/others/WorldCup")
    public String forecast(@RequestParam("host") String host , @RequestParam("guest") String guest , Model model){
        // String host = (String) model.getAttribute("host");
        // String guest = (String) model.getAttribute("guest");
        System.out.println(host+guest);
        String forecast = worldCup.forecast(host,guest);
        List<String> rate = worldCup.getRate();
        model.addAttribute("forecast",forecast);
        model.addAttribute("list",rate);
        return "worldCup";
    }

}
