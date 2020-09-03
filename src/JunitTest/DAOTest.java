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
		System.out.println("��ǰ ���");
		System.out.println("-----------------------");
		for(int i=0; i<plist.size(); i++) {
			ProductDTO tempDTO = new ProductDTO();
			tempDTO=plist.get(i);
			System.out.println("��ȣ: "+tempDTO.getNo());
			System.out.println("�̸�: " +tempDTO.getName());
			System.out.println("ȸ�� : "+tempDTO.getCompany());
			System.out.println("���� : "+tempDTO.getCnt());
			System.out.println("-----------------------");
		}
	}
	@Test
	public void updateCntTest() {
		pdao.updateCnt("��ǳ��", 5);
	}
	@Test
	public void updateUpTest() {
		pdao.updateUp("��ǳ��", 5);
	}
	@Test
	public void buyTest() {
		listTest();
		String selId= null;
		String selName = null;
		int selCnt = -1;
		ShoppingDTO sdto = new ShoppingDTO();
		System.out.println("ID�� �Է��ϼ���");
		selId=in.nextLine();
		sdto.setUserid(selId);
		System.out.println("�����ϴ� ��ǰ�� �Է��ϼ���");
		selName = in.nextLine();
		sdto.setProname(selName);
		System.out.println("���� ������ �Է��ϼ���");
		selCnt = in.nextInt();
		in.nextLine();
		sdto.setCnt(selCnt);
		
		sdao.insertOne(sdto);
		pdao.updateCnt(selName, selCnt);
		System.out.println("��ٱ��Ͽ� �����ϴ�.");
		
	}
	@Test
	public void shoppinglistTest() {
		ArrayList<ShoppingDTO> slist = sdao.selectAll();
		System.out.println("��ٱ���");
		System.out.println("-----------------------");
		for(int i= 0; i<slist.size(); i++) {
			ShoppingDTO tempDTO = new ShoppingDTO();
			tempDTO=slist.get(i);
			System.out.println(i + "�� �ֹ��Ͻ� ��ǰ�Դϴ�.");
			System.out.println("��ID: " +tempDTO.getUserid());
			System.out.println("��ǰ: " +tempDTO.getProname());
			System.out.println("����: " +tempDTO.getCnt());
			System.out.println("-----------------------");
		}
	}
	@Test
	public void buycancelTest() {
		shoppinglistTest();
		String selName = null;
		int selCnt = -1;
		ShoppingDTO sdto = new ShoppingDTO();
		System.out.println("����� ��ǰ�� �Է��ϼ���.");
		selName=in.nextLine();
		sdto.setProname(selName);
		System.out.println("����� ��ǰ�� ������ �Է��ϼ���.");
		System.out.println("������ �ֹ��� ��ǰ�� ��ü������ �Է��ϼ���");
		selCnt=in.nextInt();
		in.nextLine();
		sdto.setCnt(selCnt);
		
		sdao.deleteOne(sdto);
		pdao.updateUp(selName, selCnt);
		System.out.println("�ֹ��� ��ҵǾ����ϴ�.");
	}
	@Test
	public void soldoutlistTest() {
		ArrayList<ProductDTO> plist = pdao.soldOut();
		System.out.println("ǰ���� ��ǰ ���");
		System.out.println("-----------------------");
		for(int i=0; i<plist.size(); i++) {
			ProductDTO tempDTO = new ProductDTO();
			tempDTO=plist.get(i);
			System.out.println("��ȣ: "+tempDTO.getNo());
			System.out.println("�̸�: " +tempDTO.getName());
			System.out.println("ȸ�� : "+tempDTO.getCompany());
			System.out.println("���� : "+tempDTO.getCnt());
			System.out.println("-----------------------");
		}
		
	}
}
