package com.xiaoyong.cms.service;

import com.github.pagehelper.PageInfo;
import com.xiaoyong.cms.domain.Article;
import com.xiaoyong.cms.domain.ArticleWithBLOBs;

public interface ArticleService {

	PageInfo<Article> selects(Integer page, Article art, Integer pageSize);

	ArticleWithBLOBs selectById(Integer id);


	int updateArtlocked(ArticleWithBLOBs art);

	int insertSelective(ArticleWithBLOBs article);

}
