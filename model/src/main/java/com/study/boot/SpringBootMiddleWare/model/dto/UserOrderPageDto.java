package com.study.boot.SpringBootMiddleWare.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName UserOrderPageDto
 * @Description: TODO
 * @Author lxl
 * @Date 2020/8/19
 * @Version V1.0
 **/
@Data
public class UserOrderPageDto extends PageDto implements Serializable {
    private String search;
}
