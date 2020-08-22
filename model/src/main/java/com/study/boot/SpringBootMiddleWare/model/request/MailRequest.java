package com.study.boot.SpringBootMiddleWare.model.request;

import lombok.Data;

/**
 * @ClassName MailRequest
 * @Description: TODO
 * @Author lxl
 * @Date 2020/8/22
 * @Version V1.0
 **/
@Data
public class MailRequest {
    //主题
    private String subject;
    //内容
    private String content;
    //接收人
    private String[] tos;
}
