package review.tree;

import java.util.Comparator;

import node.tree.printer.BinaryTrees;
import review.tree.BinaryTree.Visitor;

public class Main {

	
	static void bst_test1() {
		
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
		
		System.out.println(tree);
		
		BinaryTrees.println(tree);
		
//		System.out.println("================================");
//		
//		tree.remove(4);
//		
//		BinaryTrees.println(tree);
//		
//		System.out.println("================================");
//		
//		tree.remove(7);
//		
//		BinaryTrees.println(tree);
//		
//		System.out.println("================================");
		
	}
	
	static void bst_test2() {
		
		Integer data[] = new Integer[] {
				7, 4, 9, 2 ,5, 8, 11, 3
		};
		
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		
		
		for (int i = 0; i < data.length; i++) {
			tree.add(data[i]);
		} 
		
		BinaryTrees.println(tree);
		
		// 7, 4, 2, 3, 5, 9, 8, 11
		System.out.println("递归前序遍历 ================================");
		tree.preorderRecursion();
		
		System.out.println("前序遍历 ================================");
		tree.preorder(new Visitor<Integer>() {
			boolean visit(Integer element) {
				System.out.println(element);
				return element == 5 ? true : false;
			}
		});
		
		
		// 2, 3, 4, 5, 7, 8, 9, 11
		System.out.println("递归中序遍历 ================================");
		tree.inorderRecursion();
		
		System.out.println("中序遍历 ================================");
		tree.inorder(new Visitor<Integer>() {
			boolean visit(Integer element) {
				System.out.println(element);
				return element == 5 ? true : false;
			}
		});
		
		
		// 3, 2, 5, 4, 8, 11, 9, 7
		System.out.println("递归后序遍历 ================================");
		tree.postorderRecursion();

		System.out.println("后序遍历 ================================");
		tree.postorder(new Visitor<Integer>() {
			boolean visit(Integer element) {
				System.out.println(element);
				return element == 5 ? true : false;
			}
		});
		
		
		// 7, 4, 9, 2, 5, 8, 11, 3
		System.out.println("层序遍历 ================================");
		tree.levelOrder(new Visitor<Integer>() {
			boolean visit(Integer element) {
				System.out.println(element);
				return element == 5 ? true : false;
			}
		});
		
		
		// 7, 4, 9, 2, 5, 8, 11, 3
		System.out.println("树的高度 ======= " + tree.height() + " recursion " + tree.heightRecursion());
	}
	
	
	static void avl_test() {
		Integer data[] = new Integer[] {
				
				5, 97, 28, 62, 14, 37, 40, 15, 83, 24, 48, 16, 1, 61
		};
		
		AVLTree<Integer> avl = new AVLTree<Integer>();
		for (int i = 0; i < data.length; i++) {
			avl.add(data[i]);
		}
		
		BinaryTrees.println(avl);
		
		System.out.println("================================");
		
		avl.remove(83);
		
		BinaryTrees.println(avl);
	}
	
		
	public static void main(String[] args) {
		
//		bst_test1();
//		bst_test2();
		
		avl_test();
	}

}
