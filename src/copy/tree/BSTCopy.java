package copy.tree;

import java.util.Comparator;

@SuppressWarnings("unchecked")
public class BSTCopy<E> extends BinaryTreeCopy<E> {
	
	private Comparator<E> comparator;
	
	public BSTCopy() {
		this(null);
	}
	
	public BSTCopy(Comparator<E> comparator) {
		this.comparator = comparator;
	}
	
	
	public void add(E element) {
		elementNotNullCheck(element);
		
		if (root == null) {
			root = createNode(element, null);
			size ++;
			afterAdd(root);
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
		afterAdd(newNode);
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
		afterRemove(node);
	}
	
	public boolean contains(E element) {
		return node(element) != null;
	}
	
	/**
	 * after add remove
	 */
	protected void afterAdd(Node<E> node) {}
	protected void afterRemove(Node<E> node) {}
	
	
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
}