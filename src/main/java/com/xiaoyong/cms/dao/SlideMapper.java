package com.xiaoyong.cms.dao;

import java.util.List;

import com.xiaoyong.cms.domain.Slide;

public interface SlideMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Slide record);

    int insertSelective(Slide record);

    Slide selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Slide record);

    int updateByPrimaryKey(Slide record);

	List<Slide> list();
}