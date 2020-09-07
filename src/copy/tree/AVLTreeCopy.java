package copy.tree;

import java.util.Comparator;

public class AVLTreeCopy<E> extends BSTCopy<E> {
	
	public AVLTreeCopy() {
		this(null);
	}
	
	public AVLTreeCopy(Comparator<E> comparator) {
		super(comparator);
	}
	
	
	@Override
	protected void afterAdd(Node<E> node) {
		
		// 找到高度最低的失衡父节点
		while ((node = node.parent) != null) {
			if (isBalanced(node)) {
				// 是平衡的则更新高度
				updateHeight(node);
			} else {
				// 不平衡则恢复平衡
				rebalanced(node);
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
				rebalanced(node);
			}
		}
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected Node<E> createNode(E element, Node<E> parent) {
		return new AVLNode(element, parent);
	}
	
	private void rebalanced(Node<E> grand) {
		Node<E> parent = ((AVLNode<E>)grand).tallerChild();
		Node<E> node = ((AVLNode<E>)parent).tallerChild();
		
		if (parent.isLeftChild()) { // L
			if (node.isLeftChild()) { // LL
				rotate(grand, node.left, node, node.right, parent, parent.right, grand, grand.right);
			} else { // LR
				rotate(grand, parent.left, parent, node.left, node, node.right, grand, grand.right);
			}
		} else { // R
			if (node.isLeftChild()) { // RL
				rotate(grand, grand.left, grand, node.left, node, node.right, parent, parent.right);
			} else { // RR
				rotate(grand, grand.left, grand, parent.left, parent, node.left, node, node.right);
			}
		}
	}
	
	private void rotate(
			Node<E> r,
			Node<E> a, Node<E> b, Node<E> c,
			Node<E> d,
			Node<E> e, Node<E> f, Node<E> g) {
		
		// 修改 d - 变成子树的根节点
		d.parent = r.parent;
		if (r.isLeftChild()) {
			r.parent.left = d;
		} else if (r.isRightChild()) {
			r.parent.right = d;
		} else {
			root = d;
		}
		
		// a b c 
		b.left = a;
		b.right = c;
		if (a != null) {
			a.parent = b;
		}
		if (c != null) {
			c.parent = b;
		}
		updateHeight(b);
		
		// e f g
		f.left = e;
		f.right = g;
		if (e != null) {
			e.parent = f;
		}
		if (g != null) {
			g.parent = f;
		}
		updateHeight(f);
		
		b.parent = d;
		d.left = b;
		
		f.parent = d;
		d.right = f;
		updateHeight(d);
	}
	
	
	
	
	
	
	
	
	@SuppressWarnings("unused")
	private void rebalanced1(Node<E> grand) {
		Node<E> parent = ((AVLNode<E>)grand).tallerChild();
		Node<E> child = ((AVLNode<E>)parent).tallerChild();
		
		if (parent.isLeftChild()) { // L
			if (child.isLeftChild()) { // LL
				rotateRight(grand);
			} else { // LR
				rotateLeft(parent);
				rotateRight(grand);
			}
		} else { // R
			if (child.isLeftChild()) { // RL
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
		
		afterRotate(grand, parent, child);
	}
	
	private void rotateRight(Node<E> grand) {
		Node<E> parent = grand.left;
		Node<E> child = parent.right;
		
		grand.left = child;
		parent.right = grand;
		
		afterRotate(grand, parent, child);
	}
	
	private void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
		parent.parent = grand.parent;
		if (grand.isLeftChild()) {
			grand.parent.left = parent;
		} else if (grand.isRightChild()) {
			grand.parent.right = parent;
		} else {
			root = parent;
		}
		
		if (child != null) {
			child.parent = grand;
		}
		grand.parent = parent;
		
		updateHeight(grand);
		updateHeight(parent);
	}
	
	// 平衡因子 <= 1 则为平衡
	private boolean isBalanced(Node<E> node) {
		return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
	}
	
	// 更新高度
	private void updateHeight(Node<E> node) {
		((AVLNode<E>)node).updateHeight();
	}
	
	public static class AVLNode<E> extends Node<E> {

		public int height = 1;
		
		public AVLNode(E element, Node<E> parent) {
			super(element, parent);
		}
		// 平衡因子
		public int balanceFactor() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
			int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
			return leftHeight - rightHeight;
		}
		// 更新高度
		public void updateHeight() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
			int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
			height = Math.max(leftHeight, rightHeight) + 1;
		}
		// 左右 height 比较高的节点
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
