package com.xiaoyong.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.xiaoyong.cms.domain.Article;
import com.xiaoyong.cms.domain.ArticleWithBLOBs;
import com.xiaoyong.cms.domain.Category;
import com.xiaoyong.cms.domain.Channel;
import com.xiaoyong.cms.domain.Slide;
import com.xiaoyong.cms.service.ArticleService;
import com.xiaoyong.cms.service.CategoryService;
import com.xiaoyong.cms.service.ChannelService;
import com.xiaoyong.cms.service.SlideService;
import com.xiaoyong.cms.util.PageUtil;
import com.xiaoyong.common.utils.DateUtil;

@Controller
public class IndexController {
	@Autowired
	private ChannelService channelservice;
	@Autowired
	private CategoryService categoryservice;
	@Autowired
	private ArticleService articleservice;
	@Autowired
	private SlideService slideservice;

	@RequestMapping(value = { "", "/", "index" })
	public String index(Model m, Article art, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "4") Integer pageSize) {
		art.setDeleted(0);
		List<Channel> channel = channelservice.getchannel();
		PageInfo<Article> info;
		String url = "";
		if (art.getChannelId() != null) {
			List<Category> category = categoryservice.getcategory(art.getChannelId());
			info = articleservice.selects(page, art, pageSize);
			m.addAttribute("category", category);
		} else {
			art.setHot(1);
			List<Slide> slide = slideservice.getslide();
			info = articleservice.selects(page, art, 3);
			m.addAttribute("slide", slide);
		}
		if (art.getHot() != null) {
			url += "?hot=1";
		}
		if (art.getChannelId() != null) {
			url += "?channelId=" + art.getChannelId();
			if (art.getCategoryId() != null) {
				url += "&categoryId=" + art.getCategoryId();
			}
		}
		m.addAttribute("list", info.getList());
		m.addAttribute("channel", channel);
		m.addAttribute("art", art);
		String pages = PageUtil.page(page, info.getPages(), url, pageSize);
		m.addAttribute("pages", pages);
		//24小时的热点文章
		Article art24 = new Article();
		art24.setCreated(DateUtil.getByBefore());
		art24.setHot(1);
		PageInfo<Article> info24 = articleservice.selects(1, art24, 4);
		m.addAttribute("art24", info24.getList());
		
		//最新文章
		Article newart = new Article();
		newart.setStatus(1);
		PageInfo<Article> infonew = articleservice.selects(1, newart, 4);
		m.addAttribute("newart", infonew.getList());
		return "index/index";
	}
	@RequestMapping("select")
	public String select(Model m,Integer id) {
		ArticleWithBLOBs art=articleservice.selectById(id);
		m.addAttribute("art", art);
		return "index/article";
	}
}
