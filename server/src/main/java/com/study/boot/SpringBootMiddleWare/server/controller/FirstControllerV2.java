package com.study.boot.SpringBootMiddleWare.server.controller;

import com.study.boot.SpringBootMiddleWare.api.response.BaseResponse;
import com.study.boot.SpringBootMiddleWare.api.response.StatusCode;
import com.study.boot.SpringBootMiddleWare.model.dto.UserOrderDto;
import com.study.boot.SpringBootMiddleWare.model.dto.UserOrderPageDto;
import com.study.boot.SpringBootMiddleWare.server.service.FirstServices;
import com.study.boot.SpringBootMiddleWare.server.utils.ValidateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName FirstController
 * @Description: TODO
 * @Author lxl
 * @Date 2020/8/16
 * @Version V1.0
 **/
@Controller
@RequestMapping("/first/v2")
public class FirstControllerV2 extends AbstractController{

    @Autowired
    private FirstServices firstServices;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
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

    @RequestMapping("/userOrder")
    @ResponseBody
    public BaseResponse getUserOrderByOrderNo(String orderNo){
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

    @RequestMapping("/getOrderPage")
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

    @RequestMapping("/addUserOrderV2")
    @ResponseBody
    public BaseResponse addUserOrder(@RequestBody @Validated UserOrderDto userOrderDto, BindingResult bindingResult){
        String checks = ValidateUtil.checkResult(bindingResult);
        if (StringUtils.isNotBlank(checks)){
            return new BaseResponse(StatusCode.InvalidParams.getCode(),checks);
        }
        BaseResponse baseResponse = new BaseResponse(StatusCode.Success);
        try{
            baseResponse.setData(firstServices.addUserOrder(userOrderDto));
        }catch (Exception e){
            log.error("分页查询失败",e.fillInStackTrace());
            baseResponse = new BaseResponse(StatusCode.Fail);
        }finally {

        }
        return baseResponse;
    }
}
