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
	
	/**
	 * 思路：
	 * - 最后被删除的元素都在叶子节点中
	 * - 叶子节点的四种情况和添加时一样：
	 * 
	 * - 红黑(p)红
	 * - 红黑(p)
	 * - 黑(p)红
	 * - 黑(p)
	 * 
	 * 
	 * 删除的情况：
	 * 1、删除的是红节点
	 * - 直接删除，不做任何处理
	 * 
	 * 2、删除的是黑色节点
	 * 
	 * 2.1、删除的黑色节点有两个红色节点 
	 * - 不做处理，因为对于二叉搜索树来说，这种情况是删除度为2的节点，所以最终会用子节点代替，最终删除的还是叶子节点
	 * 
	 * 2.2、删除的黑色节点有一个红色节点
	 * - 这种情况从二叉搜索树的角度来说，是用红色子节点来代替黑色节点
	 * - 所以处理方式是：将替代的红色子节点染成黑色即可
	 * 
	 * 2.3、删除的黑色节点是叶子节点 -- 这种情况会导致下溢
	 * 
	 * 2.3.1、删除的black叶子节点的 sibling 节点是 black
	 * 
	 * 2.3.1.1、sibling 节点至少有一个红色节点
	 * 从 B 树的角度来看就是兄弟节点数量足够，可以借一个过来
	 * - 根据情况进行旋转操作 (LL/RR/LR/RL)
	 * - 旋转后的中心节点继承 parent 节点的颜色
	 * - 旋转后的左右节点染成 black
	 * 
	 * 2.3.1.2、sibling 没有一个 red 子节点
	 * - 将 sibling 染成 red、parent 染成 black 即可
	 * - 这里要注意，如果 parent 为黑色，会导致parent也下溢，只需要把parent当做被删除的节点处理即可
	 * 
	 * 2.3.2、删除的black叶子节点的 sibling 节点为 red
	 * - sibling 染成黑色，parent 染成红色，进行旋转
	 * - 旋转后又回到 sibling 为 black 的情况
	 * 
	 */
	
	@Override
	protected void afterRemove(Node<E> node) {
		// 被删除的节点是红色 || 用以代替删除节点的子节点是红色
		if (isRed(node)) {
			black(node);
			return;
		}
		
		Node<E> parent = node.parent;
		// 删除的是根节点
		if (parent == null) return;
		
		// 删除的黑色节点下溢
		// 判断被删除的节点是左还是右
		boolean left = parent.left == null || node.isLeftChild();
		Node<E> sibling = left ? parent.right : parent.left;
		// 左右处理完全对称 -- 这里先处理好右边的情况，然后在处理左边
		if (left) { // 被删除的节点在左边，兄弟节点在右边
			if (isRed(sibling)) { // 兄弟节点是红色
				black(sibling);
				red(parent);
				rotateLeft(parent);
				// 更换兄弟节点
				sibling = parent.right;
			}
			// 以下兄弟节点必然都是黑色
			if (isBlack(sibling.left) && isBlack(sibling.right)) {
				// 兄弟节点没有一个红色节点，下溢，父节点要向下跟兄弟节点合并
				boolean parentBlack = isBlack(parent);
				black(parent);
				red(sibling);
				if (parentBlack) {
					afterRemove(parent, null);
				}
			} else {
				// 兄弟节点至少有一个红色子节点，向兄弟节点借元素
				// 兄弟节点的左边是黑色 (黑(p)红的情况，左边为空，即为black)，兄弟要先旋转 - 统一转成 LL 情况
				if (isBlack(sibling.right)) {
					rotateRight(sibling);
					sibling = parent.right;
				}
				
				color(sibling, colorOf(parent));
				black(sibling.right);
				black(parent);
				rotateLeft(parent);
			}
		} else { // 被删除的节点在右边，兄弟节点在左边
			if (isRed(sibling)) { // 兄弟节点是红色
				black(sibling);
				red(parent);
				rotateRight(parent);
				// 更换兄弟节点
				sibling = parent.left;
			}
			// 以下兄弟节点必然都是黑色
			if (isBlack(sibling.left) && isBlack(sibling.right)) {
				// 兄弟节点没有一个红色节点，下溢，父节点要向下跟兄弟节点合并
				boolean parentBlack = isBlack(parent);
				black(parent);
				red(sibling);
				if (parentBlack) {
					afterRemove(parent, null);
				}
			} else {
				// 兄弟节点至少有一个红色子节点，向兄弟节点借元素
				// 兄弟节点的左边是黑色 (黑(p)红的情况，左边为空，即为black)，兄弟要先旋转 - 统一转成 LL 情况
				if (isBlack(sibling.left)) {
					rotateLeft(sibling);
					sibling = parent.left;
				}
				
				color(sibling, colorOf(parent));
				black(sibling.left);
				black(parent);
				rotateRight(parent);
			}
		}		
	}
	
