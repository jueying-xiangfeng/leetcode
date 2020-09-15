package review.tree;

import java.util.Comparator;

/**
 * 二叉搜索树 - Binary Search Tree
 * 
 * (二叉搜索树、二叉排序树)
 *
 * - 任意一个节点的值都大于其左子树所有节点的值
 * - 任意一个节点的值都小于其右子树所有节点的值
 * - 它的左右子树也是一颗二叉搜索树
 * 
 * 二叉搜索树可以大大提高搜索数据的效率
 * 
 * 二叉搜索树存储的元素必须具备可比较性
 * - 比如 int、double 等
 * - 如果是自定义类型，需要制定比较方式
 * - 不允许为 null
 * 
 */

@SuppressWarnings({"unchecked" })
public class BinarySearchTree<E> extends BinaryTree<E> {
	
	protected Comparator<E> comparator;
	
	public BinarySearchTree() {
		this(null);
	}
	
	public BinarySearchTree(Comparator<E> comparator) {
		this.comparator = comparator;
	}
	
	
	/**
	 * 添加 元素
	 * 基本思路：找到新建节点的parent，然后设置 left | right
	 * @param element
	 */
	public void add(E element) {
		elementNotNullCheck(element);
		
		if (root == null) {
			root = createNode(element, null);
			size ++;
			
			afterAdd(root);
			return;
		}
		
		Node<E> parent = null;
		Node<E> node = root;
		int cmp = 0;
		
		while (node != null) {
			cmp = compare(element, node.element);
			
			parent = node;
			
			if (cmp > 0) {
				node = node.right;
			} else if (cmp < 0) {
				node = node.left;
			} else { // 相等则直接替换
				node.element = element;
				return;
			}
		}
		Node<E> newNode = createNode(element, parent);
		if (cmp > 0) {
			parent.right = newNode;
		} else {
			parent.left = newNode;
		}
		size ++;
		
		// 添加 node 之后的调整 
		afterAdd(newNode);
	}
	
	/**
	 * 删除 元素
	 * @param element
	 */
	public void remove(E element) {
		remove(node(element));
	}
	
	/**
	 * 是否包含 元素
	 * @param element
	 * @return
	 */
	public boolean contains(E element) {
		return node(element) != null;
	}
	
	/**
	 * 添加 node 之后的调整
	 * @param node - 新添加的节点
	 */
	protected void afterAdd(Node<E> node) {  }
	
	/**
	 * 删除 node 之后的调整 
	 * @param node - 被删除的节点
	 */
	protected void afterRemove(Node<E> node) {  }
	
	
	
	/**
	 * 删除 node -- 思路：
	 * 1、删除度为 2 的节点（转化为度为1的情况）
	 * - 先用前驱或者后继节点覆盖
	 * - 删除相应的前驱或者后继节点
	 * - 如果一个节点的度为2，那么他的前驱或者后继节点的度只能为0或1
	 * 
	 * 2、删除度为 1 的节点
	 * - 用子节点代替原节点的位置
	 * 		= child = node.left || child = node.right
	 * 
	 * - 用 child 代替 node 的位置
	 * 		= 如果 node 是左子节点
	 * 		= child.parent = node.parent
	 * 		= node.parent.left = child
	 * 
	 * 		= 如果 node 是右子节点
	 * 		= child.parent = node.parent
	 * 		= parent.right = child
	 * 
	 * 		= 如果 node 是根节点
	 * 		= root = child
	 * 		= child.parent = null
	 * 
	 * 3、删除叶子节点 - 直接删除
	 * 
	 * - node == node.parent.left
	 * 		= node.parent.left = null
	 * 
	 * - node == node.parent.right
	 * 		= node.parent.right = null
	 * 
	 * - node.parent == null
	 * 		= root = null
	 * 
	 * @param node
	 */
	private void remove(Node<E> node) {
		if (node == null) return;
		
		size --;
		
		if (node.hasTowChildren()) {
			// 这里使用后继节点覆盖
			Node<E> successor = successor(node);
			node.element = successor.element;
			node = successor;
		}
		// node 节点度为1或0
		Node<E> replacement = node.left != null ? node.left : node.right;
		
		// 度为1
		if (replacement != null) {
			// 更改 parent
			replacement.parent = node.parent;
			// node 为 度为1的根节点
			if (node.parent == null) {
				root = replacement;
			} else if (node.isLeftChild()) {
				node.parent.left = replacement;
			} else {
				node.parent.right = replacement;
			}
			
			afterRemove(node);
			
		} else if (node.parent == null) {
			root = null;
		} else {
			// 叶子节点
			if (node.isLeftChild()) {
				node.parent.left = null;
			} else {
				node.parent.right = null;
			}
			
			afterRemove(node);
		}
	}
	
	/**
	 * @return -- 获取 element 对应的 node
	 */
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
	 * 返回值：
	 * - =0 ：e1 = e2
	 * - >0 ：e1 > e2
	 * - <0 ：e1 < e2
	 */
	private int compare(E e1, E e2) {
		if (comparator != null) {
			return comparator.compare(e1, e2);
		}
		return ((Comparable<E>)e1).compareTo(e2);
	}
	
	/**
	 * 检查 element 是否为空
	 * @param element
	 */
	private void elementNotNullCheck(E element) {
		if (element == null) {
			throw new IllegalArgumentException("element must not be null");
		}
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toString(sb, root, "");
		return sb.toString();
	}
	private void toString(StringBuilder sb, Node<E> node, String prefix) {
		if (node == null) return;
		
		sb.append(prefix).append("【");
		sb.append(node.element).append("】").append("\n");
		
		toString(sb, node.left, prefix + "【L】");
		toString(sb, node.right, prefix + "【R】");
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
