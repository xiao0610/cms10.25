package com.xiaoyong.cms.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xiaoyong.cms.domain.User;
import com.xiaoyong.cms.exception.CMSException;
import com.xiaoyong.cms.service.UserService;
import com.xiaoyong.cms.vo.UserVo;

@Controller
@RequestMapping("passport")
public class PassportController {
	@Autowired
	private UserService userservice;

	@GetMapping("reg")
	public String reg() {
		return "passport/reg";
	}

	@PostMapping("reg")
	public String reg(RedirectAttributes ra,Model m, UserVo user) {
		try {
			userservice.reg(user);
			ra.addFlashAttribute("user", user);
			m.addAttribute("error","");
			return "redirect:login";
		} catch (CMSException e) {
			// TODO Auto-generated catch block
			m.addAttribute("error", e.getMessage());
			e.printStackTrace();
			return "passport/reg";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			m.addAttribute("error", "系统错误");
			e.printStackTrace();
			return "passport/reg";
		}
	}
	@GetMapping("login")
	public String login() {
		return "passport/login";
	}
	@PostMapping("login")
	public String login(Model m,User user,HttpSession session) {
		try {
			User admin= userservice.login(user);
			session.setAttribute("user", admin);
			if(admin.getRole().equals("0")) {
				return "redirect:/my";
			}if(admin.getRole().equals("1")) {
				return "redirect:/admin";
			}
		} catch (CMSException e) {
			// TODO Auto-generated catch block
			m.addAttribute("error", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			m.addAttribute("error", "系统错误");
			e.printStackTrace();
		}
		return "passport/login";
	}
	@GetMapping("logout")
	public String logout(HttpServletRequest request) {
		//false: 如果requst中则不创建session
		HttpSession session = request.getSession(false);
		if(null!=session)
		session.invalidate();
		return "redirect:/passport/login";
	}
	
	@ResponseBody
	@PostMapping("checkname")
	public boolean checkname(String name) {
		int i=userservice.checkname(name);
		return i<=0;
	}
	
}
