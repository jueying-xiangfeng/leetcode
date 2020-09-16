package review.set;

import review.tree.BinaryTree.Visitor;

public class Main {

	
	static void test1() {
		
		Set<Integer> listSet = new ListSet<>();
		listSet.add(12);
		listSet.add(10);
		listSet.add(11);
		listSet.add(11);
		listSet.add(10);
		
		listSet.traversal(new Visitor<Integer>() {
			
			@Override
			public boolean visit(Integer element) {
				System.out.println(element);
				return false;
			}
		});
		
		System.out.println("----------------------------------");
		
		Set<Integer> treeSet = new TreeSet<>();
		treeSet.add(12);
		treeSet.add(10);
		treeSet.add(11);
		treeSet.add(11);
		treeSet.add(10);
		
		treeSet.traversal(new Visitor<Integer>() {
			
			@Override
			public boolean visit(Integer element) {
				System.out.println(element);
				return false;
			}
		});
		
	}
	
	
	public static void main(String[] args) {
		
		
		test1();
		
	}

}
