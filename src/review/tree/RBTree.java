package review.tree;

import java.util.Comparator;

/**
 * 完美匹配【2，4】树
 * 
 * 红黑树性质：
 * 
 * 1、节点是 red 或者 black
 * 2、根节点是 black
 * 3、叶子节点(外部节点 -- 空节点)都是 black
 * 4、
 * - red 节点的子节点都是 black
 * - red 节点 parent 都是 black
 * - 从根节点到叶子节点的所有路径上不能有 2 个连续的 red 节点
 * 5、从任一节点到叶子节点的所有路径都包含相同数目的 black 节点
 * 
 */

@SuppressWarnings("unused")
public class RBTree<E> extends BBST<E> {
	private static final boolean RED = false;
	private static final boolean BLACK = true;
	
	public RBTree() {
		this(null);
	}
	
	public RBTree(Comparator<E> comparator) {
		super(comparator);
	}
	
	/**
	 * 思路：
	 * - 新添加节点默认染成 red，能尽快满足性质
	 * - 新添加的节点必然在叶子节点中
	 * 
	 * parent 有四种情况
	 * - 红 黑(p) 红
	 * - 黑(p) 红
	 * - 红 黑(p)
	 * - 黑(p)
	 * 可以看到新添加的节点共有 12 各个位置，也就是 12 种情况
	 * 
	 * 1、parent 为 black -- 包含 4 种情况
	 * - 满足 4 阶 B 树性质，不做额外处理
	 * 
	 * 2、uncle 不是红色节点
	 * - LL/RR 
	 * 		= parent 染成 black，grand 染成 red
	 * 		= grand 进行单旋操作(LL:右旋  RR:左旋)
	 * 
	 * - LR/RL
	 * 		= 自己染成 black，grand 染成 red
	 * 		= (LR:parent 左旋，grand 右旋    RL:parent 右旋，grand 左旋)
	 * 
	 * 3、uncle 是红色节点 - 这种情况会产生上溢
	 * - parent、uncle 都染成 black
	 * - grand 染成红色 & 向上合并 (当做新添加的节点处理)
	 * 
	 */
	@Override
	protected void afterAdd(Node<E> node) {
		Node<E> parent = node.parent;
		
		// 添加的是根节点 || 上溢达到了根节点
		if (parent == null) {
			black(node);
			return;
		}
		// 父节点为 black，不做额外处理
		if (isBlack(parent)) return;
		// 叔父节点
		Node<E> uncle = parent.sibling();
		// 祖父节点
		Node<E> grand = red(parent.parent);
		
		if (isRed(uncle)) { // 叔父节点为红色 - 【B树节点上溢】
			black(parent);
			black(uncle);
			// 把染红的祖父节点当做新添加的节点
			afterAdd(grand);
			return;
		}
		
		// 最后一种情况 -- 叔父节点为 black(黑红、红黑)
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
	
	@Override
	protected void afterRemove(Node<E> node) {
		// TODO Auto-generated method stub
		super.afterRemove(node);
	}
	
	
	// 获取 node 颜色
	private boolean colorOf(Node<E> node) {
		return node == null ? BLACK : ((RBNode<E>)node).color;
	}
	
	private boolean isBlack(Node<E> node) {
		return colorOf(node) == BLACK;
	}
	
	private boolean isRed(Node<E> node) {
		return colorOf(node) == RED;
	}
	
	// 染色
	private RBNode<E> color(Node<E> node, boolean color) {
		if (node != null) ((RBNode<E>)node).color = color;
		return ((RBNode<E>)node);
	}
	
	private RBNode<E> red(Node<E> node) {
		return color(node, RED);
	}
	
	private RBNode<E> black(Node<E> node) {
		return color(node, BLACK);
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected Node<E> createNode(E element, Node<E> parent) {
		return new RBNode(element, parent);
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
	
	@Override
	public Object string(Object node) {
		return node;
	}
}
