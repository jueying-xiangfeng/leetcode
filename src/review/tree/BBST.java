package review.tree;

import java.util.Comparator;

public class BBST<E> extends BinarySearchTree<E> {
	
	public BBST() {
		this(null);
	}
	
	public BBST(Comparator<E> comparator) {
		super(comparator);
	}
	
	
	// 向左旋转
	protected void rotateLeft(Node<E> grand) {
		Node<E> parent = grand.right;
		Node<E> child = parent.left;
		
		grand.right = child;
		parent.left = grand;
		afterRotate(grand, parent, child);
	}
	
	// 向右旋转
	protected void rotateRight(Node<E> grand) {
		Node<E> parent = grand.left;
		Node<E> child = parent.right;
		
		grand.left = child;
		parent.right = grand;
		afterRotate(grand, parent, child);
	}
	
	protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
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
	}
	
}
