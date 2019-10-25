package com.xiaoyong.cms.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaoyong.cms.dao.ArticleMapper;
import com.xiaoyong.cms.domain.Article;
import com.xiaoyong.cms.domain.ArticleWithBLOBs;
import com.xiaoyong.cms.service.ArticleService;
@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleMapper mapper;

	@Override
	public PageInfo<Article> selects(Integer page, Article art, Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, pageSize);
		List<Article> list = mapper.selects(art);
		return new PageInfo<Article>(list);
	}

	@Override
	public ArticleWithBLOBs selectById(Integer id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateArtlocked(ArticleWithBLOBs art) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(art);
	}

	@Override
	public int insertSelective(ArticleWithBLOBs article) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(article);
	}

}
