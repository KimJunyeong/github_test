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
 * 	DB���� �ε�, ����, �ݱ⸦ ���� Ŭ����
 * */

// ������ �ȿ� �ۼ��ϰ� �Ǹ� ȣ��� �ٷ� �������� ������ �ȵȴ�.
// �޼ҵ带 ����� ������ ������ ȣ�����־�� ��

public class DBUtil {
		
	/**
	 * 	�ε�
	 * 	--> �ѹ� �ε带 �����ϰ� �Ǹ� ������Ʈ �������� �ٽ� ���� �� �ʿ�x (������ or staticInitialize�ʱ�ȭ ��)
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
	 * 	����
	 * 	������ �ʿ��� ������ ����� ��� ���Ƿ� ���ᵵ ���־�� �ϴµ� �����ڳ� staticInitialize�� �־��ָ� �ȵȴ�.
	 * 	�޼ҵ�� �и����ִ� ���� ����
	 * @throws SQLException 
	 * */
			public static  Connection getConnection() throws SQLException  {
			
				
					return ds.getConnection();
				
				// getConnection�� Connection�� �����Ѵ�!!
				// ������ �ʿ��� ������ �����ؼ� ����ϴ� �� ���ٴ� static�� �ٿ��� ���� ���� ȣ�����ִ� ���� ����.
					
			}
	
	/**
	 * 	�ݱ�
	 * */
			
			/**
			 *  insert,update, delete�� ���
			 *   Statement�� ���־ �θ�Ÿ���̹Ƿ� ������!!
			 * */
			public  static void dbClose(Connection con, Statement st ) {
				/* ���ܸ� ������ �ȴٸ� fianlly ��� �ȿ� try catch ������ ��ø�Ǿ� ���ǹǷ� ���ܸ� ó�����ִ�
				 ���� ����. */
			
				try {
					if (st != null) st.close();				// ����� �Ͽ��ٸ� �ݾ��ش�
					if (con != null )	con.close();			//  ����� �Ͽ��ٸ� �ݾ� �ش�
					
				} catch (SQLException e) {e.printStackTrace(); }
		}
			
			/**
			 * 	�ݾ� �־�� �ϴ� ���� select�� ���ԵǾ� �ִ� ���!
			 * */
			public  static void dbClose(Connection con, Statement st, ResultSet rs ) {
				
				try {
					if (rs != null) {
						rs.close();
						dbClose(con, st);			// �޼ҵ� ����
					}
				} catch (SQLException e) {e.printStackTrace(); }
			}
}




