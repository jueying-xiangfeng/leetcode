package review.tree;

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
