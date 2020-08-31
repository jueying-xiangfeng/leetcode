package node.avl;

import java.util.Comparator;

/**
 * 二叉搜索树 Binary Search Tree -- BST
 * 
 * (二叉查找树、二叉排序树)
 * 
 * - 任意一个节点的值都大于其左子树所有节点的值
 * - 任意一个节点的值都小于其右子树所有节点的值
 * - 他的左右子树也是一颗二叉搜索树 
 * 
 * 二叉搜索树可以大大提高搜索数据的效率
 * 
 * 二叉搜索树存储的元素必须具备可比较性
 * - 比如 int、double 等
 * - 如果是自定义类型，需要制定比较方式
 * - 不允许为 null
 * 
 */

@SuppressWarnings("unchecked")
public class BST<E> extends BinaryTree<E> {
	private Comparator<E> comparator;
	
	public BST() {
		this(null);
	}
	
	public BST(Comparator<E> comparator) {
		this.comparator = comparator;
	}
	
	public void add(E element) {
		elementNotNullCheck(element);
		
		// 添加第一个节点
		if (root == null) {
			root = createNode(element, null);
			size ++;
			
			// 新添加节点之后的处理
			afterAdd(root);
			return;
		}
		// 添加的不是第一个节点
		Node<E> node = root;
		Node<E> parent = null;
		int cmp = 0;
		
		while (node != null) {
			cmp = compare(element, node.element); 
			parent = node;
			if (cmp > 0) {
				node = node.right;
			} else if (cmp < 0) {
				node = node.left;
			} else { // 相等
				node.element = element;
				return;
			}
		}
		
		// 看看插入到父节点的哪个位置
		Node<E> newNode = createNode(element, parent);
		if (cmp > 0) {
			parent.right = newNode;
		} else {
			parent.left = newNode;
		}
		size ++;
		
		// 新添加节点之后的处理
		afterAdd(root);
	}
	/**
	 * 添加 node 之后的调整
	 * @param node
	 */
	protected void afterAdd(Node<E> node) { }
	
	/**
	 * 删除
	 * 
	 * 1、删除度为2的节点
	 * - 先用前驱或者后继节点的值覆盖原节点的值
	 * - 删除相应的前驱或者后继节点
	 * - 如果一个节点的度为2，那么他的前去或者后继节点的度只能为0或1
	 * 
	 * 2、删除度为1的节点
	 * - 用子节点代替原节点的位置
	 * 		- child 是 node.left  或者  child 是 node.right
	 * 
	 * - 用 child 代替 node 的位置
	 * 		- 如果 node 是左子节点
	 * 		- child.parent = node.parent
	 * 		- node.parent.left = child
	 * 	
	 * 		- 如果 node 是右子节点
	 * 		- child.parent = node.parent
	 * 		- child.parent.right = child
	 * 
	 * 		- 如果 node 是根节点
	 * 		- root = child
	 * 		- child.parent = null
	 * 
	 * 3、删除叶子节点
	 * 
	 * 直接删除
	 * 
	 * - node == node.parent.left
	 * 		- node.parent.left = null
	 * 
	 * - node == node.parent.right
	 * 		- node.parent.right = null
	 * 
	 * - node.parent == null
	 * 		- root = null
	 * 
	 * 
	 * @param element
	 */
	public void remove(E element) {
		remove(node(element));
	}
	
	public boolean contains(E element) {
		return node(element) != null;
	}
	
	private void remove(Node<E> node) {
		
		if (node == null) return;
		
		size --;
		
		// 度为2的节点
		if (node.left != null && node.right != null) {
			// 找到后继节点
			Node<E> s = successor(node);
			// 使用后继节点的值覆盖当前节点
			node.element = s.element;
			node = s;
		}
		// 删除 node 节点，node 的度必然为 1 或 0
		Node<E> replacement = node.left != null ? node.left : node.right;
		
		// node 是度为1的节点
		if (replacement != null) {
			// 更改 parent
			replacement.parent = node.parent;
			// 更改 parent 的 left、right 指向
			if (node.parent == null) {
				root = replacement;
			} else if (node == node.parent.left) {
				node.parent.left = replacement;
			} else {
				node.parent.right = replacement;
			}
		} 
		// node 是叶子节点 并且是根节点
		else if (node.parent == null) {
			root = null;
		}
		// node 是叶子节点。但不是根节点
		else {
			if (node == node.parent.left) {
				node.parent.left = null;
			} else {
				node.parent.right = null;
			}
		}
	}
	
	private Node<E> node(E element) {
		Node<E> node = root;
		int cmp = 0;
		while (node != null) {
			cmp = compare(element, node.element);
			if (cmp == 0) return node;
			
			if (cmp > 0) {
				node = node.right;
			} else {
				node = node.left;
			}
		}
		return null;
	}
	
	
	/**
	 * @return
	 * 返回值 = 0: e1 = e2
	 * 返回值 > 0: e1 > e2
	 * 返回值 < 0: e1 < e2
	 */
	private int compare(E e1, E e2) {
		if (comparator != null) {
			return comparator.compare(e1, e2);
		}
		return ((Comparable<E>)e1).compareTo(e2);
	}
	
	private void elementNotNullCheck(E element) {
		if (element == null) {
			throw new IllegalArgumentException("element must not be null");
		}
	}
	
	
}
