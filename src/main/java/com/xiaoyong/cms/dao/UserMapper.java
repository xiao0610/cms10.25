package com.xiaoyong.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaoyong.cms.domain.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> selects(@Param("username")String username);

	User login(User user);

	int checkname(@Param("name")String name);
}