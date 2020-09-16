package review.set;

import review.tree.BinaryTree.Visitor;

/**
 * 
 * 集合 -- Set
 * 
 * 集合特点:
 * - 不存放重复的元素
 * - 常用于去重
 *
 */

public interface Set<E> {
	int size();
	boolean isEmpty();
	void clear();
	boolean contains(E element);
	void add(E element);
	void remove(E element);
	void traversal(Visitor<E> visitor);
}
