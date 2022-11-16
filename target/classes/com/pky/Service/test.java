package com.pky.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class test {
    @Autowired
    IMailService mailService;
    public static void main(String[] args) {

        System.out.println(new java.sql.Timestamp(new Date().getTime()));

        // IMailServiceImpl iMailService = new IMailServiceImpl();
        // iMailService.sendSimpleMail("xqsx48pky@163.com","主题：你好普通邮件","内容：第一封邮件");


        // IMailService iMailService = new IMailService();
        // iMailService.sendSimpleMail("xqsx48pky@163.com","主题：你好普通邮件","内容：第一封邮件");
//        String result="{face_token=;aa1926fcb8549bf80ad7d0296fa9f1c5, user_list=[{group_id=StRoot, user_id=201613117, user_info=, score=88.957275390625}]}";
//        String substring = result.substring(1, result.length() - 1);
//        String regEx="[\n`~!@#$%^&*()+|{}':;'\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？]";
//        String aa = "";
//        Pattern p = Pattern.compile(regEx);
//        Matcher m = p.matcher(substring);//这里把想要替换的字符串传进来
//        String newString = m.replaceAll(aa).trim();
//        String[] split = newString.split(",");
//        split[1]=split[1].substring(10, split[1].length());
//        String face_token=split[0].substring(11,split[0].length());
//        String group_id=split[1].substring(9,split[1].length());
//        String user_id=split[2].substring(8,split[2].length());
//        String user_info=split[3].substring(10,split[3].length());
//        String score=split[4].substring(6,split[4].length());
//        System.out.println(face_token);
//        Map<String,String> mapresule=new HashMap<>();
//        mapresule.put("face_token",face_token);
//        mapresule.put("group_id",group_id);
//        mapresule.put("user_id",user_id);
//        mapresule.put("user_info",user_info);
//        mapresule.put("score",score);


    }
}
