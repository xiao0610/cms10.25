package com.xiaoyong.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.xiaoyong.cms.domain.Article;
import com.xiaoyong.cms.domain.ArticleWithBLOBs;
import com.xiaoyong.cms.domain.User;
import com.xiaoyong.cms.service.ArticleService;
import com.xiaoyong.cms.service.UserService;
import com.xiaoyong.cms.util.PageUtil;

@RequestMapping("admin")
@Controller
public class AdminController {
	@Autowired
	private UserService userService;

	@Autowired
	private ArticleService articleService;

	@RequestMapping(value = { "index", "", "/" })
	public String index() {
		return "admin/index";
	}
	
	@GetMapping("user")
	public String user(Model m, @RequestParam(defaultValue = "") String username,
			@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "3") Integer pageSize) {

		PageInfo<User> info = userService.selects(page, username, pageSize);
		String pages = PageUtil.page(page, info.getPages(), "/admin/user?username=" + username, pageSize);
		m.addAttribute("list", info.getList());
		m.addAttribute("info", info);
		m.addAttribute("pages", pages);
		m.addAttribute("page", page);
		m.addAttribute("username", username);
		return "admin/user";
	}

	@ResponseBody
	@RequestMapping("updateUserlocked")
	public boolean updateUserlocked(User u) {
		try {
			userService.updateUserlocked(u);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@GetMapping("article")
	public String article(Model m, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "3") Integer pageSize, Article art) {
		if (art.getStatus() == null) {
			art.setStatus(0);
		}
		if (art.getTitle() == null) {
			art.setTitle("");
		}
		PageInfo<Article> info = articleService.selects(page, art, pageSize);
		String pages = PageUtil.page(page, info.getPages(),
				"/admin/article?title=" + art.getTitle() + "&status=" + art.getStatus(), pageSize);
		m.addAttribute("list", info.getList());
		m.addAttribute("info", info);
		m.addAttribute("art", art);
		m.addAttribute("pages", pages);
		m.addAttribute("page", page);
		return "admin/article";
	}
	@ResponseBody
	@RequestMapping("updateArtlocked")
	public boolean updateArtlocked(ArticleWithBLOBs art) {
		return articleService.updateArtlocked(art)>0;
	}
	
	@RequestMapping("seeart")
	public String select(Model m,Integer id) {
		ArticleWithBLOBs art=articleService.selectById(id);
		m.addAttribute("art", art);
		return "admin/seearticle";
	}
	@ResponseBody
	@RequestMapping("updateArticle")
	public boolean updateArticle(ArticleWithBLOBs art) {
		return articleService.updateArtlocked(art)>0;
	}
}
