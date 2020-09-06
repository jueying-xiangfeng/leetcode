package review.linkedlist;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Integer> list = new LinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		
		System.out.println(list);
		
		list.add(0, 6);
		System.out.println("----------");
		System.out.println(list);
		
		Asserts.test(list.indexOf(4) == 4);
		
		list.remove(0);
		list.remove(4);
		list.remove(1);
		System.out.println("----------");
		System.out.println(list);
		
		
		
		
	}
	
}
