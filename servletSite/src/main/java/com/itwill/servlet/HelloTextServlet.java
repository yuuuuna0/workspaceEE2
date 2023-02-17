package com.itwill.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//#1. javax.servlet.http.HttpServlet 클래스를 상속 받아야한다.
public class HelloTextServlet extends HttpServlet {
	//#2. HttpServlet service 메쏘드를 오버라이딩 해야한다.(요청시 실행)
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// #2-1. 클라이언트에 전송할 데이타의 타입(종류)설정
		response.setContentType("text/plain;charset=UTF-8");
		// #2-2. 클라이언트에데이타를 전송하기위한 출력스트림 생성
		PrintWriter out=response.getWriter();
		// #2-3. 클라이언트로 데이타(text) 전송
		
		int no=(int)(Math.random()*10);
		
		for(int i=0;i<no;i++) {
			out.println("Hello text[dynamic resource] "+(i+1)+"!!!");
		}
		return;
	}

}











