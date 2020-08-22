package com.study.boot.SpringBootMiddleWare.server.service;

import com.study.boot.SpringBootMiddleWare.model.dto.MailDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @ClassName MailService
 * @Description: TODO
 * @Author lxl
 * @Date 2020/8/21
 * @Version V1.0
 **/
@Service
public class MailService {

    private static final Logger log = LoggerFactory.getLogger(MailService.class);

    @Value("spring.mail.username")
    private static String mailFrom;
    @Autowired
    private JavaMailSender mailSender;
    /*
     * @MethodName: sendSimpleTextMail
     * @Description: 发送简单文本文件
     * @Param: []
     * @Return: void
     * @Author: lxl
     * @Date: 下午3:23
    **/
    public void sendSimpleTextMail(MailDto mailDto){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(mailFrom);
        simpleMailMessage.setSubject(mailDto.getSubject());
        simpleMailMessage.setText(mailDto.getContent());
        simpleMailMessage.setTo(mailDto.getTos());
        mailSender.send(simpleMailMessage);
        log.info("----发送简单文件成功");
    }

}
