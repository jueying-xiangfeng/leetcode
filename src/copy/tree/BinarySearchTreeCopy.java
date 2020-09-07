package copy.tree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import node.tree.printer.BinaryTreeInfo;

@SuppressWarnings("unchecked")
public class BinarySearchTreeCopy<E> implements BinaryTreeInfo {
	
	private int size;
	private Node<E> root;
	private Comparator<E> comparator;
	
	public BinarySearchTreeCopy() {
		this(null);
	}
	
	public BinarySearchTreeCopy(Comparator<E> comparator) {
		this.comparator = comparator;
	}
	
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
	
	public void add(E element) {
		elementNotNullCheck(element);
		
		if (root == null) {
			root = createNode(element, null);
			size ++;
			return;
		}
		
		Node<E> node = root;
		Node<E> parent = null;
		int cmp = 0;
		
		// 找到要添加节点的父节点
		while (node != null) {
			cmp = compare(element, node.element);
			parent = node;
			
			if (cmp > 0) {
				node = node.right;
			} else if (cmp < 0) {
				node = node.left;
			} else {
				node.element = element;
				return;
			}
		}
		// 创建新节点
		Node<E> newNode = createNode(element, parent);
		if (cmp > 0) {
			parent.right = newNode;
		} else {
			parent.left = newNode;
		}
		size ++;
	}
	
	public void remove(E element) {
		Node<E> node = node(element);
		if (node == null) return;
		
		size --;
		
		// 度为 2 的节点
		if (node.left != null && node.right != null) {
			Node<E> successor = successor(node);
			node.element = successor.element;
			node = successor;
		}
		// 到这里已经转化成了度为 1 或 0 的节点
		Node<E> replacement = node.left != null ? node.left : node.right;
		// node 是度为 1 的节点
		if (replacement != null) {
			// 修改 parent
			replacement.parent = node.parent;
			// 修改 parent left right 指向
			if (node.parent == null) {
				root = replacement;
			} else if (node.left != null) {
				node.parent.left = replacement;
			} else {
				node.parent.right = replacement;
			}
		} else if (node.parent == null) {
			root = null;
		} else {
			if (node == node.parent.left) {
				node.parent.left = null;
			} else {
				node.parent.right = null;
			}
		}
	}
	
