package review.arraylist;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(0, 5);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);
		list.add(0, 10);
		list.add(11);
		
		System.out.println(list);
		
		System.out.println("---------------");
		
		
		list.remove(4);
		
		System.out.println(list);
	}

}
