package com.study.boot.SpringBootMiddleWare.server.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * @ClassName ValidateUtil
 * @Description: TODO
 * @Author lxl
 * @Date 2020/8/19
 * @Version V1.0
 **/
public class ValidateUtil {


    public static String checkResult(BindingResult bindingResult){
        StringBuilder stringBuilder = new StringBuilder("");
        for (ObjectError error:bindingResult.getAllErrors()) {
            stringBuilder.append(error.getDefaultMessage());
        }
        return stringBuilder.toString();
    }
}
