package com.study.boot.SpringBootMiddleWare.server.utils;

import com.study.boot.SpringBootMiddleWare.model.dto.MailDto;
import com.study.boot.SpringBootMiddleWare.server.service.MailService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Callable;

/**
 * @ClassName SendMailThread
 * @Description: TODO
 * @Author lxl
 * @Date 2020/8/22
 * @Version V1.0
 **/
@Data
public class SendMailThread implements Callable<Boolean> {
    private MailDto mailDto;
    @Autowired
    private MailService mailService;

    public SendMailThread(MailDto mailDto){
        this.mailDto = mailDto;
    }
    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Boolean call() throws Exception {
        mailService.sendHTMLMail(mailDto);

        return true;
    }
}
