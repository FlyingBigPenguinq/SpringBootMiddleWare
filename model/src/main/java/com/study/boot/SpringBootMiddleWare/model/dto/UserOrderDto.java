package com.study.boot.SpringBootMiddleWare.model.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @ClassName UserOrderDto
 * @Description: TODO
 * @Author lxl
 * @Date 2020/8/19
 * @Version V1.0
 **/
public class UserOrderDto {
    private Integer id;

    @NotBlank(message = "订单编号不能为空")
    private String orderNo;

    @NotNull(message = "用户ID不能为空")
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
