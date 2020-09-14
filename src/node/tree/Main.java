package node.tree;

import java.util.Comparator;

import node.tree.BinarySearchTree.Visitor;
import node.tree.file.Files;
import node.tree.printer.BinaryTrees;

public class Main {

	public static class PersonComparator implements Comparator<Person> {
		public int compare(Person o1, Person o2) {
			return o1.getAge() - o2.getAge();
		}
	}
	
	public static class PersonComparator2 implements Comparator<Person> {
		public int compare(Person o1, Person o2) {
			return o2.getAge() - o1.getAge();
		}
	}
	
	static void test1() {
		Integer data[] = new Integer[] {
				
				7, 4, 9, 2 ,5, 8, 11, 3
		};
		
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		
		BinaryTrees.println(bst);
	}
	
	static void test2() {
		Integer data[] = new Integer[] {
				
				7, 4, 9, 2 ,5, 8, 11, 3
		};
		
		BinarySearchTree<Person> bst1 = new BinarySearchTree<>();
		for (int i = 0; i < data.length; i++) {
			bst1.add(new Person(data[i]));
		}
		BinaryTrees.println(bst1);
		
		// 匿名类 -- block
		BinarySearchTree<Person> bst2 = new BinarySearchTree<Person>(new Comparator<Person>() {
			public int compare(Person o1, Person o2) {
				return o2.getAge() - o1.getAge();
			}
		});
		for (int i = 0; i < data.length; i++) {
			bst2.add(new Person(data[i]));
		}
		BinaryTrees.println(bst2);
	}
	
	static void test3() {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		for (int i = 0; i < 40; i++) {
			bst.add((int)(Math.random() * 100));
		}
		BinaryTrees.println(bst);
		
		String string = BinaryTrees.printString(bst);
		string += "\n";
		Files.writeToFile("/Users/wangxiangfeng/work/1.text", string);
	}
	
	static void test4() {
		Integer data[] = new Integer[] {
				
				7, 4, 9, 2 ,5, 8, 11, 3, 1, 12
		};
		
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		
		BinaryTrees.println(bst);
		// 前序遍历
//		bst.preorderTraversal();
		// 中序遍历
//		bst.inorderTraversal();
		// 后序遍历
//		bst.postorderTraversal();
		// 层序遍历
//		bst.levelOrderTraversal();
		
		bst.preorder(new Visitor<Integer>() {
			public boolean visit(Integer element) {
				System.out.print("_" + element + "_");
				
				return element == 1 ? true : false;
			}
		});
		System.out.println("");
		bst.inorder(new Visitor<Integer>() {
			public boolean visit(Integer element) {
				System.out.print("_" + element + "_");
				
				return false;
			}
		});
		System.out.println("");
		bst.postorder(new Visitor<Integer>() {
			public boolean visit(Integer element) {
				System.out.print("_" + element + "_");
				
				return false;
			}
		});
		System.out.println("");
		bst.levelOrder(new Visitor<Integer>() {
			public boolean visit(Integer element) {
				System.out.print("_" + element + "_");
				
				return false;
			}
		});
	}
	
	static void test5() {
		Integer data[] = new Integer[] {
				
				7, 4, 9, 2 ,5, 8, 11, 3, 1, 12
		};
		
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		
		System.out.println(bst);
		
		BinaryTrees.println(bst);
	}
	
	static void test6() {
		
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		for (int i = 0; i < 20; i++) {
			bst.add((int)(Math.random() * 100));
		}
		BinaryTrees.println(bst);
		
		System.out.println(bst.height());
		System.out.println(bst.height1());
	}
	
	static void test7() {
		
		Integer data[] = new Integer[] {
				
				7, 4, 9, 2 ,5, 8, 11, 1
		};
		
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		
		BinaryTrees.println(bst);
		System.out.println(bst.isComplete());
	}
	
	static void test8() {
		
		Integer data[] = new Integer[] {
				
				7, 4, 9, 2 ,5, 8, 11, 1
		};
		
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
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
