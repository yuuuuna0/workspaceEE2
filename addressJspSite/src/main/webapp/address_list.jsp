<%@page import="java.util.List"%>
<%@page import="com.itwill.address.Address"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.itwill.address.AddressService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
/*
 * 0.요청객체encoding설정
 * 1.파라메타받기
 * 2.AddressService객체생성
 * 3.AddressService객체 selectAll() 메쏘드호출
 * 4.List<Address> 리스트 출력
 */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>Insert title here</title>
<script type="text/javascript">
	
</script>
</head>
<body>
	<h1>[주소록리스트]</h1>
	<hr>
	<div>
		<a href='address_main.jsp'>[메인]</a> <a href='address_insert_form.jsp'>[주소록쓰기폼]</a>
	</div>
	<div>
		<ul>
			<li><a href='address_detail.jsp?no=76'>[76]이봐요</a></li>

			<li><a href='address_detail.jsp?no=77'>[77]집에갈래</a></li>

			<li><a href='address_detail.jsp?no=78'>[78]집중안되는사람</a></li>

			<li><a href='address_detail.jsp?no=4'>[4]구경호</a></li>

			<li><a href='address_detail.jsp?no=79'>[79]배고파</a></li>

			<li><a href='address_detail.jsp?no=6'>[6]이경호</a></li>

			<li><a href='address_detail.jsp?no=7'>[7]이름변경</a></li>

			<li><a href='address_detail.jsp?no=8'>[8]양경호</a></li>

			<li><a href='address_detail.jsp?no=9'>[9]인경호</a></li>

			<li><a href='address_detail.jsp?no=10'>[10]김변경</a></li>

			<li><a href='address_detail.jsp?no=11'>[11]박경호</a></li>

			<li><a href='address_detail.jsp?no=13'>[13]구경호</a></li>

			<li><a href='address_detail.jsp?no=14'>[14]정경호</a></li>

			<li><a href='address_detail.jsp?no=15'>[15]이경호</a></li>

			<li><a href='address_detail.jsp?no=80'>[80]학원을스벅처럼</a></li>

			<li><a href='address_detail.jsp?no=17'>[17]양경호</a></li>

			<li><a href='address_detail.jsp?no=18'>[18]인경호</a></li>

			<li><a href='address_detail.jsp?no=19'>[19]홍서범</a></li>

			<li><a href='address_detail.jsp?no=81'>[81]집중하는사람</a></li>

			<li><a href='address_detail.jsp?no=82'>[82]이력서빨리써요</a></li>

			<li><a href='address_detail.jsp?no=73'>[73]박경호</a></li>

			<li><a href='address_detail.jsp?no=83'>[83]어디가시나봐요</a></li>

			<li><a href='address_detail.jsp?no=84'>[84]잘있어요</a></li>

			<li><a href='address_detail.jsp?no=85'>[85]김경호</a></li>



			<li><a href='address_detail.jsp?no=47'>[47]가아드1</a></li>
		</ul>
	</div>
</body>
</html>
