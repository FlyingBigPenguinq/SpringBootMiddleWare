package com.study.boot.SpringBootMiddleWare.server.controller;

import com.study.boot.SpringBootMiddleWare.api.response.BaseResponse;
import com.study.boot.SpringBootMiddleWare.api.response.StatusCode;
import com.study.boot.SpringBootMiddleWare.server.utils.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ConfigController
 * @Description: TODO
 * @Author lxl
 * @Date 2020/8/20
 * @Version V1.0
 **/
@RestController
@RequestMapping("config")
public class ConfigController extends AbstractController{

    @Autowired
    private Environment env;

    /*@Value("wx.auth.token.appId")
    private String appid;*/
    /**
     * 读取配置信息
     * @return
     */
    @RequestMapping(value = "getInfo/v1",method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse getConfigInfo(){
        BaseResponse baseResponse = new BaseResponse(StatusCode.Success);
        Map<String,Object> dataMap =  new HashMap<>();
        try{
            String appid = env.getProperty("wx.auth.token.appId");
            String appSecrect = env.getProperty("wx.auth.token.appSecrect");
            String appRandom = env.getProperty("wx.auth.token.appRandom");
            String appVersion = env.getProperty("wx.auth.token.appVersion");
            dataMap.put("appid",appid);
            dataMap.put("appSecrect",appSecrect);
            dataMap.put("appRandom",appRandom);
            dataMap.put("appVersion",appVersion);
            baseResponse.setData(dataMap);
        }catch (Exception e){
            baseResponse = new BaseResponse(StatusCode.Fail);
        }
        return baseResponse;
    }

    @Autowired
    ConfigProperties configProperties;

    @RequestMapping(value = "getInfo/v2",method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse getConfigInfoV2(){
        BaseResponse baseResponse = new BaseResponse(StatusCode.Success);
        Map<String,Object> dataMap =  new HashMap<>();
        try{
            baseResponse.setData(configProperties);
        }catch (Exception e){
            baseResponse = new BaseResponse(StatusCode.Fail);
        }
        return baseResponse;
    }
}
