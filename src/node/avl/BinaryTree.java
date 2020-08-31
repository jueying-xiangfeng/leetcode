package node.avl;

import java.util.LinkedList;
import java.util.Queue;

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
	
	protected static class Node<E> {
		E element;
		Node<E> left;
		Node<E> right;
		Node<E> parent;
		
		public Node(E element, Node<E> parent) {
			this.element = element;
			this.parent = parent;
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
	
	/**
	 * 遍历接口设计
	 */
	public void preorder(Visitor<E> visitor) {
		if (visitor == null) return;
		preorder(root, visitor);
	}
	private void preorder(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor.stop) { return; }
		
		visitor.stop = visitor.visit(node.element);
		preorder(node.left, visitor);
		preorder(node.right, visitor);
	}
	
	public void inorder(Visitor<E> visitor) {
		if (visitor == null) return;
		inorder(root, visitor);
	}
	private void inorder(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor.stop) { return; }
		
		inorder(node.left, visitor);
		if (visitor.stop) return;
		visitor.stop = visitor.visit(node.element);
		inorder(node.right, visitor);
	}
	
	public void postorder(Visitor<E> visitor) {
		if (visitor == null) return;
		postorder(root, visitor);
	}
	private void postorder(Node<E> node, Visitor<E> visitor) {
		if (node == null) { return; }
		
		postorder(node.left, visitor);
		postorder(node.right, visitor);
		if (visitor.stop) return;
		visitor.stop = visitor.visit(node.element);
	}
	
	public void levelOrder(Visitor<E> visitor) {
		if (visitor == null) return;
		levelOrder(root, visitor);
	}
	private void levelOrder(Node<E> node, Visitor<E> visitor) {
		if (node == null) { return; }
		
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(node);
		
		while (!queue.isEmpty()) {
			Node<E> tmpNode = queue.poll();
			
			if (visitor.visit(tmpNode.element)) return;
			
			if (tmpNode.left != null) {
				queue.offer(tmpNode.left);
			}
			if (tmpNode.right != null) {
				queue.offer(tmpNode.right);
			}
		}
	}
	
	
	/**
	 * 求树的高度
	 */
	public int height() {
		return height(root);
	}
	private int height(Node<E> node) {
		if (node == null) return 0;
		return 1 + Math.max(height(node.left), height(node.right));
	}
	
	// 非递归求高度
	public int height1() {
		return height1(root);
	}
	private int height1(Node<E> node) {
		if (node == null) return 0;
		// 树的高度
		int height = 0;
		// 存储着每一层的元素数量
		int levelSize = 1;
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(node);
		
		while (!queue.isEmpty()) {
			Node<E> tmpNode = queue.poll();
			levelSize --;
			
			if (tmpNode.left != null) {
				queue.offer(tmpNode.left);
			}
			if (tmpNode.right != null) {
				queue.offer(tmpNode.right);
			}
			// 意味着要访问下一层
			if (levelSize == 0) {
				levelSize = queue.size();
				height ++;
			}
		}
		return height;
	}
	
	
	/**
	 * 判断是否是完全二叉树 - (层序遍历)
	 * 
	 * - 树为空 - false
	 * - left != null 入队
	 * - left == null && right != null - false
	 * - right != null 入队
	 * - right == null (则有两种情况，作为空或者不为空，不管哪种情况，
	 * 		后面的节点都用该为叶子节点，否则 - false)
	 * 
	 * - 遍历结束，返回 true
	 * 
	 */
	public boolean isComplete() {
		if (root == null) return false;
		
		boolean leaf = false;
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		
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
	
	
	/**
	 * 前驱结点
	 * 中序遍历时的前一个节点 (如果是二叉搜索树，前驱结点就是前一个比它小的节点)
	 * 
	 * - node.left != null
	 * 		- predecessor = node.left.right.right.right...
	 * 		- 终止：right = null
	 * 
	 * - node.left == null && node.parent != null
	 * 		- predecessor = node.parent.parent.parent...
	 * 		- 终止：node 在 parent 的右子树中
	 * 
	 * - node.left == null && node.parent == null
	 * 		- 没有前驱节点 (根节点)
	 * 
	 */
	@SuppressWarnings("unused")
	protected Node<E> predecessor(Node<E> node) {
		
		if (node == null) return null;
		
		Node<E> p = node.left;
		if (p != null) {
			while (p.right != null) {
				p = p.right;
			}
 			return p;
		}
		
		// 从父节点、祖父节点中寻找前驱结点
		while (node.parent != null && node == node.parent.left) {
			node = node.parent;
		}
		
		// node.parent = null
		// node = node.parent.right
		return node.parent;
	}
	
	/**
	 * 后继节点
	 */
	@SuppressWarnings("unused")
	protected Node<E> successor(Node<E> node) {
		
		if (node == null) return null;
		
		Node<E> p = node.right;
		if (p != null) {
			while (p.left != null) {
				p = p.left;
			}
 			return p;
		}
		
		// 从父节点、祖父节点中寻找后继结点
		while (node.parent != null && node == node.parent.right) {
			node = node.parent;
		}
		
		// node.parent = null
		// node = node.parent.left
		return node.parent;
	}
	
	protected Node<E> createNode(E element, Node<E> parent) {
		return new Node<>(element, parent);
	}
	
	public static abstract class Visitor<E> {
		boolean stop;
		/**
		 * @return 返回 true 代表停止遍历
		 */
		public abstract boolean visit(E element);
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
