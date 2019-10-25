package com.xiaoyong.cms.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoyong.cms.dao.ChannelMapper;
import com.xiaoyong.cms.domain.Channel;
import com.xiaoyong.cms.service.ChannelService;

@Service
public class ChannelServiceImpl implements ChannelService {
	@Autowired
	private ChannelMapper mapper;
	@Override
	public List<Channel> getchannel() {
		// TODO Auto-generated method stub
		return mapper.list();
	}

}
