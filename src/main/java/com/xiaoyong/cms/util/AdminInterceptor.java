package com.xiaoyong.cms.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xiaoyong.cms.domain.User;

public class AdminInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession(false);
		if(null!=session) {
			User user = (User) session.getAttribute("user");
			if(null!=user) {
				if(user.getRole().equals("1")) {
					return true;
				}else {
					request.getRequestDispatcher("my").forward(request, response);
					return true;
				}
			}
		}
		request.setAttribute("error", "请先登录");
		request.getRequestDispatcher("/WEB-INF/view/passport/login.jsp").forward(request, response);
		return false;
	}
}
