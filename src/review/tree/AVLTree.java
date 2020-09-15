package review.tree;

import java.util.Comparator;

import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

/** 
 * AVL 树是最早发明的自平衡二叉搜索树之一
 * 
 * 1、平衡因子：Balanced Factor - 某节点左右子树的高度差
 * 
 * 2、AVL 树的特点
 * 
 * - 每个节点的平衡因子只能是 1、0、-1 (绝对值 <= 1，如果超过1，称之为失衡)
 * - 每个节点的左右子树高度差不超过 1
 * - 添加、删除、搜索 的时间复杂度是 o(logn)
 * 
 * 
 * 
 * ------------
 * 
 * 1、添加
 * - 可能会导致所有祖先节点都失衡
 * - 只要让高度最低的失衡节点恢复平衡，整棵树就恢复平衡【仅需O(1)次调整】
 * 
 * 2、删除
 * - 可能会导致父节点或祖先节点失衡（只有一个节点会失衡）
 * - 恢复平衡后，可能会导致更高层的祖先节点失衡【最多需要 O(logn)次调整】
 * 
 * 3、平均时间复杂度
 * - 搜索 - O(logn)
 * - 添加 - O(logn)，仅需O(1)次旋转操作
 * - 删除 - O(logn)，最多需要O(logn)次旋转操作
 * 
 */

@SuppressWarnings("unused")
public class AVLTree<E> extends BinarySearchTree<E> {
	
	public AVLTree() {
		this(null);
	}
	
	public AVLTree(Comparator<E> comparator) {
		super(comparator);
	}
	
	
	@Override
	protected void afterAdd(Node<E> node) {
		while ((node = node.parent) != null) {
			if (isBalanced(node)) {
				// 平衡 -- 更新高度
				updateHeight(node);
			} else {
				// 恢复平衡
				rebalance(node);
				break;
			}
		}
	}
	
	@Override
	protected void afterRemove(Node<E> node) {
		while ((node = node.parent) != null) {
			if (isBalanced(node)) {
				updateHeight(node);
			} else {
				rebalance(node);
			}
		}
	}
	
	
	
	/**
	 * 恢复平衡
	 */
	private void rebalance(Node<E> grand) {
		Node<E> parent = ((AVLNode<E>)grand).tallerChild();
		Node<E> node = ((AVLNode<E>)parent).tallerChild();
		
		if (parent.isLeftChild()) { // L
			if (node.isLeftChild()) { // LL
				rotateRight(grand);
			} else { // LR
				rotateLeft(parent);
				rotateRight(grand);
			}
		} else { // R
			if (node.isLeftChild()) { // RL
				rotateRight(parent);
				rotateLeft(grand);
			} else { // RR
				rotateLeft(grand);
			}
		}
	}
	
	
	// 向左旋转
	private void rotateLeft(Node<E> grand) {
		Node<E> parent = grand.right;
		Node<E> child = parent.left;
		
		grand.right = child;
		parent.left = grand;
		afterRotate(grand, parent, child);
	}
	
	// 向右旋转
	private void rotateRight(Node<E> grand) {
		Node<E> parent = grand.left;
		Node<E> child = parent.right;
		
		grand.left = child;
		parent.right = grand;
		afterRotate(grand, parent, child);
	}
	
	private void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
		// 让 parent 成为 子树的根节点
		parent.parent = grand.parent;
		
		if (grand.isLeftChild()) {
			grand.parent.left = parent;
		} else if (grand.isRightChild()) {
			grand.parent.right = parent;
		} else { // 根节点
			root = parent;
		}
		// 更新 child 的 parent
		if (child != null) {
			child.parent = grand;
		}
		// 更新 grand 的 parent
		grand.parent = parent;
		// 更新高度
		updateHeight(grand);
		updateHeight(parent);
	}
	
	
	
	/**
	 * 节点是否平衡
	 */
	private boolean isBalanced(Node<E> node) {
		return Math.abs(((AVLNode<E>)node).balancedFactor()) <= 1;
	}
	
	/**
	 * 更新高度
	 */
	private void updateHeight(Node<E> node) {
		((AVLNode<E>)node).updateHeight();
	}
	
	@Override
	protected Node<E> createNode(E element, Node<E> parent) {
		return new AVLNode<E>(element, parent);
	}
	
	private static class AVLNode<E> extends Node<E> {
		int height = 1;
		
		public AVLNode(E element, Node<E> parent) {
			super(element, parent);
		}
		
		public int balancedFactor() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
			int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
			return leftHeight - rightHeight;
		}
		
		public void updateHeight() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
			int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
			height = 1 + Math.max(leftHeight, rightHeight);
		}
		
		public Node<E> tallerChild() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
			int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
			if (leftHeight > rightHeight) return left;
			if (leftHeight < rightHeight) return right;
			return isLeftChild() ? left : right;
		}
		
		@Override
		public String toString() {
			String parentString = "null";
			if (parent != null) {
				parentString = parent.element.toString();
			}
			return element + "_p(" + parentString + ")_h(" + height + ")";
		}
	}
	
	@Override
	public Object string(Object node) {
		return node;
	}
}
