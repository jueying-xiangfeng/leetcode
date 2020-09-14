package review.tree;

import java.util.Comparator;

import node.tree.printer.BinaryTrees;

public class Main {

	/**
	 * bst test
	 */
	static void bst_test() {
		
		Integer data[] = new Integer[] {
				7, 4, 9, 2 ,5, 8, 11, 3
		};
		
//		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1 - o2;
			}
		});
		
		
		for (int i = 0; i < data.length; i++) {
			tree.add(data[i]);
		}
		
		BinaryTrees.println(tree);
		
		System.out.println("================================");
		
		tree.remove(4);
		
		BinaryTrees.println(tree);
		
		System.out.println("================================");
		
		tree.remove(7);
		
		BinaryTrees.println(tree);
		
		System.out.println("================================");
		
	}
	
		
	public static void main(String[] args) {
		
		bst_test();
		
		
		
	}

}
