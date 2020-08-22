package com.study.boot.SpringBootMiddleWare.server.exception;

import com.google.common.collect.Maps;
import com.study.boot.SpringBootMiddleWare.api.response.BaseResponse;
import com.study.boot.SpringBootMiddleWare.api.response.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ClassName GlobalExceptionHandler
 * @Description: TODO
 * @Author lxl
 * @Date 2020/8/20
 * @Version V1.0
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log= LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseBody
    public BaseResponse notFoundHandle(Exception e, HttpServletRequest request){
        BaseResponse response=new BaseResponse(StatusCode.Fail);
        Map<String,Object> resMap= Maps.newHashMap();

        resMap.put("requestURI",request.getRequestURI());
        resMap.put("errorInfo",e.getMessage());

        response.setData(resMap);
        return response;
    }
}

