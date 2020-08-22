package com.study.boot.SpringBootMiddleWare.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName MailDto
 * @Description: TODO
 * @Author lxl
 * @Date 2020/8/21
 * @Version V1.0
 **/
@Data
public class MailDto implements Serializable {

    //主题
    private String subject;
    //内容
    private String content;
    //接收人
    private String[] tos;

}
