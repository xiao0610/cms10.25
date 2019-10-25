package com.xiaoyong.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.xiaoyong.cms.domain.Article;
import com.xiaoyong.cms.domain.ArticleWithBLOBs;
import com.xiaoyong.cms.domain.Channel;
import com.xiaoyong.cms.domain.User;
import com.xiaoyong.cms.service.ArticleService;
import com.xiaoyong.cms.service.CategoryService;
import com.xiaoyong.cms.service.ChannelService;
import com.xiaoyong.cms.util.PageUtil;

@Controller
@RequestMapping("my")
public class MyController {
	@Autowired
	private ChannelService channelservice;
	@Autowired
	private CategoryService categoryservice;
	@Autowired
	private ArticleService articleService;

	@RequestMapping(value = { "", "/", "index" })
	public String index() {
		return "my/index";
	}

	@GetMapping("addart")
	public String addart(Model m) {
		List<Channel> list = channelservice.getchannel();
		m.addAttribute("list", list);
		return "my/addart";
	}

	@GetMapping("getcategory")
	@ResponseBody
	public Object getcategory(int id) {
		return categoryservice.getcategory(id);
	}

	@ResponseBody
	@PostMapping("publish")
	public boolean publish(HttpServletRequest request, ArticleWithBLOBs article, MultipartFile file) {
		// 文件上传
		if (!file.isEmpty()) {
			String path = "d:/pic/";// 文件上传地址
			// 为了防止文件重名,需要使用uuid改变文件的上传名称
			// 先获取文件的原始名称.
			// 1.jpg
			String oldFilename = file.getOriginalFilename();
			String filename = UUID.randomUUID() + oldFilename.substring(oldFilename.lastIndexOf("."));
			try {
				file.transferTo(new File(path + filename));// 把文件写入硬盘

				// 设置文章标题的图片
				article.setPicture(filename);

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 文章的默认属性
		// 从session获取当前登录人的信息

		HttpSession session = request.getSession(false);
		if (null == session)
			return false;

		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());// 文章作者
		article.setStatus(0);// 未审核
		article.setDeleted(0);// 未删除
		article.setHits(0);// 点击量默认0
		article.setHot(0);// 默认非热门
		article.setCreated(new Date());
		article.setUpdated(new Date());
		// 保存文章
		return articleService.insertSelective(article) > 0;
	}
	//查询全部文章
	@RequestMapping("articles")
	public String selectsByUser(HttpServletRequest request, Model model, Article article,
			@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "4") Integer pageSize) {
		// 默认查询条件
		if (null == article.getStatus()) {
			article.setStatus(0);
		}

		// 查询自己的文章. .//

		HttpSession session = request.getSession(false);
		if (null == session)// session过期
			return "redirect:/passport/login";

		User user = (User) session.getAttribute("user");
		article.setDeleted(0);
		article.setUserId(user.getId());// 文章作者
		System.out.println(article);
		PageInfo<Article> info = articleService.selects(page, article, pageSize);
		if (article.getStatus() == null) {
			article.setStatus(0);
		}
		if (article.getTitle() == null) {
			article.setTitle("");
		}
		String pages = PageUtil.page(page, info.getPages(), "/my/articles?title=" + article.getTitle() + "&status=" + article.getStatus(), pageSize);
		model.addAttribute("articles", info.getList());
		model.addAttribute("article", article);
		model.addAttribute("pages", pages);

		return "my/articles";

	}
	//文章展示
	@GetMapping("article")
	public String article(Model model, Integer id) {
		ArticleWithBLOBs article = articleService.selectById(id);
		model.addAttribute("article", article);
		return "my/article";

	}

	@GetMapping("selectart")
	public String publish(Model model, Integer id) {
		ArticleWithBLOBs article = articleService.selectById(id);
		model.addAttribute("article", article);
		List<Channel> list = channelservice.getchannel();
		model.addAttribute("list", list);
		return "/my/articleupdate";
	}

	@ResponseBody
	@PostMapping("updateart")
	public boolean publishUpdate(HttpServletRequest request, ArticleWithBLOBs article, MultipartFile file) {
		// 文件上传
		if (!file.isEmpty()) {
			String path = "d:/pic/";// 文件上传地址
			// 为了防止文件重名,需要使用uuid改变文件的上传名称
			// 先获取文件的原始名称.
			// 1.jpg
			String oldFilename = file.getOriginalFilename();
			String filename = UUID.randomUUID() + oldFilename.substring(oldFilename.lastIndexOf("."));

			try {
				file.transferTo(new File(path + filename));// 把文件写入硬盘

				// 设置文章标题的图片
				article.setPicture(filename);

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 文章的默认属性
		article.setUpdated(new Date());
		// 保存文章
		return articleService.updateArtlocked(article) > 0;

	}
}
