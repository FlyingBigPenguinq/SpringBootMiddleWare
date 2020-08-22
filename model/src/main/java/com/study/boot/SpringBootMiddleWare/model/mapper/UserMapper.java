package com.study.boot.SpringBootMiddleWare.model.mapper;

import com.study.boot.SpringBootMiddleWare.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.Set;
@Mapper
public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);

    Set<String> selectAllMails();
}