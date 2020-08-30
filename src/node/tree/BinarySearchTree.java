package node.tree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

import node.tree.printer.BinaryTreeInfo;

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
public class BinarySearchTree<E> implements BinaryTreeInfo {
	
	private int size;
	private Node<E> root;
	private Comparator<E> comparator;
	
	public BinarySearchTree() {
		this(null);
	}
	
	public BinarySearchTree(Comparator<E> comparator) {
		this.comparator = comparator;
	}
	
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
	
	public void add(E element) {
		elementNotNullCheck(element);
		
		// 添加第一个节点
		if (root == null) {
			root = new Node<>(element, null);
			size ++;
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
		Node<E> newNode = new Node<>(element, parent);
		if (cmp > 0) {
			parent.right = newNode;
		} else {
			parent.left = newNode;
		}
		size ++;
	}
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toString(root, sb, "");
		return sb.toString();
	}
	
	private void toString(Node<E> node, StringBuilder sb, String prefix) {
		if (node == null) return;
		
		toString(node.left, sb, prefix + "L--");
		sb.append(prefix).append(node.element).append("\n");
		toString(node.right, sb, prefix + "R--");
	}
	
	
	private static class Node<E> {
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
	}

	/**
	 * 二叉树遍历
	 * 
	 * - 前序遍历 (Preorder Traversal)
	 * - 中序遍历 (Inorder Traversal)
	 * - 后序遍历 (Postorder Traversal)
	 * - 层序遍历 (Level Order Traversal)
	 * 
	 * 
	 * - 前序遍历：树状结构展示 (注意左右子树的顺序)
	 * - 中序遍历：二叉搜索树的中序遍历按升序或者降序处理节点
	 * - 后序遍历：适用于一些先子后父的操作
	 * - 层序遍历：计算二叉树高度、判断一棵树是否为完全二叉树
	 * 
	 */
	
	/**
	 * 前序遍历
	 * 
	 * 遍历顺序：
	 * 根节点 -> 前序遍历左子树 -> 前序遍历右子树
	 */
	public void preorderTraversal() {
		preorderTraversal(root);
	}
	private void preorderTraversal(Node<E> node) {
		if (node == null) { return; }
		
		System.out.println(node.element);
		preorderTraversal(node.left);
		preorderTraversal(node.right);
	}
	
	/**
	 * 中序遍历
	 * 
	 * 遍历顺序：
	 * 中序遍历左子树 -> 根节点 -> 中序遍历右子树
	 * 
	 * 二叉搜索树的中序遍历结果是升序或者降序的
	 */
	public void inorderTraversal() {
		inorderTraversal(root);
	}
	private void inorderTraversal(Node<E> node) {
		if (node == null) { return; }
		
		inorderTraversal(node.left);
		System.out.println(node.element);
		inorderTraversal(node.right);
	}
	
	/**
	 * 后序遍历
	 * 
	 * 遍历顺序：
	 * 后序遍历左子树 -> 后序遍历右子树 -> 根节点
	 * 
	 */
	public void postorderTraversal() {
		postorderTraversal(root);
	}
	private void postorderTraversal(Node<E> node) {
		if (node == null) { return; }
		
		postorderTraversal(node.left);
		postorderTraversal(node.right);
		System.out.println(node.element);
	}
	
	/**
	 * 层序遍历
	 * 
	 * 遍历顺序：
	 * 从上到下，从左到右一次访问每一个节点
	 * 
	 */
	public void levelOrderTraversal() {
		levelOrderTraversal(root);
	}
	private void levelOrderTraversal(Node<E> node) {
		if (node == null) { return; }
		
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(node);
		
		while (!queue.isEmpty()) {
			Node<E> tmpNode = queue.poll();
			System.out.println(tmpNode.element);
			
			if (tmpNode.left != null) {
				queue.offer(tmpNode.left);
			}
			if (tmpNode.right != null) {
				queue.offer(tmpNode.right);
			}
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
	private Node<E> predecessor(Node<E> node) {
		
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
	private Node<E> successor(Node<E> node) {
		
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
