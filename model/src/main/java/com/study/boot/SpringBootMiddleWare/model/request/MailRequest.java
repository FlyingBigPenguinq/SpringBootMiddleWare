package com.study.boot.SpringBootMiddleWare.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName MailRequest
 * @Description: TODO
 * @Author lxl
 * @Date 2020/8/22
 * @Version V1.0
 **/
@Data
public class MailRequest implements Serializable {
    //主题
    @NotBlank
    private String subject;
    //内容
    @NotBlank
    private String content;
    //接收人
    @NotNull
    private String[] tos;
}
