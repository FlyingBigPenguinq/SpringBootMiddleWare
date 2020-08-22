package com.study.boot.SpringBootMiddleWare.server.controller;

import com.study.boot.SpringBootMiddleWare.api.response.BaseResponse;
import com.study.boot.SpringBootMiddleWare.model.request.MailRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName MailController
 * @Description: 发送邮件的Ｃｏｎｔｒｏｌｌｅｒ
 * @Author lxl
 * @Date 2020/8/21
 * @Version V1.0
 **/
@Controller("mail")
public class MailController extends AbstractController{

    @RequestMapping(value = "send")
    @ResponseBody
    public BaseResponse simpleTextMail(@Validated MailRequest mailRequest, BindingResult result){



        return null;
    }
}
