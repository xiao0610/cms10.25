package com.xiaoyong.cms.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyong.cms.dao.UserMapper;
import com.xiaoyong.cms.domain.User;
import com.xiaoyong.cms.exception.CMSException;
import com.xiaoyong.cms.service.UserService;
import com.xiaoyong.cms.util.Md5Util;
import com.xiaoyong.cms.vo.UserVo;
import com.xiaoyong.common.utils.StringUtil;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper usermapper;

	@Override
	public int insertSelective(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PageInfo<User> selects(Integer pageNum, String user, Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		List<User> list = usermapper.selects(user);
		return new PageInfo<User>(list);
	}

	@Override
	public void updateUserlocked(User u) {
		// TODO Auto-generated method stub
		usermapper.updateByPrimaryKeySelective(u);
	}

	@Override
	public void reg(UserVo user) {
		// TODO Auto-generated method stub
		if (null == user) {

			throw new CMSException("用户名或密码必须输入");
		}

		if (!StringUtil.hasText(user.getUsername())) {
			throw new CMSException("用户名必须输入");
		}
		if (!(user.getUsername().length() >= 2 && user.getUsername().length() <= 5)) {
			throw new CMSException("用户名长度在[2-5]之间");
		}

		if (!StringUtil.hasText(user.getPassword())) {
			throw new CMSException("密码必须输入");
		}
		if (!(user.getPassword().length() >= 6 && user.getPassword().length() <= 10)) {
			throw new CMSException("密码长度在[6-10]之间");
		}

		if (!(user.getPassword().equals(user.getRepassword()))) {
			throw new CMSException("两次密码不一致");
		}
		Date date = new Date();
		user.setCreated(date);
		user.setNickname(user.getUsername());
		user.setPassword(Md5Util.md5Encoding(user.getPassword()));
		user.setLocked(0);
		usermapper.insertSelective(user);
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		if (null == user) {

			throw new CMSException("用户名或密码必须输入");
		}

		if (!StringUtil.hasText(user.getUsername())) {
			throw new CMSException("用户名必须输入");
		}
		if (!(user.getUsername().length() >= 2 && user.getUsername().length() <= 5)) {
			throw new CMSException("用户名长度在[2-5]之间");
		}

		if (!StringUtil.hasText(user.getPassword())) {
			throw new CMSException("密码必须输入");
		}
		if (!(user.getPassword().length() >= 6 && user.getPassword().length() <= 10)) {
			throw new CMSException("密码长度在[6-10]之间");
		}
		if (usermapper.login(user) == user) {

			throw new CMSException("用户名或密码不正确");
		}
		if(usermapper.login(user).getLocked()==1) {
			throw new CMSException("账号被禁用");
		}
		if(!(usermapper.login(user).getPassword().equals(Md5Util.md5Encoding(user.getPassword())))) {
			throw new CMSException("密码不正确");
		}
		return usermapper.login(user);
	}

	@Override
	public int checkname(String name) {
		// TODO Auto-generated method stub
		return usermapper.checkname(name);
	}

}
