<%@page import="java.net.URLEncoder"%>
<%@page import="com.itwill.user.User"%>
<%@page import="com.itwill.user.UserService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/*
		0  . GET방식요청일때 user_main.jsp로 redirection
		1  . 요청객체 인코딩설정
	    2  . 파라메타 받기
	    3  . UserService객체생성
	    4  . UserService.create() 메쏘드실행
	    5-1. 아이디중복이면 user_write_form.jsp  
	    5-2. 가입성공이면   user_login_form.jsp 로 redierction
	*/
	if(request.getMethod().equalsIgnoreCase("GET")){
		response.sendRedirect("user_main.jsp");
		return;
	}
	//request.setCharacterEncoding("UTF-8");
	String userId=request.getParameter("userId");
	String password=request.getParameter("password");
	String name=request.getParameter("name");
	String email=request.getParameter("email");
	User newUser=new User(userId,password,name,email);
	
	UserService userService=new UserService();
	int result=userService.create(newUser);
	if(result==-1){
		/*##########아이디중복##############*/
		/****************
		String msg=
	URLEncoder.encode(userId+" 는 이미존재하는 아이디입니다","UTF-8");
		password=URLEncoder.encode(password, "UTF-8");
		name=URLEncoder.encode(name, "UTF-8");
		email=URLEncoder.encode(email, "UTF-8");
		
		String queryString="msg="+msg+
		           "&userId="+userId+
		           "&password="+password+
		           "&name="+name+
		           "&email="+email;
		****************************************/
		
		/**************case1 script***************
		out.println("<script>");
		out.println("alert('"+msg+"');");
		out.println("location.href='user_write_form.jsp?"+queryString+"';");
		out.println("</script>");
		********************************/
		/**************case2 redirect***************
		response.sendRedirect("user_write_form.jsp?"+queryString);
		**/
		/**************case3 forward***************/
		String msg=userId+" 는 이미존재하는 아이디입니다";
		request.setAttribute("msg",msg);
		request.setAttribute("fuser", newUser);
		RequestDispatcher rd=
		request.getRequestDispatcher("user_write_form.jsp");
		rd.forward(request, response);
	}else if(result==1){
		/*##########회원가입성공##############*/
		response.sendRedirect("user_login_form.jsp");
	}
%>







