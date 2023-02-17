package com.itwill.guest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwill.guest.Guest;
import com.itwill.guest.GuestService;
import com.itwill.summer.mvc.Controller;

/*
 * - 클라이언트요청한개를 처리하는 비즈니스로직을 담고있는 객체
 * - DispatcherServlet객체가 호출하는 handleRequest메쏘드를가짐
 * - handleRequest메쏘드가호출되면 DispatcherServlet객체에 forwardPath를 반환해줌
 */
public class GuestWriteActionController implements Controller {
	private GuestService guestService;
	public GuestWriteActionController() {
		guestService=new GuestService();
	}

	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		String forwardPath = "";
		/*
		 * 1.GET방식이면 user_main.do로 redirection
		 * 2.파라메타받기
		 * 3.GuestService객체사용해서 쓰기
		 * 4.guest_list.do 로 redirection
		 */
		try {
			if(request.getMethod().equalsIgnoreCase("GET")) {
				forwardPath="redirection:guest_main.do";
			}else{
				String guest_name=(String)request.getParameter("guest_name");
				String guest_email=(String)request.getParameter("guest_email");
				String guest_homepage=(String)request.getParameter("guest_homepage");
				String guest_title=(String)request.getParameter("guest_title");
				String guest_content=(String)request.getParameter("guest_content");
				Guest newGuest=new Guest(0,guest_name,null,guest_email,guest_homepage,guest_title,guest_content);
				guestService.insertGuest(newGuest);
				forwardPath = "redirect:guest_list.do";
			}
		} catch (Exception e) {
			e.printStackTrace();
			forwardPath="redirect:guest_error.do";
		}
		return forwardPath;
	}
}