//	@Override
//	protected void afterRemove(Node<E> node, Node<E> replacement) {
//		// 被删除的节点是红色，不作处理
//		if (isRed(node)) return;
//		
//		// 用以代替的节点是红色
//		if (isRed(replacement)) {
//			black(replacement);
//			return;
//		}
//		
//		Node<E> parent = node.parent;
//		// 删除的是根节点
//		if (parent == null) return;
//		
//		// 删除的黑色节点下溢
//		// 判断被删除的节点是左还是右
//		boolean left = parent.left == null || node.isLeftChild();
//		Node<E> sibling = left ? parent.right : parent.left;
//		// 左右处理完全对称 -- 这里先处理好右边的情况，然后在处理左边
//		if (left) { // 被删除的节点在左边，兄弟节点在右边
//			if (isRed(sibling)) { // 兄弟节点是红色
//				black(sibling);
//				red(parent);
//				rotateLeft(parent);
//				// 更换兄弟节点
//				sibling = parent.right;
//			}
//			// 以下兄弟节点必然都是黑色
//			if (isBlack(sibling.left) && isBlack(sibling.right)) {
//				// 兄弟节点没有一个红色节点，下溢，父节点要向下跟兄弟节点合并
//				boolean parentBlack = isBlack(parent);
//				black(parent);
//				red(sibling);
//				if (parentBlack) {
//					afterRemove(parent, null);
//				}
//			} else {
//				// 兄弟节点至少有一个红色子节点，向兄弟节点借元素
//				// 兄弟节点的左边是黑色 (黑(p)红的情况，左边为空，即为black)，兄弟要先旋转 - 统一转成 LL 情况
//				if (isBlack(sibling.right)) {
//					rotateRight(sibling);
//					sibling = parent.right;
//				}
//				
//				color(sibling, colorOf(parent));
//				black(sibling.right);
//				black(parent);
//				rotateLeft(parent);
//			}
//		} else { // 被删除的节点在右边，兄弟节点在左边
//			if (isRed(sibling)) { // 兄弟节点是红色
//				black(sibling);
//				red(parent);
//				rotateRight(parent);
//				// 更换兄弟节点
//				sibling = parent.left;
//			}
//			// 以下兄弟节点必然都是黑色
//			if (isBlack(sibling.left) && isBlack(sibling.right)) {
//				// 兄弟节点没有一个红色节点，下溢，父节点要向下跟兄弟节点合并
//				boolean parentBlack = isBlack(parent);
//				black(parent);
//				red(sibling);
//				if (parentBlack) {
//					afterRemove(parent, null);
//				}
//			} else {
//				// 兄弟节点至少有一个红色子节点，向兄弟节点借元素
//				// 兄弟节点的左边是黑色 (黑(p)红的情况，左边为空，即为black)，兄弟要先旋转 - 统一转成 LL 情况
//				if (isBlack(sibling.left)) {
//					rotateLeft(sibling);
//					sibling = parent.left;
//				}
//				
//				color(sibling, colorOf(parent));
//				black(sibling.left);
//				black(parent);
//				rotateRight(parent);
//			}
//		}
//	}
	
	
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
