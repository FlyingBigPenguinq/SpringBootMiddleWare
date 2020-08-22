package com.study.boot.SpringBootMiddleWare.server.controller;

import com.study.boot.SpringBootMiddleWare.api.response.BaseResponse;
import com.study.boot.SpringBootMiddleWare.api.response.StatusCode;
import com.study.boot.SpringBootMiddleWare.model.dto.MailDto;
import com.study.boot.SpringBootMiddleWare.model.request.MailRequest;
import com.study.boot.SpringBootMiddleWare.server.exception.NotFoundException;
import com.study.boot.SpringBootMiddleWare.server.service.MailService;
import com.study.boot.SpringBootMiddleWare.server.utils.ValidateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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

    @Autowired
    MailService mailService;

    @RequestMapping(value = "sendSimpleText")
    @ResponseBody
    public BaseResponse simpleTextMail(@Validated MailRequest mailRequest, BindingResult result){
        String checks = ValidateUtil.checkResult(result);
        if (StringUtils.isNotBlank(checks)){
            return new BaseResponse(StatusCode.InvalidParams.getCode(),checks);
        }
        BaseResponse baseResponse = new BaseResponse(StatusCode.Success);
        try{
            log.info("---发送邮件｛｝",mailRequest.getSubject());
            MailDto mailDto = new MailDto();
            BeanUtils.copyProperties(mailRequest,mailDto);
            mailService.sendSimpleTextMail(mailDto);
        }catch (Exception e){
            return new BaseResponse(StatusCode.Fail.getCode(),e.toString());
        }
        return baseResponse;
    }

    @RequestMapping(value = "sendHTMLText")
    @ResponseBody
    public BaseResponse HTMLMail(@Validated MailRequest mailRequest, BindingResult result){
        String checks = ValidateUtil.checkResult(result);
        if (StringUtils.isNotBlank(checks)){
            return new BaseResponse(StatusCode.InvalidParams.getCode(),checks);
        }
        BaseResponse baseResponse = new BaseResponse(StatusCode.Success);
        try{
            log.info("---发送邮件｛｝",mailRequest.getSubject());
            MailDto mailDto = new MailDto();
            BeanUtils.copyProperties(mailRequest,mailDto);
            mailService.sendSimpleTextMail(mailDto);
        }catch (Exception e){
            return new BaseResponse(StatusCode.Fail.getCode(),e.toString());
        }
        return baseResponse;
    }
}
