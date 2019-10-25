package com.xiaoyong.cms.service;

import com.github.pagehelper.PageInfo;
import com.xiaoyong.cms.domain.User;
import com.xiaoyong.cms.vo.UserVo;

public interface UserService {
	int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

	PageInfo<User> selects(Integer pageNum, String user, Integer pageSize);

	void updateUserlocked(User u);

	void reg(UserVo user);

	User login(User user);

	int checkname(String name);
}
