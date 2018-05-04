package ex0503.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * 	DB관련 로드, 연결, 닫기를 위한 클래스
 * */

// 생성자 안에 작성하게 되면 호출시 바로 사용되지만 재사용이 안된다.
// 메소드를 만들면 재사용은 되지만 호출해주어야 함

public class DBUtil {
		
	/**
	 * 	로드
	 * 	--> 한번 로드를 설정하게 되면 프로젝트 내에서는 다시 설정 할 필요x (생성자 or staticInitialize초기화 블럭)
	 * 	
	 * */
	
	static DataSource ds;
	static {
		Context initContext;
		
		try {
			initContext = new InitialContext();
			//Context envContext  = (Context)initContext.lookup("java:/comp/env/jdbc/myoracle");
			ds = (DataSource)initContext.lookup("java:/comp/env/jdbc/myoracle");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	/**
	 * 	연결
	 * 	연결은 필요할 때마다 끌어다 당겨 쓰므로 종료도 해주어야 하는데 생성자나 staticInitialize에 넣어주면 안된다.
	 * 	메소드로 분리해주는 것이 좋다
	 * @throws SQLException 
	 * */
			public static  Connection getConnection() throws SQLException  {
			
				
					return ds.getConnection();
				
				// getConnection은 Connection을 리턴한다!!
				// 연결이 필요할 떄마다 생성해서 사용하는 것 보다는 static을 붙여서 생성 없이 호출해주는 것이 좋다.
					
			}
	
	/**
	 * 	닫기
	 * */
			
			/**
			 *  insert,update, delete인 경우
			 *   Statement만 써주어도 부모타입이므로 다형성!!
			 * */
			public  static void dbClose(Connection con, Statement st ) {
				/* 예외를 던지게 된다면 fianlly 블록 안에 try catch 구문이 중첩되어 사용되므로 예외를 처리해주는
				 것이 좋다. */
			
				try {
					if (st != null) st.close();				// 사용을 하였다면 닫아준다
					if (con != null )	con.close();			//  사용을 하였다면 닫아 준다
					
				} catch (SQLException e) {e.printStackTrace(); }
		}
			
			/**
			 * 	닫아 주어야 하는 곳에 select가 포함되어 있는 경우!
			 * */
			public  static void dbClose(Connection con, Statement st, ResultSet rs ) {
				
				try {
					if (rs != null) {
						rs.close();
						dbClose(con, st);			// 메소드 재사용
					}
				} catch (SQLException e) {e.printStackTrace(); }
			}
}




