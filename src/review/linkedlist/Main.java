package review.linkedlist;

public class Main {

	static void testList(List<Integer> list) {
		list.add(11);
		list.add(22);
		list.add(33);
		list.add(44);

		list.add(0, 55); // [55, 11, 22, 33, 44]
		list.add(2, 66); // [55, 11, 66, 22, 33, 44]
		list.add(list.size(), 77); // [55, 11, 66, 22, 33, 44, 77]

		list.remove(0); // [11, 66, 22, 33, 44, 77]
		list.remove(2); // [11, 66, 33, 44, 77]
		list.remove(list.size() - 1); // [11, 66, 33, 44]

		Asserts.test(list.indexOf(44) == 3);
		Asserts.test(list.indexOf(22) == List.ELEMENT_NOT_FOUND);
		Asserts.test(list.contains(33));
		Asserts.test(list.get(0) == 11);
		Asserts.test(list.get(1) == 66);
		Asserts.test(list.get(list.size() - 1) == 44);
		
		
//		list.remove(0);
//		list.remove(0);
//		list.remove(0);
//		list.remove(0);
		
		
		System.out.println(list);
	}
	
	
	static void josephus() {
		CircleLinkedList<Integer> list = new CircleLinkedList<>();
		for (int i = 1; i <= 8; i++) {
			list.add(i);
		}
		
		// 设置 current 指向头结点
		list.reset();
		
		while (!list.isEmpty()) {
			int n = 3;
			while (n > 1) {
				list.next();
				n--;
			}
			
			System.out.println(list.remove());
		}
	}
	
	/*
	 * 
	 * 
3
6
1
5
2
8
4
7
	 * */
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		testList(new ArrayList<Integer>());
		testList(new CircleLinkedList<Integer>());
		
		josephus();
		
		
//		List<Integer> list = new LinkedList<Integer>();
//		list.add(1);
//		list.add(2);
//		list.add(3);
//		list.add(4);
//		list.add(5);
//		
//		System.out.println(list);
//		
//		list.add(0, 6);
//		System.out.println("----------");
//		System.out.println(list);
//		
//		Asserts.test(list.indexOf(4) == 4);
//		
//		list.remove(0);
//		list.remove(4);
//		list.remove(1);
//		System.out.println("----------");
//		System.out.println(list);
		
		
		/*
		  	size=5, [1, 2, 3, 4, 5]
			----------
			size=6, [6, 1, 2, 3, 4, 5]
			----------
			size=3, [1, 3, 4]
		 */
		
	}
	
}
