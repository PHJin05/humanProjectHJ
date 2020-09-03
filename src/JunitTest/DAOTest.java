package JunitTest;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import DAO.ProductDAO;
import DAO.ShoppingDAO;
import DTO.ProductDTO;
import DTO.ShoppingDTO;

public class DAOTest {
	ProductDAO pdao= null;
	ShoppingDAO sdao= null;
	Scanner in =new Scanner(System.in);
	
	@Before
	public void loading() {
		pdao = new ProductDAO();
		sdao = new ShoppingDAO();
	}
	@Test
	public void listTest() {
		ArrayList<ProductDTO> plist = pdao.selectAll();
		System.out.println("제품 목록");
		System.out.println("-----------------------");
		for(int i=0; i<plist.size(); i++) {
			ProductDTO tempDTO = new ProductDTO();
			tempDTO=plist.get(i);
			System.out.println("번호: "+tempDTO.getNo());
			System.out.println("이름: " +tempDTO.getName());
			System.out.println("회사 : "+tempDTO.getCompany());
			System.out.println("수량 : "+tempDTO.getCnt());
			System.out.println("-----------------------");
		}
	}
	@Test
	public void updateCntTest() {
		pdao.updateCnt("선풍기", 5);
	}
	@Test
	public void updateUpTest() {
		pdao.updateUp("선풍기", 5);
	}
	@Test
	public void buyTest() {
		listTest();
		String selId= null;
		String selName = null;
		int selCnt = -1;
		ShoppingDTO sdto = new ShoppingDTO();
		System.out.println("ID를 입력하세요");
		selId=in.nextLine();
		sdto.setUserid(selId);
		System.out.println("구매하는 제품을 입력하세요");
		selName = in.nextLine();
		sdto.setProname(selName);
		System.out.println("구매 수량을 입력하세요");
		selCnt = in.nextInt();
		in.nextLine();
		sdto.setCnt(selCnt);
		
		sdao.insertOne(sdto);
		pdao.updateCnt(selName, selCnt);
		System.out.println("장바구니에 담겼습니다.");
		
	}
	@Test
	public void shoppinglistTest() {
		ArrayList<ShoppingDTO> slist = sdao.selectAll();
		System.out.println("장바구니");
		System.out.println("-----------------------");
		for(int i= 0; i<slist.size(); i++) {
			ShoppingDTO tempDTO = new ShoppingDTO();
			tempDTO=slist.get(i);
			System.out.println(i + "번 주문하신 제품입니다.");
			System.out.println("고객ID: " +tempDTO.getUserid());
			System.out.println("제품: " +tempDTO.getProname());
			System.out.println("수량: " +tempDTO.getCnt());
			System.out.println("-----------------------");
		}
	}
	@Test
	public void buycancelTest() {
		shoppinglistTest();
		String selName = null;
		int selCnt = -1;
		ShoppingDTO sdto = new ShoppingDTO();
		System.out.println("취소할 제품을 입력하세요.");
		selName=in.nextLine();
		sdto.setProname(selName);
		System.out.println("취소할 제품의 수량을 입력하세요.");
		System.out.println("수량은 주문한 제품의 전체수량을 입력하세요");
		selCnt=in.nextInt();
		in.nextLine();
		sdto.setCnt(selCnt);
		
		sdao.deleteOne(sdto);
		pdao.updateUp(selName, selCnt);
		System.out.println("주문이 취소되었습니다.");
	}
	@Test
	public void soldoutlistTest() {
		ArrayList<ProductDTO> plist = pdao.soldOut();
		System.out.println("품절된 제품 목록");
		System.out.println("-----------------------");
		for(int i=0; i<plist.size(); i++) {
			ProductDTO tempDTO = new ProductDTO();
			tempDTO=plist.get(i);
			System.out.println("번호: "+tempDTO.getNo());
			System.out.println("이름: " +tempDTO.getName());
			System.out.println("회사 : "+tempDTO.getCompany());
			System.out.println("수량 : "+tempDTO.getCnt());
			System.out.println("-----------------------");
		}
		
	}
}
