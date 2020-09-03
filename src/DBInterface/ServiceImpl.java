package DBInterface;

import java.util.ArrayList;
import java.util.Scanner;

import DAO.ProductDAO;
import DAO.ShoppingDAO;
import DTO.ProductDTO;
import DTO.ShoppingDTO;

public class ServiceImpl implements Service{
	private ProductDAO pdao = new ProductDAO();
	private ShoppingDAO sdao = new ShoppingDAO();
	private Scanner in = new Scanner(System.in);
	
	@Override
	public void list() {
		ArrayList<ProductDTO> plist = pdao.selectAll();
		System.out.println("��ǰ ���");
		System.out.println("----------------------------");
		for(int i=0; i<plist.size(); i++) {
			ProductDTO tempDTO = new ProductDTO();
			tempDTO=plist.get(i);
			System.out.println("��ȣ: "+tempDTO.getNo());
			System.out.println("�̸�: " +tempDTO.getName());
			System.out.println("ȸ�� : "+tempDTO.getCompany());
			System.out.println("���� : "+tempDTO.getCnt());
			System.out.println("----------------------------");
		}
	}
	@Override
	public void buy() {
		list();
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
	@Override
	public void shoppinglist() {
		ArrayList<ShoppingDTO> slist = sdao.selectAll();
		System.out.println("��ٱ���");
		System.out.println("----------------------------");
		for(int i= 0; i<slist.size(); i++) {
			ShoppingDTO tempDTO = new ShoppingDTO();
			tempDTO=slist.get(i);
			System.out.println(i + "�� �ֹ��Ͻ� ��ǰ�Դϴ�.");
			System.out.println("ID: " +tempDTO.getUserid());
			System.out.println("��ǰ: " +tempDTO.getProname());
			System.out.println("����: " +tempDTO.getCnt());
			System.out.println("----------------------------");
		}
	}
	@Override
	public void butcancel() {
		shoppinglist();
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
	@Override
	public void soldoutlist() {
		ArrayList<ProductDTO> plist = pdao.soldOut();
		System.out.println("ǰ���� ��ǰ ���");
		System.out.println("----------------------------");
		for(int i=0; i<plist.size(); i++) {
			ProductDTO tempDTO = new ProductDTO();
			tempDTO=plist.get(i);
			System.out.println("��ȣ: "+tempDTO.getNo());
			System.out.println("�̸�: " +tempDTO.getName());
			System.out.println("ȸ�� : "+tempDTO.getCompany());
			System.out.println("���� : "+tempDTO.getCnt());
			System.out.println("----------------------------");
		}
		
	}
	
}
