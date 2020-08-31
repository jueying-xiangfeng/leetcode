package node.avl;

import node.tree.printer.BinaryTrees;

public class Main {
	
	static void test1() {
		
		Integer data[] = new Integer[] {
				
				7, 4, 9, 2 ,5, 8, 11, 1
		};
		
		BST<Integer> bst = new BST<Integer>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		
		BinaryTrees.println(bst);
		
		bst.remove(4);
		
		BinaryTrees.println(bst);
	}
	
	public static void main(String[] args) {
		
		test1();
	}
}