	public boolean contains(E element) {
		return node(element) != null;
	}
	
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
				if (node.right != null) {
					return false;
				}
			}
			
			if (node.right != null) {
				queue.offer(node.right);
			} else {
				leaf = true;
			}
		}
		return true;
	}
	
	
	
	public int height() {
		return height(root);
	}
	private int height(Node<E> node) {
		if (node == null) return 0;
		
		int height = 0;
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(node);
		int levelSize = 1;
		
		while (!queue.isEmpty()) {
			Node<E> tmp = queue.poll();
			levelSize --;
			
			if (tmp.left != null) {
				queue.offer(tmp.left);
			}
			if (tmp.right != null) {
				queue.offer(tmp.right);
			}
			
			if (levelSize == 0) {
				levelSize = queue.size();
				height ++;
			}
		}
		return height;
	}
	
	public int heightRecursive() {
		return heightRecursive(root);
	}
	private int heightRecursive(Node<E> node) {
		if (node == null) return 0;
		return Math.max(heightRecursive(node.left), heightRecursive(node.right)) + 1;
	}
	
	
	// -- 前驱节点、后继节点
	@SuppressWarnings("unused")
	private Node<E> predecessor(Node<E> node) {
		if (node == null) return null;
		
		Node<E> p = node.left;
		if (p != null) {
			while (p.right != null) {
				p = p.right;
			}
			return p;
		}
		
		while (node.parent != null && node == node.parent.left) {
			node = node.parent;
		}
		return node.parent;
	}
	
	@SuppressWarnings("unused")
	private Node<E> successor(Node<E> node) {
		if (node == null) return null;
		
		Node<E> p = node.right;
		if (p != null) {
			while (p.left != null) {
				p = p.left;
			}
			return p;
		}
		
		while (node.parent != null && node == node.parent.right) {
			node = node.parent;
		}
		
		return node.parent;
	}
	
	
	
	
	// --- 迭代遍历
	public void preorder() {
		if (root == null) return;
		
		Stack<Node<E>> stack = new Stack<>();
		stack.push(root);
		
		while (!stack.isEmpty()) {
			Node<E> node = stack.pop();
			
			System.out.print(node.element + "->");
			
			if (node.right != null) {
				stack.push(node.right);
			}
			if (node.left != null) {
				stack.push(node.left);
			}
		}
	}
	
	public void inorder() {
		if (root == null) return;
		
		Stack<Node<E>> stack = new Stack<>();
		Node<E> node = root;
		
		do {
			if (node != null) {
				stack.push(node);
				node = node.left;
			} else {
				node = stack.pop();
				System.out.print(node.element + "->");
				node = node.right;
			}
		} while (!stack.isEmpty() || node != null);
	}
	
	public void postorder() {
		if (root == null) return;

		Stack<Node<E>> stack = new Stack<>();
		stack.push(root);
		Node<E> pre = root;
		
		while (!stack.isEmpty()) {
			Node<E> node = stack.peek();
			
			if ((node.left == null && node.right == null) || (pre == node.left || pre == node.right)) {
				node = stack.pop();
				System.out.print(node.element + "->");
				pre = node;
			} else {
				if (node.right != null) {
					stack.push(node.right);
				}
				if (node.left != null) {
					stack.push(node.left);
				}
			}
		}
	}
	
	public void levelOrder() {
		if (root == null) return;
		
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			
			System.out.print(node.element + "->");
			
			if (node.left != null) {
				queue.offer(node.left);
			}
			if (node.right != null) {
				queue.offer(node.right);
			}
		}
	}
	
	
	
	
	// ---- 递归遍历
	/**
	 * 前序遍历
	 */
	public void preorderTraversal() {
		preorderTraversal(root);
	}
	private void preorderTraversal(Node<E> node) {
		if (node == null) return;
		
		System.out.print(node.element + "-");
		preorderTraversal(node.left);
		preorderTraversal(node.right);
	}
	
	public void inorderTraversal() {
		inorderTraversal(root);
	}
	private void inorderTraversal(Node<E> node) {
		if (node == null) return;
		
		inorderTraversal(node.left);
		System.out.print(node.element + "-");
		inorderTraversal(node.right);
	}
	
	public void postorderTraversal() {
		postorderTraversal(root);
	}
	private void postorderTraversal(Node<E> node) {
		if (node == null) return;
		
		postorderTraversal(node.left);
		postorderTraversal(node.right);
		System.out.print(node.element + "-");
	}
	
	public void levelOrderTraversal() {
		levelOrderTraversal(root);
	}
	private void levelOrderTraversal(Node<E> node) {
		if (node == null) return;
		
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(node);
		
		while (!queue.isEmpty()) {
			Node<E> tmpNode = queue.poll();

			System.out.print(tmpNode.element + "-");
			
			if (tmpNode.left != null) {
				queue.offer(tmpNode.left);
			}
			if (tmpNode.right != null) {
				queue.offer(tmpNode.right);
			}
		}
	}
	
	
	
	protected Node<E> createNode(E element, Node<E> parent) {
		return new Node<>(element, parent);
	}
	
	
	private void elementNotNullCheck(E element) {
		if (element == null) {
			throw new IllegalArgumentException("element must not be null");
		}
	}
	
	private int compare(E e1, E e2) {
		 if (comparator != null) {
			return comparator.compare(e1, e2);
		}
		return ((Comparable<E>)e1).compareTo(e2);
	}
	
	private Node<E> node(E element) {
		Node<E> node = root;
		int cmp = 0;
		
		while (node != null) {
			cmp = compare(element, node.element);
			
			if (cmp == 0) {
				return node;
			}
			if (cmp > 0) {
				node = node.right;
			} else {
				node = node.left;
			}
		}
		return null;
	}
	
	
	public static abstract class Visitor<E> {
		boolean stop;
		/**
		 * @return 返回 true 代表停止遍历
		 */
		public abstract boolean visit(E element);
	}
	
	
	private static class Node<E> {
		E element;
		Node<E> left;
		Node<E> right;
		Node<E> parent;
		
		public Node(E element, Node<E> parent) {
			this.element = element;
			this.parent = parent;
		}
		
		public boolean isLeaf() {
			return this.left == null && this.right == null;
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
		Node<E> myNode = (Node<E>)node;
		String parentString = "null";
		if (myNode.parent != null) {
			parentString = myNode.parent.element.toString();
		}
		return myNode.element + "_p(" + parentString + ")";
	}
}
