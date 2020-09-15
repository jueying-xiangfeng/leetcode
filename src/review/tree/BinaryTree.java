package review.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import node.tree.printer.BinaryTreeInfo;

@SuppressWarnings("unchecked")
public class BinaryTree<E> implements BinaryTreeInfo {
	
	protected int size;
	protected Node<E> root;
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		root = null;
		size = 0;
	}
	
	
	
	/**
	 * @return -- node 的前驱节点
	 * 
	 * 1、node.left != null
	 * - predecessor = node.left.right.right...right (终止条件：right = null)
	 * 
	 * 2、node.left == null && node.parent != null
	 * - predecessor = node.parent.parent...parent (终止条件：node = parent.right)
	 * 
	 * 3、node.left == null && parent == null
	 * - 没有前驱节点 (没有左子树的根节点)
	 * 
	 */
	protected Node<E> predecessor(Node<E> node) {
		if (node == null) return null;
		// 1 -
		Node<E> predecessor = node.left;
		if (predecessor != null) {
			while (predecessor.right != null) {
				predecessor = predecessor.right;
			}
			return predecessor;
		}
		// 2 -
		while (node.parent != null && node.isLeftChild()) {
			node = node.parent;
		}
		// 3 -
		return node.parent;
	}
	
	/**
	 * @return -- node 的后继节点
	 */
	protected Node<E> successor(Node<E> node) {
		if (node == null) return null;
		// 1 -
		Node<E> successor = node.right;
		if (successor != null) {
			while (successor.left != null) {
				successor = successor.left;
			}
			return successor;
		}
		// 2 -
		while (node.parent != null && node.isRightChild()) {
			node = node.parent;
		}
		// 3 -
		return node.parent;
	}
	
	
	/*************** 迭代遍历 ****************/
	
	public void levelOrder(Visitor<E> visitor) {
		levelOrder(root, visitor);
	}
	private void levelOrder(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor == null) return;
		
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(node);
		
		while (!queue.isEmpty()) {
			Node<E> tmpNode = queue.poll();
			visitor.stop = visitor.visit(tmpNode.element);
			
			if (visitor.stop == true) return;
			
			if (tmpNode.left != null) {
				queue.offer(tmpNode.left);
			}
			if (tmpNode.right != null) {
				queue.offer(tmpNode.right);
			}
		}
	}
	
	public void preorder(Visitor<E> visitor) {
		preorder(root, visitor);
	}
	private void preorder(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor == null) return;
		
		Stack<Node<E>> stack = new Stack<>();
		stack.push(node);
		
		while (!stack.isEmpty()) {
			Node<E> tmpNode = stack.pop();
			visitor.stop = visitor.visit(tmpNode.element);
			
			if (visitor.stop == true) return;
			
			if (tmpNode.right != null) {
				stack.push(tmpNode.right);
			}
			if (tmpNode.left != null) {
				stack.push(tmpNode.left);
			}
		}
	}
	
	public void inorder(Visitor<E> visitor) {
		inorder(root, visitor);
	}
	private void inorder(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor == null) return;
		
		Stack<Node<E>> stack = new Stack<>();
		Node<E> tmpNode = node;
		
		do {
			if (tmpNode != null) {
				stack.push(tmpNode);
				tmpNode = tmpNode.left;
			} else {
				tmpNode = stack.pop();
				visitor.stop = visitor.visit(tmpNode.element);
				tmpNode = tmpNode.right;
				
				if (visitor.stop == true) return;
			}
		} while (!stack.isEmpty() || tmpNode != null);
	}
	
	public void postorder(Visitor<E> visitor) {
		postorder(root, visitor);
	}
	private void postorder(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor == null) return;
		
		Stack<Node<E>> stack = new Stack<>();
		stack.push(node);
		Node<E> preNode = null;
		
		while (!stack.isEmpty()) {
			Node<E> topNode = stack.peek();
			
			if (topNode.isLeaf() || (preNode != null && preNode == topNode.left || preNode == topNode.right)) {
				topNode = stack.pop();
				visitor.stop = visitor.visit(topNode.element);
				preNode = topNode;
				if (visitor.stop == true) return;
			} else {
				if (topNode.right != null) {
					stack.push(topNode.right);
				}
				if (topNode.left != null) {
					stack.push(topNode.left);
				}
			}
		}
	}
	
	
	
	
	/*************** 递归遍历 ****************/
	
	public void preorderRecursion() {
		preorderRecursion(root);
	}
	private void preorderRecursion(Node<E> node) {
		if (node == null) return;
		
		System.out.println(node.element);
		preorderRecursion(node.left);
		preorderRecursion(node.right);
	}
	
	public void inorderRecursion() {
		inorderRecursion(root);
	}
	private void inorderRecursion(Node<E> node) {
		if (node == null) return;
		
		inorderRecursion(node.left);
		System.out.println(node.element);
		inorderRecursion(node.right);
	}
	
	public void postorderRecursion() {
		postorderRecursion(root);
	}
	private void postorderRecursion(Node<E> node) {
		if (node == null) return;
		
		postorderRecursion(node.left);
		postorderRecursion(node.right);
		System.out.println(node.element);
	}
	
	
	
	
	public int height() {
		return height(root);
	}
	private int height(Node<E> node) {
		if (node == null) return 0;
		
		int height = 0;
		int levelSize = 1; // 记录每一层的元素个数
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(node);
		
		while (!queue.isEmpty()) {
			Node<E> tmpNode = queue.poll();
			levelSize --;
			
			if (tmpNode.right != null) {
				queue.offer(tmpNode.right);
			}
			if (tmpNode.left != null) {
				queue.offer(tmpNode.left);
			}
			
			if (levelSize == 0) {
				levelSize = queue.size();
				height ++;
			}
		}
		return height;
	}
	
	public int heightRecursion() {
		return heightRecursion(root);
	}
	private int heightRecursion(Node<E> node) {
		if (node == null) return 0;
		return 1 + Math.max(heightRecursion(node.left), heightRecursion(node.right));
	}
	
	
	/**
	 * 完全二叉树
	 * 思路：-- 层序遍历
	 * 
	 * - 树为空 -- false
	 * 
	 * - left != null -- 入队
	 * - left == null && right != null -- false
	 * 
	 * - right != null -- 入队
	 * - right == null -- leaf (完全二叉树只要左子树不为空，右子树为空，
	 * 		则表示之后的节点都应该是叶子节点)
	 * 
	 * - 遍历结束 -- true
	 */
	public boolean isComplete() {
		if (root == null) return false;
		
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		boolean leaf = false;
		
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			
			if (leaf && !node.isLeaf()) return false;
			
			if (node.left != null) {
				queue.offer(node.left);
			} else {
				if (node.right != null) return false;
			}
			if (node.right != null) {
				queue.offer(node.right);
			} else {
				leaf = true;
			}
		}
		return true;
	}
	
	
	
	public static abstract class Visitor<E> {
		boolean stop;
		// 返回 true 代表停止遍历
		abstract boolean visit(E element);
	}
	
	
	/**
	 * @return 创建一个新的节点
	 */
	protected Node<E> createNode(E element, Node<E> parent) {
		return new Node<>(element, parent);
	}
	
	protected static class Node<E> {
		E element;
		Node<E> left;
		Node<E> right;
		Node<E> parent;
		
		public Node(E element, Node<E> parent) {
			this.element = element;
			this.parent = parent; 
		}
		
		public boolean hasTowChildren() {
			return left != null && right != null;
		}
		
		public boolean isLeaf() {
			return left == null && right == null;
		}
		
		public boolean isLeftChild() {
			return parent != null && this == parent.left;
		}
		
		public boolean isRightChild() {
			return parent != null && this == parent.right;
		}
	}


	
	@Override
	public Object root() {
		return root;
	}

	@Override
	public Object left(Object node) {
		return ((Node<E>)node).left;
	}

	@Override
	public Object right(Object node) {
		return ((Node<E>)node).right;
	}

	@Override
	public Object string(Object node) {
		return node;
	}
}
