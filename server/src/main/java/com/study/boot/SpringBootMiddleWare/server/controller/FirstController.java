package com.study.boot.SpringBootMiddleWare.server.controller;

import com.study.boot.SpringBootMiddleWare.api.response.BaseResponse;
import com.study.boot.SpringBootMiddleWare.api.response.StatusCode;
import com.study.boot.SpringBootMiddleWare.model.dto.UserOrderPageDto;
import com.study.boot.SpringBootMiddleWare.server.service.FirstServices;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Method;

/**
 * @ClassName FirstController
 * @Description: TODO
 * @Author lxl
 * @Date 2020/8/16
 * @Version V1.0
 **/
@Controller
@RequestMapping("first")
public class FirstController extends AbstractController{

    @Autowired
    private FirstServices firstServices;

    @RequestMapping(value = "hello",method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse info(){
        BaseResponse baseResponse = new BaseResponse(StatusCode.Success);
        try{
            baseResponse.setData("HEllＯ　Ｗｏｒｌｄ");
        }catch (Exception e){
            baseResponse = new BaseResponse(StatusCode.Fail);
        }finally {

        }
        return baseResponse;
    }

    @RequestMapping("order")
    @ResponseBody
    public BaseResponse getUserOrderById(Integer id){
        BaseResponse baseResponse = new BaseResponse(StatusCode.Success);
        baseResponse.setData(firstServices.getUserOrderById(id));
        return baseResponse;
    }

    @RequestMapping(value = "userOrder",method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse getUserOrderByOrderNo(String orderNo){
        System.out.println("进来了");
        BaseResponse baseResponse = new BaseResponse(StatusCode.Success);
        if (StringUtils.isBlank(orderNo)){
            return new BaseResponse(StatusCode.InvalidParams);
        }
        try{
            baseResponse.setData(firstServices.getinfo(orderNo));
        }catch (Exception e){
            log.error("用户查询订单失败",orderNo,e.fillInStackTrace());
            baseResponse = new BaseResponse(StatusCode.Fail);
        }finally {

        }
        return baseResponse;
    }

    @RequestMapping("/")
    @ResponseBody
    public BaseResponse pageGetOrders(UserOrderPageDto userOrderPageDto){
        BaseResponse baseResponse = new BaseResponse(StatusCode.Success);
        try{
            baseResponse.setData(firstServices.pageGetOrders(userOrderPageDto));
        }catch (Exception e){
            log.error("分页查询失败",e.fillInStackTrace());
            baseResponse = new BaseResponse(StatusCode.Fail);
        }finally {

        }
        return baseResponse;
    }
}
