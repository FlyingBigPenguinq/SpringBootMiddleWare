package com.study.boot.SpringBootMiddleWare.server.service;

import com.google.common.collect.Maps;
import com.study.boot.SpringBootMiddleWare.model.dto.MailDto;
import com.study.boot.SpringBootMiddleWare.server.utils.SendMailThread;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @ClassName MailService
 * @Description: TODO
 * @Author lxl
 * @Date 2020/8/21
 * @Version V1.0
 **/
@Service
public class MailService {

    @Autowired
    private Environment env;
    private static final Logger log = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private UserMapper userMapper;
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

    /**
     * @MethodName: sendSimpleTextMail
     * @Description: 发送ｈｔｍｌ文本邮件
     * @Param: [mailDto]
     * @Return: void
     * @Author: lxl
     * @Date: 下午2:48
    **/
    //发送HTML文本邮件
    public void sendHTMLMail(MailDto dto) throws Exception{
        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper messageHelper=new MimeMessageHelper(message,true,"utf-8");
        messageHelper.setFrom(mailFrom);
        messageHelper.setTo(dto.getTos());
        messageHelper.setSubject(dto.getSubject());
        messageHelper.setText(dto.getContent(),true);

        mailSender.send(message);
        log.info("----发送HTML文本邮件成功---->");
    }


    //发送带附件的邮件
    public void sendAppendixMail(MailDto dto) throws Exception{
        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper messageHelper=new MimeMessageHelper(message,true,"utf-8");
        messageHelper.setFrom(mailFrom);
        messageHelper.setTo(dto.getTos());
        messageHelper.setSubject(dto.getSubject());
        messageHelper.setText(dto.getContent(),true);

        //TODO:在messageHelper里面加入附件
        messageHelper.addAttachment(env.getProperty("mail.send.attachment.one.name"),new File(env.getProperty("mail.send.attachment.one.location")));
        messageHelper.addAttachment(env.getProperty("mail.send.attachment.two.name"),new File(env.getProperty("mail.send.attachment.two.location")));

        //TODO:当  附件的文件名长度大于60 || splitLongParameters=true 的时候 - 会出现附件乱码/其他奇奇怪怪的现象 - 经验
        messageHelper.addAttachment(env.getProperty("mail.send.attachment.three.name"),new File(env.getProperty("mail.send.attachment.three.location")));
        //messageHelper.addAttachment(env.getProperty("mail.send.attachment.four.name"),new File(env.getProperty("mail.send.attachment.four.location")));


        mailSender.send(message);
        log.info("----发送带附件的邮件成功---->");
    }

    /**
     * @MethodName: init
     * @Description: 设置邮件所需的系统参数
     * @Param: []
     * @Return: void
     * @Author: lxl
     * @Date: 下午3:44
    **/
    @PostConstruct
    public void init(){
        System.setProperty("mail.mime.splitlongparameters","false");
    }



    //TODO:渲染模板
    public String renderTemplate(final String templateFile,Map<String,Object> paramMap) throws Exception{
        Configuration cfg=new Configuration(Configuration.VERSION_2_3_26);

        //TODO:设置ftl模板所在的根目录
        cfg.setClassForTemplateLoading(this.getClass(),"/ftl");

        Template template=cfg.getTemplate(templateFile);
        return FreeMarkerTemplateUtils.processTemplateIntoString(template,paramMap);
    }


    //发送HTML模板文本邮件
    public void sendHTMLTemplateMail(MailDto dto) throws Exception{
        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper messageHelper=new MimeMessageHelper(message,true,"utf-8");
        messageHelper.setFrom(mailFrom);
        messageHelper.setTo(dto.getTos());
        messageHelper.setSubject(dto.getSubject());

        //TODO:渲染模板，得到html邮件内容
        Map<String,Object> dataMap= Maps.newHashMap();
        dataMap.put("mailTos", Arrays.toString(dto.getTos()));
        String content=this.renderTemplate(env.getProperty("mail.template.file.location"),dataMap);
        messageHelper.setText(content,true);


        mailSender.send(message);
        log.info("----发送HTML模板文本邮件成功---->");
    }


    public void threadSendMails(MailDto mailDto){
        Set<String> sets = userMapper.selectAllMails();
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(10);
        executor.setCorePoolSize(5);
        executor.setQueueCapacity(6);
        if (sets!=null && !sets.isEmpty()){
            sets.stream().forEach(new Consumer<String>() {
                @Override
                public void accept(String s) {
                    mailDto.setTos(new String[]{s});
                    executor.submit(new SendMailThread(mailDto));
                }
            });
        }
    }
}
