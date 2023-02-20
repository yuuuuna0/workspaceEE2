package com.itwill.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwill.summer.mvc.Controller;
import com.itwill.user.UserService;

public class UserRemoveActionController implements Controller {
	private UserService userService;
	public UserRemoveActionController() {
		userService=new UserService();
	}
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		String forwardPath = "";
		/**************** login_check *******************/
		HttpSession session=request.getSession();
		if(session.getAttribute("sUserId")==null) {
			forwardPath="redirect:user_login_form.do";
			return forwardPath;
		}
		/*********************************************/
		/*
		0.login 여부체크
		1.GET방식이면 redirect:user_main.do  forwardPath반환
		2.요청객체인코딩설정
		3.세션에있는 sUserId를 사용해서 UserService.remove(sUserId) 메쏘드호출
		4.성공: redirect:user_main.do  forwardPath반환
		  실패: forward:/WEB-INF/views/user_error.jsp  forwardPath반환
		*/
		try {
			if(request.getMethod().equalsIgnoreCase("GET")) {
				forwardPath="redirect:user_main.do";
			} else {
				String sUSerId=(String)session.getAttribute("sUserId");
				int result=userService.remove(sUSerId);
				if(result!=0) {
					session.invalidate();
					forwardPath="redirect:user_main.do";
				} else {
					forwardPath="forwardPath:/WEB-INF/views/user_error.jsp";	//세션이 null일때도 해당 에러로 포워딩?
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			forwardPath="redirect:user_error.do";
		}
		
		return forwardPath;
	}

}
