package com.study.boot.SpringBootMiddleWare.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.boot.SpringBootMiddleWare.model.dto.UserOrderDto;
import com.study.boot.SpringBootMiddleWare.model.dto.UserOrderPageDto;
import com.study.boot.SpringBootMiddleWare.model.entity.UserOrder;
import com.study.boot.SpringBootMiddleWare.model.mapper.UserOrderMapper;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @ClassName FirstServices
 * @Description: TODO
 * @Author lxl
 * @Date 2020/8/18
 * @Version V1.0
 **/
@Service
public class FirstServices {

    private Logger logger = LoggerFactory.getLogger(FirstServices.class);

    @Autowired
    private UserOrderMapper userOrderMapper;

    public UserOrder getUserOrderById(Integer id){
        return userOrderMapper.selectByPrimaryKey(id);
    }

    public UserOrder getinfo (final String orderNo) throws Exception{
        if (StringUtils.isBlank(orderNo)){
            return null;
        }
        return userOrderMapper.selectUserOrderByOrderNo(orderNo);
    }

    /**
     * 根据Ｓｅａｒｃｈ　和　页码来分页查询
     * @param userOrderPageDto
     * @return
     * @throws Exception
     */
    public PageInfo<UserOrder> pageGetOrders(UserOrderPageDto userOrderPageDto) throws Exception{
        PageHelper.startPage(userOrderPageDto.getPageNo(),userOrderPageDto.getPageSize());
        List<UserOrder> userOrders = userOrderMapper.pageSelectUserOrder(userOrderPageDto);
        return new PageInfo<>(userOrders);
    }

    /***
     * 添加用户订单
     * @param userOrderDto
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Integer addUserOrder(UserOrderDto userOrderDto) throws InvocationTargetException, IllegalAccessException {
        UserOrder userOrder = new UserOrder();
        BeanUtils.copyProperties(userOrderDto,userOrder);
        userOrder.setCreateTime(DateTime.now().toDate());
        userOrder.setId(null);
        return userOrderMapper.insert(userOrder);
    }

    public Integer updataUsrOrder(UserOrderDto userOrderDto) throws InvocationTargetException, IllegalAccessException {
        UserOrder userOrder = userOrderMapper.selectByPrimaryKey(userOrderDto.getUserId());
        if (userOrder !=null ){
            BeanUtils.copyProperties(userOrderDto,userOrder);
            userOrder.setUpdateTime(DateTime.now().toDate());
            userOrderMapper.updateByPrimaryKey(userOrder);
        }
        return 1;
    }
}
