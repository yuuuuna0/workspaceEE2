package com.itwill.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import com.itwill.shop.domain.User;






/*
 사용자관리에서 데이타베이스와의 작업을 전담하는 클래스
 USERINFO 테이블에 사용자를 추가,삭제,검색,수정등의 작업을한다.
 */
public class UserDao {
	/*
	 * - DataSource객체 : Connection을 반환해주는객체
	 * - 톰캣에서제공하는 DataSource 객체사용
	 */
	private DataSource dataSource;

	public UserDao() throws Exception {
		/******Apache BasicDataSource*****/
		BasicDataSource basicDataSource=new BasicDataSource();
		/*
		 * jdbc.properties 파일을 Properties객체로생성
		 */
		Properties properties=new Properties();
		properties.load(this.getClass().getResourceAsStream("/com/itwill/shop/jdbc.properties"));
		basicDataSource.setDriverClassName(properties.getProperty("driverClassName"));
		basicDataSource.setUrl(properties.getProperty("url"));
		basicDataSource.setUsername(properties.getProperty("username"));
		basicDataSource.setPassword(properties.getProperty("password"));
		this.dataSource = basicDataSource;
		
	}

	/*
	 * 사용자관리테이블에 새로운사용자생성
	 */
	public int create(User user) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		int insertRowCount = 0;
		try {
			/*
			 * 예외발생 예상코드
			 */
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(UserSQL.USER_INSERT);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getEmail());
			insertRowCount = pstmt.executeUpdate();
		} finally {
			/*
			 * 예외발생과 관계없이 반듯시 실행되는 코드
			 */
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return insertRowCount;
	}

	/*
	 * 기존의 사용자정보를 수정
	 */
	public int update(User user) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateRowCount = 0;
		try {
			/*
			 * 예외발생 예상코드
			 */
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(UserSQL.USER_UPDATE);
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getUserId());
			updateRowCount = pstmt.executeUpdate();
		} finally {
			/*
			 * 예외발생과 관계없이 반듯시 실행되는 코드
			 */
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return updateRowCount;
	}

	/*
	 * 사용자아이디에해당하는 사용자를 삭제
	 */
	public int remove(String userId) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		int removeRowCount = 0;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(UserSQL.USER_REMOVE);
			pstmt.setString(1, userId);
			removeRowCount = pstmt.executeUpdate();

		} finally {
			/*
			 * 예외발생과 관계없이 반듯시 실행되는 코드
			 */
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();

		}
		return removeRowCount;
	}

	/*
	 * 사용자아이디에해당하는 정보를 데이타베이스에서 찾아서 User 도메인클래스에 저장하여 반환
	 */
	public User findUser(String userId) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User findUser = null;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(UserSQL.USER_SELECT_BY_ID);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				findUser = new User(rs.getString("userid"), 
									rs.getString("password"), 
									rs.getString("name"),
									rs.getString("email"));

			}
		} finally {
			/*
			 * 예외발생과 관계없이 반듯시 실행되는 코드
			 */
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}
		return findUser;
	}

	/*
	 * 모든사용자 정보를 데이타베이스에서 찾아서 List<User> 콜렉션 에 저장하여 반환
	 */
	public ArrayList<User> findUserList() throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<User> findUserList = new ArrayList<User>();
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(UserSQL.USER_SELECT_ALL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				findUserList.add(new User(	rs.getString("userid"),
											rs.getString("password"), 
											rs.getString("name"),
											rs.getString("email")));

			}
		} finally {
			/*
			 * 예외발생과 관계없이 반듯시 실행되는 코드
			 */
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}
		return findUserList;
	}

	/*
	 * 인자로 전달되는 아이디를 가지는 사용자가 존재하는지의 여부를판별
	 */
	public boolean existedUser(String userId) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean isExist = false;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(UserSQL.USER_SELECT_BY_ID_COUNT);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			rs.next();
			int count = rs.getInt("cnt");
			if (count == 1) {
				isExist = true;
			}
		} finally {
			/*
			 * 예외발생과 관계없이 반듯시 실행되는 코드
			 */
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}
		return isExist;
	}

}
