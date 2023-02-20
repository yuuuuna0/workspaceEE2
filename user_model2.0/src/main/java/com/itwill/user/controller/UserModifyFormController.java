package com.itwill.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwill.summer.mvc.Controller;
import com.itwill.user.User;
import com.itwill.user.UserService;

public class UserModifyFormController implements Controller{
	private UserService userService;
	public UserModifyFormController() {
		userService=new UserService();
	}
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		String forwardPath="";
		/****************login_check*******************/
		HttpSession session=request.getSession();
		if(session.getAttribute("sUserId")==null) {
			forwardPath="redirect:user_login_form.do";
			return forwardPath;
		}
		/*********************************************/
		/*
		1. UserService객체생성
		2. 세션의 sUserId를 사용해서 UserService.findUser()메쏘드호출
		3. 반환된 User객체를 request객체에 setAttribute한다
		4. forward:/WEB-INF/views/user_modify_form.jsp forwardPath를 반환
		*/
		try {
			String sUSerId=(String)session.getAttribute("sUserId");
			User user=userService.findUser(sUSerId);
			request.setAttribute("user", user);
			forwardPath="forward:/WEB-INF/views/user_modify_form.jsp";
		}catch (Exception e) {
			e.printStackTrace();
			forwardPath="redirect:user_error.do";
		}
		return forwardPath;
	}

}
