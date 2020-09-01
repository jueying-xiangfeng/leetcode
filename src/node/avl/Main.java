package node.avl;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import node.avl.Times.Task;
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
		
//		test1();
		
		
		List<Integer> data = new ArrayList<>();
		for (int i = 0; i < 100_0000; i++) {
			data.add((int)(Math.random() * 100_0000));
		}
		
		List<Integer> addData = new ArrayList<>();
		for (int i = 0; i < 100_0000; i++) {
			addData.add((int)(Math.random() * 100_0000));
		}
		
		// BST
		BST<Integer> bst = new BST<Integer>();
		for (int i = 0; i < data.size(); i++) {
			bst.add(data.get(i));
		}
		
		// AVL
		AVLTree<Integer> avl = new AVLTree<Integer>();
		for (int i = 0; i < data.size(); i++) {
			avl.add(data.get(i));
		}
		
		
		Times.test("BST", new Task() {
			
			@Override
			public void execute() {
				for (int i = 0; i < data.size(); i++) {
					bst.contains(data.get(i));
				}
				
				
//				for (int i = 0; i < data.size(); i++) {
//					bst.remove(data.get(i));
//				}
			}
		});
		
		
		Times.test("AVL", new Task() {
			
			@Override
			public void execute() {
				
				for (int i = 0; i < data.size(); i++) {
					avl.contains(data.get(i));
				}
				
				
//				for (int i = 0; i < data.size(); i++) {
//					avl.remove(data.get(i));
//				}
			}
		});
		
	}
}
