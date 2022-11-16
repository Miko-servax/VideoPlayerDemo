package com.pky.Controller;

import com.pky.Event.watchEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class testController {

    @Autowired
    private ApplicationEventPublisher publisher;

    @RequestMapping("/test")
    public ResponseEntity pushOrder(){

        String userName = "王芳";

        publisher.publishEvent(new watchEvent(this));

        return ResponseEntity.ok(userName);
    }


    @RequestMapping("/test2")
    public String getOrder(HttpServletRequest request){
        String ww = (String) request.getSession().getAttribute("currentUserWatchEvent");
        return ww;
    }

}
