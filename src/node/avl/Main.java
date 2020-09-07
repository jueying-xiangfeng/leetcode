package node.avl;

import node.tree.printer.BinaryTrees;

public class Main {
	
	static void test1() {
		
		Integer data[] = new Integer[] {
				
				56, 89, 58, 74, 84, 21, 13
		};
		
		RBTree<Integer> rb = new RBTree<Integer>();
		for (int i = 0; i < data.length; i++) {
			rb.add(data[i]);
		}
		
		BinaryTrees.println(rb);
	}
	
	public static void main(String[] args) {
		
		test1();
		
//		
//		List<Integer> data = new ArrayList<>();
//		for (int i = 0; i < 100_0000; i++) {
//			data.add((int)(Math.random() * 100_0000));
//		}
//		
//		List<Integer> addData = new ArrayList<>();
//		for (int i = 0; i < 100_0000; i++) {
//			addData.add((int)(Math.random() * 100_0000));
//		}
//		
//		// BST
//		BST<Integer> bst = new BST<Integer>();
//		for (int i = 0; i < data.size(); i++) {
//			bst.add(data.get(i));
//		}
//		
//		// AVL
//		AVLTree<Integer> avl = new AVLTree<Integer>();
//		for (int i = 0; i < data.size(); i++) {
//			avl.add(data.get(i));
//		}
//		
//		
//		Times.test("BST", new Task() {
//			
//			@Override
//			public void execute() {
//				for (int i = 0; i < data.size(); i++) {
//					bst.contains(data.get(i));
//				}
//				
//				
////				for (int i = 0; i < data.size(); i++) {
////					bst.remove(data.get(i));
////				}
//			}
//		});
//		
//		
//		Times.test("AVL", new Task() {
//			
//			@Override
//			public void execute() {
//				
//				for (int i = 0; i < data.size(); i++) {
//					avl.contains(data.get(i));
//				}
//				
//				
////				for (int i = 0; i < data.size(); i++) {
////					avl.remove(data.get(i));
////				}
//			}
//		});
		
	}
}
