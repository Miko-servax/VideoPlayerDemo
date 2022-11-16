package com.pky.Service;

import com.pky.Face.BaiduAIFace;
import com.pky.SetingModel.Setingmodel;
import com.pky.utils.GetToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

//人脸登录业务层
@Service
public class LoginService {
    @Autowired
    BaiduAIFace faceapi;
    @Autowired
    Setingmodel setingmodel;
    
    private static String token = GetToken.getAuth();
    
    public Map<String,Object> searchface(StringBuffer imagebase64) throws IOException {
        String substring = imagebase64.substring(imagebase64.indexOf(",")+1, imagebase64.length());
        setingmodel.setImgpath(substring);
        setingmodel.setGroupID("test");
        System.out.println(substring);
        Map map = faceapi.FaceSearch(setingmodel);
        return map;
    }
    /**
     * 人脸注册
     * @param
     * @return
     * @throws Exception 
     */
    public String registerface(StringBuffer imagebast64,String userName) throws Exception {
    	String result = faceapi.registerface(imagebast64,userName);
            
        return result;
       
    }
}
