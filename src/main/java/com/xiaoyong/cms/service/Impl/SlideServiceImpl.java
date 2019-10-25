package com.xiaoyong.cms.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyong.cms.dao.SlideMapper;
import com.xiaoyong.cms.domain.Slide;
import com.xiaoyong.cms.service.SlideService;
@Service
public class SlideServiceImpl implements SlideService {
	@Autowired
	private SlideMapper mapper;

	@Override
	public List<Slide> getslide() {
		// TODO Auto-generated method stub
		return mapper.list();
	}
}
