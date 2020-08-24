package com.study.boot.SpringBootMiddleWare.model.mapper.primary;

import com.study.boot.SpringBootMiddleWare.model.dto.UserOrderPageDto;
import com.study.boot.SpringBootMiddleWare.model.entity.UserOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserOrder record);

    int insertSelective(UserOrder record);

    UserOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserOrder record);

    int updateByPrimaryKey(UserOrder record);

    UserOrder selectUserOrderByOrderNo(String orderNo);

    List<UserOrder> pageSelectUserOrder(UserOrderPageDto userOrderPageDto);
}