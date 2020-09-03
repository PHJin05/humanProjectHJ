package Manager;

import java.util.Scanner;

import DBInterface.ServiceImpl;

public class Shop {
	private Scanner in = new Scanner(System.in);
	private ServiceImpl serv = new ServiceImpl();

	public Shop() {
		
		while (true) {
			menu();
			int selNum = -1;
			selNum = in.nextInt();
			in.nextLine();
			switch (selNum) {
			case 1:
				list();
				break;
			case 2:
				buy();
				break;
			case 3:
				shoppinglist();
				break;
			case 4:
				buycancel();
				break;
			case 5:
				soldout();
				break;
			default:
			}
		}
	}

	private void menu() {
		System.out.println("1. ��ǰ ����");
		System.out.println("2. ��ǰ ����");
		System.out.println("3. ���� ���");
		System.out.println("4. ���� ���");
		System.out.println("5. ǰ�� ��ǰ");

	}

	private void list() {
		serv.list();

	}

	private void buy() {
		serv.buy();

	}

	private void shoppinglist() {
		serv.shoppinglist();

	}
	private void buycancel() {
		serv.butcancel();
		
	}
	private void soldout() {
		serv.soldoutlist();
		
	}
}
