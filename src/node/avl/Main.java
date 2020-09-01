package node.avl;

import node.tree.printer.BinaryTrees;

public class Main {
	
	static void test1() {
		
		Integer data[] = new Integer[] {
				
				74, 10, 15, 54, 50, 6, 9, 5, 14, 78, 79, 82, 12, 90
		};
		
		AVLTree<Integer> avl = new AVLTree<Integer>();
		for (int i = 0; i < data.length; i++) {
			avl.add(data[i]);
		}
		
		BinaryTrees.println(avl);
		System.out.println("\n----------------------------------\n");
		avl.remove(79);
		avl.remove(90);
		avl.remove(82);
		
		BinaryTrees.println(avl);
	}
	
	public static void main(String[] args) {
		
		test1();
	}
}
