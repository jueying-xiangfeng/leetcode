package copy.tree;

import java.util.Comparator;
import node.tree.printer.BinaryTrees;

public class Main {

	
	public static void test1() {
		Integer data[] = new Integer[] {
				
				74, 10, 15, 54, 50, 6, 9, 5, 14, 78, 79, 82, 12, 90
		};
		
//		BinarySearchTreeCopy<Integer> bst = new BinarySearchTreeCopy<>(new Comparator<Integer>() {
//			public int compare(Integer o1, Integer o2) {
//				return o1 - o2;
//			};
//		});
//		for (int i = 0; i < data.length; i++) {
//			bst.add(data[i]);
//		}
//		
//		BinaryTrees.println(bst);
		
//		System.out.println("\n------------------------------\n");
		
//		bst.preorderTraversal();
//		bst.inorderTraversal();
//		bst.postorderTraversal();
//		bst.levelOrderTraversal();
		
		
//		bst.preorder();
//		bst.inorder();
		
//		System.out.println("\n------------------------------\n");
		
//		bst.remove(38);
//		BinaryTrees.println(bst);
		
//		System.out.println(bst.height());
		
		
//		bst.postorder();
//		bst.levelOrder();
		
		
		AVLTreeCopy<Integer> avl = new AVLTreeCopy<>();
		for (int i = 0; i < data.length; i++) {
			avl.add(data[i]);
		}
		
		BinaryTrees.println(avl);
		
		avl.remove(90);
		avl.remove(79);
		avl.remove(82);
		
		System.out.println("\n------------------------------\n");
		BinaryTrees.println(avl);
		
	}
	
	public static void main(String[] args) {
		
		
		test1();
		
	}
	
}
