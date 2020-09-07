package node.avl;

import java.util.Comparator;

public class BBSTree<E> extends BST<E> {
	public BBSTree() {
		this(null);
	}
	
	public BBSTree(Comparator<E> comparator) {
		super(comparator);
	}
	
	protected void rotateLeft(Node<E> grand) {
		Node<E> parent = grand.right;
		Node<E> child = parent.left;
		// 旋转
		grand.right = child;
		parent.left = grand;

		// 更新子树根节点、父节点、高度
		afterRotate(grand, parent, child);
	}
	
	protected void rotateRight(Node<E> grand) {
		Node<E> parent = grand.left;
		Node<E> child = parent.right;
		// 旋转
		grand.left = child;
		parent.right = grand;
		// 更新子树根节点、父节点、高度
		afterRotate(grand, parent, child);
	}
	
	protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
		// 让 parent 成为子树的根节点
		parent.parent = grand.parent;
		if (grand.isLeftChild()) {
			grand.parent.left = parent;
		} else if (grand.isRightChild()) {
			grand.parent.right = parent;
		} else {
			root = parent;
		}
				
		// 更新 child 的 parent
		if (child != null) {
			child.parent = grand;
		}
		// 更新 grand 的 parent
		grand.parent = parent;
	}
	
	
	protected void rotate(
			Node<E> r,
			Node<E> a, Node<E> b, Node<E> c,
			Node<E> d,
			Node<E> e, Node<E> f, Node<E> g) {
		// 让 d 成为这棵树的根节点
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
		if (a != null) {
			a.parent = b;
		}
		b.right = c;
		if (c != null) {
			c.parent = b;
		}
		
		// e f g
		f.left = e;
		if (e != null) {
			e.parent = f;
		}
		f.right = g;
		if (g != null) {
			g.parent = f;
		}
		
		// b f
		d.left = b;
		d.right = f;
		b.parent = d;
		f.parent = d;
	}

}
