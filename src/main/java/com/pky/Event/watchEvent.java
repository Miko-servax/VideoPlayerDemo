package com.pky.Event;

import com.pky.pojo.Users;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletRequest;

//观察者模式，定义事件
@Getter
public class watchEvent extends ApplicationEvent {
    private String userName;
    private String time;
    private String videoName;

    public watchEvent(Object source) {
        super(source);
        // this.userName = userName;
        // this.time = time;
        // this.videoName = videoName;
    }

    // public watchEvent(Object source, String userName, String time) {
    //     super(source);
    //     this.userName = userName;
        // this.time = time;
    // }
}
