package com.study.boot.SpringBootMiddleWare.model.entity;

import lombok.Data;

import java.util.Date;
@Data
public class UserOrder {
    private Integer id;

    private String orderNo;

    private Integer userId;

    private Short isActive;

    private Date createTime;

    private Date updateTime;

    private Byte payStatus;
}