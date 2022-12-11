package com.pky.Controller;

import com.pky.Service.LoginService;
import com.pky.Service.UserAOPAndListenerImpl;
import com.pky.dao.DetailsMapper;
import com.pky.dao.UserMapper;
import com.pky.pojo.Users;
import com.pky.utils.GsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

//配置登录相关Controller
@Controller
@RequestMapping("/login")
@SessionAttributes(value = "useinf")
public class LoginController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    DetailsMapper detailsMapper;

    @Autowired
    UserAOPAndListenerImpl userAOPAndListener;
    @RequestMapping("/toLogin")
    public String toLogin(Model model){
        return "/login";
    }


    @Autowired
    LoginService loginService=null;
    //登录跳转
    @RequestMapping("/jumpGetface")
    public String getface(Users users){
        // List<Users> list = userMapper.queryAllUsers();
        // for (Users users1:list) {
        //     if (users1.getUserName().equals(users)){
        //         if (users1.getPassword().equals(users)){
        //             return
        //         }
        //     }
        // }

        return "getface";
    }
    //登录成功跳转
    @RequestMapping("/facesucceed")
    public String facesucceed(){
        int userID = userAOPAndListener.findUserID();
        detailsMapper.addUser(userID);
        return "redirect:/index";
    }
    //注册跳转
    @RequestMapping("/jumpregisterface")
    public String registerface() {
        return "registerface";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    //人脸登录
    @RequestMapping("/searchface")
    @ResponseBody
    public String searchface(@RequestBody @RequestParam(name = "imagebast64") StringBuffer imagebast64, Model model, HttpServletRequest request) throws IOException {

        Map<String, Object> searchface = loginService.searchface(imagebast64);
        System.out.println("imagebast64"+"======================="+imagebast64);
        if(searchface==null||searchface.get("user_id")==null){
            System.out.println("我进来了");
            String flag="fail";
            String s = GsonUtils.toJson(flag);
            return s;
        }
        String user_id = searchface.get("user_id").toString();
        String score=searchface.get("score").toString().substring(0,2);
        int i = Integer.parseInt(score);
        if(i>80){
            model.addAttribute("userinf",user_id);
            HttpSession session = request.getSession();
            session.setAttribute("userinf",user_id);
            System.out.println("存入session");
        }
        System.out.println(searchface);
        String s = GsonUtils.toJson(searchface);
        return s;
    }
    /**
     * 人脸注册
     * @throws Exception
     */
    @RequestMapping("/Registerface")
    @ResponseBody
    public String add(@RequestBody @RequestParam(name = "imagebast64") StringBuffer imagebast64,@RequestParam(name = "userName") String userName) throws Exception {

        String registerface = loginService.registerface(imagebast64,userName);

        System.out.println("registerface"+"=============="+registerface);
        if(registerface == null) {
            String flag="error";
            String s = GsonUtils.toJson(flag);
            return s;
        }
        String flag="success";
        String s = GsonUtils.toJson(flag);
        return s;
    }
}
