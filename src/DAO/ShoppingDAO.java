package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.ShoppingDTO;

public class ShoppingDAO {
	private Connection conn = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String id = "system";
	private String pwd = "1111";

	private ResultSet rs = null;

	public Connection conn() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pwd);
			System.out.println("DB연결이 되었습니다.");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 5. 제품 사기
	public void insertOne(ShoppingDTO shopdto) {
		String sql = "insert into shopping values(?,?,?)";
		PreparedStatement ppst = null;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, shopdto.getUserid());
				ppst.setString(2, shopdto.getProname());
				ppst.setInt(3, shopdto.getCnt());
				ppst.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				try {
					if (ppst != null)
						ppst.close();
					if (conn != null)
						conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	// 6. 구매한 제품 보기
	public ArrayList<ShoppingDTO> selectAll() {
		String sql = "select * from shopping";
		Statement st = null;
		ArrayList<ShoppingDTO> templist = new ArrayList<>();
		if (conn() != null) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					ShoppingDTO tempDTO = new ShoppingDTO();
					tempDTO.setUserid(rs.getString("userid"));
					tempDTO.setProname(rs.getString("proname"));
					tempDTO.setCnt(rs.getInt("cnt"));
					templist.add(tempDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (st != null)
						st.close();
					if (conn != null)
						conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return templist;
	}
	//7. 구매 취소
	public void deleteOne(ShoppingDTO shopdto) {
		String sql = "delete from shopping where proname = ? and cnt =?";
		PreparedStatement ppst = null;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, shopdto.getProname());
				ppst.setInt(2, shopdto.getCnt());
				ppst.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				try {
					if (ppst != null)
						ppst.close();
					if (conn != null)
						conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}
}
