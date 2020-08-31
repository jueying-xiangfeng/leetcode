package node.avl;

import java.util.Comparator;

/**
 * AVL 树是最早发明的自平衡二叉搜索树之一
 *
 * 
 * 1、平衡因子：Balance Factor - 某结点的左右子树的高度差
 * 
 * 2、AVL 树的特点
 * 
 * - 每个节点的平衡因子只可能是 1 0 -1 (绝对值 <= 1，如果超过 1，称之为“失衡”)
 * - 每个节点的左右子树高度差不超过 1
 * - 搜索、添加、删除 的时间复杂度是 O(logn)
 * 
 */
public class AVLTree<E> extends BST<E> {
	public AVLTree() {
		this(null);
	}
	
	public AVLTree(Comparator<E> comparator) {
		super(comparator);
	}
	
	@Override
	protected void afterAdd(Node<E> node) {
		// 找最近的失衡父节点
		while (((node = node.parent) != null)) {
			// 判断是否失衡
			if (isBalanced(node)) {
				// 更新高度
				updateHeight(node);
			} else {
				// 恢复平衡 -- 此节点恢复后，整棵树就能保证恢复平衡
				rebalance(node);
				break;
			}
		}
	};
	
	
	private void updateHeight(Node<E> node) {
		((AVLNode<E>)node).updateHeight();
	}
	
	/**
	 * 恢复平衡，grand -- 高度最低的哪个不平衡节点
	 * @param grand
	 */
	@SuppressWarnings("unchecked")
	private void rebalance(Node<E> grand) {
		Node<E> parent = ((AVLNode)grand).tallerChild();
		Node<E> node = ((AVLNode)parent).tallerChild();
		
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
	
	private void rotateLeft(Node<E> grand) {
		Node<E> parent = grand.right;
		Node<E> child = parent.left;
		grand.right = child;
		parent.left = grand;
	}
	
	private void rotateRight(Node<E> grand) {
		Node<E> parent = grand.left;
		Node<E> child = parent.right;
		grand.left = child;
		parent.right = grand;
	}
	
	
	@Override
	protected Node<E> createNode(E element, Node<E> parent) {
		return new AVLNode<>(element, parent);
	};
	
	// 当前节点是否平衡
	private boolean isBalanced(Node<E> node) {
		return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
	}
	
	private static class AVLNode<E> extends Node<E> {
		int height = 1;
		
		public AVLNode(E element, Node<E> parent) {
			super(element, parent);
		}
		// 平衡因子
		public int balanceFactor() {
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
			if (leftHeight > rightHeight) {
				return left;
			}
			if (leftHeight < rightHeight) {
				return right;
			}
			return isLeftChild() ? left : right;
		}
	}
	
}
