package com.pky.Controller;

import com.pky.dao.UserMapper;
import com.pky.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

//配置注册等行为控制
@Controller
public class UsersController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/query")
    public List<Users> queryAllUsers(){
        return userMapper.queryAllUsers();
    }

    @RequestMapping("/addUser")
    public String addUser(Users users, Model model){

        if (users.getUserAge() <= 12){
            users.setUserType(1);
            // users.setAdultUser("poky");
        }else if (users.getUserAge() < 18){
            users.setUserType(2);
            // users.setAdultUser("poky");
        }else if (users.getUserAge() >= 18){
            users.setUserType(3);
            users.setAdultUser(null);
        }

        System.out.println("添加的用户信息："+users);
        userMapper.addUser(users);
        model.addAttribute("userName",users.getUserName());
        return "registerface";
    }

}
