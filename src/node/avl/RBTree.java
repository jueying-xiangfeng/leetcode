package node.avl;

import java.util.Comparator;

/**
 * 红黑树
 * 红黑树也是一种自平衡二叉搜索树
 * 
 * 红黑树必须遵守一下 5 条性质
 * 
 * 1、节点是 RED 或者 BLACK
 * 2、根节点是 BLACK
 * 3、叶子节点(外部节点，空节点) 都是 BLACK
 * 4、RED 节点的子节点都是 BLACK
 *		- RED 节点的 parent 都是 BLACK
 *		- 从根节点到叶子节点的所有路径上不能有 2 个连续的 RED 节点
 * 5、从任一节点到叶子节点的所有路径都包含相同数目的 BLACK 节点
 * 
 */

public class RBTree<E> extends BBSTree<E> {
	private static final boolean RED = false;
	private static final boolean BLACK = true;
	
	
	public RBTree() {
		this(null);
	}
	
	public RBTree(Comparator<E> comparator) {
		super(comparator);
	}
	
	
	@Override
	protected void afterAdd(Node<E> node) {
		Node<E> parent = node.parent;
		
		// 添加的是根节点 或者 上溢到达了根节点
		if (parent == null) {
			black(node);
			return;
		}
		// 共 12 中情况
		// 如果父节点为黑色，直接返回 -- 包含4中情况
		if (isBlack(parent)) return;
		
		
		// 下面 8 种情况为不满足性质4：父节点为RED -- double red
		// 叔父节点
		Node<E> uncle = parent.sibling();
		// 祖父节点
		Node<E> grand = red(parent.parent);
		
		// 下面4种情况产生上溢
		// 叔父节点是红色 【B 树节点上溢】
		if (isRed(uncle)) {
			black(parent);
			black(uncle);
			afterAdd(grand);
			return;
		}
		
		// 下面4种情况不产生上溢
		// 叔父节点不是红色
		if (parent.isLeftChild()) { // L
			if (node.isLeftChild()) { // LL
				black(parent);
			} else { // LR
				black(node);
				rotateLeft(parent);
			}
			rotateRight(grand);
		} else { // R
			if (node.isLeftChild()) { // RL
				black(node);
				rotateRight(parent);			
			} else { // RR
				black(parent);
			}
			rotateLeft(grand);	
		}
	}
	
	
	
	
	
	
	
	
	private Node<E> color(Node<E> node, boolean color) {
		if (node == null) return node;
		((RBNode<E>)node).color = color;
		return node;
	}
	
	private Node<E> red(Node<E> node) {
		return color(node, RED);
	}
	
	private Node<E> black(Node<E> node) {
		return color(node, BLACK);
	}
	
	private boolean colorOf(Node<E> node) {
		return node == null ? BLACK : ((RBNode<E>)node).color;
	}
	
	private boolean isBlack(Node<E> node) {
		return colorOf(node) == BLACK;
	}
	
	private boolean isRed(Node<E> node) {
		return colorOf(node) == RED;
	}
	
	
	@Override
	protected Node<E> createNode(E element, Node<E> parent) {
		return new RBNode<>(element, parent);
	}
	
	private static class RBNode<E> extends Node<E> {
		boolean color = RED;
		
		public RBNode(E element, Node<E> parent) {
			super(element, parent);
		}
		
		@Override
		public String toString() {
			String string = "";
			if (color == RED) {
				string = "R_";
			}
			return string + element.toString();
		}
	}
}
