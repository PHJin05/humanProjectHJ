package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.ProductDTO;
import DTO.ShoppingDTO;

public class ProductDAO {
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

	// 1. 제품 목록 보기
	public ArrayList<ProductDTO> selectAll() {
		String sql = "select * from product";
		Statement st = null;
		ArrayList<ProductDTO> templist = new ArrayList<>();
		if (conn() != null) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				
				while (rs.next()) {
					ProductDTO tempDTO = new ProductDTO();
					tempDTO.setNo(rs.getInt("no"));
					tempDTO.setName(rs.getNString("name"));
					tempDTO.setCompany(rs.getString("company"));
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
	
	// 2. 수량 줄어듬
	public void updateCnt(String name, int cnt) {
		String sql = "update product set cnt = cnt - ? where name =?";
		PreparedStatement ppst = null;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, cnt);
				ppst.setString(2, name);
				ppst.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
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
	//3. 구매 취소 후 수량 늘어남
	public void updateUp(String name, int cnt) {
		String sql = "update product set cnt = cnt + ? where name =?";
		PreparedStatement ppst = null;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, cnt);
				ppst.setString(2, name);
				ppst.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
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
	//4. 품절 제픔
	public ArrayList<ProductDTO> soldOut() {
		String sql = "select * from product where cnt = 0";
		Statement st = null;
		ArrayList<ProductDTO> templist = new ArrayList<>();
		if (conn() != null) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				
				while (rs.next()) {
					ProductDTO tempDTO = new ProductDTO();
					tempDTO.setNo(rs.getInt("no"));
					tempDTO.setName(rs.getNString("name"));
					tempDTO.setCompany(rs.getString("company"));
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
}
