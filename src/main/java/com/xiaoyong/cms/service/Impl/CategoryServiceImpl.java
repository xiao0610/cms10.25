package com.xiaoyong.cms.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyong.cms.dao.CategoryMapper;
import com.xiaoyong.cms.domain.Category;
import com.xiaoyong.cms.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
@Autowired
private CategoryMapper mapper;
	@Override
	public List<Category> getcategory(Integer channelId) {
		// TODO Auto-generated method stub
		return mapper.getListByChannelId(channelId);
	}

}
