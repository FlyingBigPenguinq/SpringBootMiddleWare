package com.study.boot.SpringBootMiddleWare.model.dto;

import java.io.Serializable;

/**
 * @ClassName PageDto
 * @Description: 所有分页功能的父类
 * @Author lxl
 * @Date 2020/8/19
 * @Version V1.0
 **/
public class PageDto implements Serializable {
    private Integer pageNo = 1;
    private Integer pageSize = 10;


    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
