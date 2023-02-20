package com.itwill.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwill.summer.mvc.Controller;
import com.itwill.user.User;

public class UserLoginFormController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		String msg1=(String)request.getAttribute("msg1");
		String msg2=(String)request.getAttribute("msg2");
		User fuser=(User)request.getAttribute("fuser");
		return "forward:/WEB-INF/views/user_login_form.jsp";
	}

}
