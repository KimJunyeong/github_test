package ex0503.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ex0503.dto.CustomerDTO;

public class CustomerDAOImpl implements CustomerDAO {

	@Override
	public String idCheck(String id) {
		Connection con=null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String result="중복입니다.";
		try {
			con=DBUtil.getConnection();
			ps=con.prepareStatement("select id from customer2 where id=?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(!rs.next())	//중복 아님
				result="멋진 아이디네요!";
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(con, ps, rs);
		}
		return result;
	}

	@Override
	public int insert(CustomerDTO customerDTO) {
		Connection con=null;
		PreparedStatement ps = null;
		int result=0;
		try {
			con=DBUtil.getConnection();
			ps=con.prepareStatement("insert into customer2 values(?,?,?,?,?)");
			ps.setString(1, customerDTO.getId());
			ps.setString(2, customerDTO.getName());
			ps.setInt(3, customerDTO.getAge());
			ps.setString(4, customerDTO.getPhone());
			ps.setString(5, customerDTO.getAddr());
			result=ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(con, ps);
		}
		return result;
	}

	@Override
	public List<CustomerDTO> selectAll() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<CustomerDTO> list=new ArrayList<>();
		try {
			con=DBUtil.getConnection();
			ps = con.prepareStatement("select * from customer2");
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new CustomerDTO(rs.getString(1), rs.getString(2), 
						rs.getInt(3), rs.getString(4), rs.getString(5)));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(con, ps, rs);
		}
		return list;
	}

	@Override
	public int update(CustomerDTO customerDTO) {
		Connection con=null;
		PreparedStatement ps = null;
		int result=0;
		try {
			con=DBUtil.getConnection();
			ps=con.prepareStatement("update customer2 set name=?, age=?, tel=?, addr=? where id=?");
			ps.setString(1, customerDTO.getName());
			ps.setInt(2, customerDTO.getAge());
			ps.setString(3, customerDTO.getPhone());
			ps.setString(4, customerDTO.getAddr());
			ps.setString(5, customerDTO.getId());
			result=ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(con, ps);
		}
		return result;
	}

	@Override
	public int delete(String id) {
		Connection con=null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			con=DBUtil.getConnection();
			ps=con.prepareStatement("delete from customer2 where id=?");
			ps.setString(1, id);
			result=ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(con, ps);
		}
		
		return result;
	}

}
