package com.itwill.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwill.summer.mvc.Controller;
import com.itwill.user.User;
import com.itwill.user.UserService;

public class UserLoginActionController implements Controller {
	private UserService userService;
	public UserLoginActionController() {
		userService=new UserService();
	}
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		String forwardPath = "";
		/*
		0  . GET방식요청일때 redirect:user_login_form.do forwardPath반환
		2  . 파라메타 받기
		3  . UserService객체생성
		4  . UserService.login() 메쏘드실행
		  0 : 아이디존재안함  	-> forward:/WEB-INF/views/user_login_form.jsp  forwardPath반환
		  1 : 패쓰워드 불일치	-> forward:/WEB-INF/views/user_login_form.jsp  forwardPath반환
		  2 : 로그인성공(세션)	-> redirect:user_main.do  forwardPath반환
		*/
		//HttpSession session=request.getSession();
		try {
			if(request.getMethod().equalsIgnoreCase("GET")) {
				forwardPath="redirect:user_login_form.do";
			} else {
				String userId=(String)request.getAttribute("userId");
				String password=(String)request.getAttribute("password");
				int result=userService.login(userId, password);
				if(result==0) {
					forwardPath="forward:/WEB-INF/view/user_login.jsp";
				} else if(result==1) {
					forwardPath="forward:/WEB-INF/views/user_login.jsp";
				} else {
					HttpSession session=request.getSession();
					session.setAttribute("sUserId", userId);
					forwardPath="redirect:user_main.do";
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			forwardPath="redirect:user_error.do";
		}
		return forwardPath;
	}

}
