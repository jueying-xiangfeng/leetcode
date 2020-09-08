package review.linkedlist;

public class LinkedList<E> extends AbstractList<E> {
	
	private Node<E> first;
	private Node<E> last;
	
	public static class Node<E> {
		E element;
		Node<E> prev;
		Node<E> next;
		public Node(Node<E> prev, E element, Node<E> next) {
			this.element = element;
			this.prev = prev;
			this.next = next;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			if (prev != null) {
				sb.append(prev.element);
			}
			sb.append("_");
			sb.append(element);
			sb.append("_");
			if (next != null) {
				sb.append(next.element);
			}
			return sb.toString();
		}
	}
	
	@Override
	public void clear() {
		first = null;
		last = null;
		size = 0;
	}

	@Override
	public E get(int index) {
		return node(index).element;
	}

	@Override
	public E set(int index, E element) {
		Node<E> node = node(index);
		E old = node.element;
		node.element = element;
		return old;
	}

	@Override
	public void add(int index, E element) {
		
		rangeCheckForAdd(index);
		
		// 链表尾部插入节点 -- 包括链表为空的情况
		if (index == size) {
			Node<E> oldLast = last;
			last = new Node<>(oldLast, element, null);
			// 此时链表为空
			if (oldLast == null) {
				first = last;
			} else {
				oldLast.next = last;
			}
		} else {
			Node<E> next = node(index);
			Node<E> prev = next.prev;
			Node<E> node = new Node<>(prev, element, next);
			next.prev = node;
			
			// index = 0
			if (prev == null) {
				first = node;
			} else {
				prev.next = node;
			}
		}
		
		size ++;
	}

	@Override
	public E remove(int index) {
		
		rangeCheck(index);
		
		Node<E> node = node(index);
		Node<E> prev = node.prev;
		Node<E> next = node.next;
		
		// index = 0
		if (prev == null) {
			first = next;
		} else {
			prev.next = next;
		}
		
		// size - 1
		if (next == null) {
			last = prev;
		} else {
			next.prev = prev;
		}
		
		size --;
		return node.element;
	}

	@Override
	public int indexOf(E element) {
		
		Node<E> node = first;
		if (element == null) {
			for (int i = 0; i < size; i++) {
				if (node.element == null) return i;
				node = node.next;
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (element.equals(node.element)) return i;
				node = node.next;
			}
		}
		return ELEMENT_NOT_FOUND;
	}
	
	
	/**
	 * 获取 index 位置对应的节点对象
	 * @param index
	 * @return
	 */
	private Node<E> node(int index) {
		rangeCheck(index);
		
		if (index < size >> 1) {
			Node<E> node = first;
			for (int i = 0; i < index; i++) {
				node = node.next;
			}
			return node;
		} else {
			Node<E> node = last;
			for (int i = size - 1; i > index; i--) {
				node = node.prev;
			}
			return node;	
		}
	}
	
	
	@Override
	public String toString() {
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("size=").append(size).append(", [");
		Node<E> node = first;
		
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				stringBuilder.append(", ");
			}
			stringBuilder.append(node);
			
			node = node.next;
		}
		stringBuilder.append("]");
		return stringBuilder.toString();
	}
}
