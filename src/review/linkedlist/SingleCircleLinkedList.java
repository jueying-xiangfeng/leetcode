package review.linkedlist;

/**
 * 单循环链表
 * 
 */

public class SingleCircleLinkedList<E> extends AbstractList<E> {
	
	private Node<E> first;
	
	public static class Node<E> {
		E element;
		Node<E> next;
		
		public Node(E element, Node<E> next) {
			this.element = element;
			this.next = next;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			
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
		
		if (index == 0) {
			Node<E> newFirst = new Node<E>(element, first);
			Node<E> last = (size == 0) ? newFirst : node(size - 1);
			last.next = newFirst;
			first = newFirst;
		} else {
			Node<E> pre = node(index - 1);
 			pre.next = new Node<E>(element, pre.next);
		}
		size ++;
	}

	@Override
	public E remove(int index) {
		
		rangeCheck(index);
		Node<E> node = first;
		if (index == 0) {
			// 只有一个节点
			if (size == 1) {
				first = null;
			} else {
				Node<E> last = node(size - 1);
				first = first.next;
				last.next = first;
			}
		} else {
			Node<E> pre = node(index - 1);
			node = pre.next;
			pre.next = node.next;
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
		
		Node<E> node = first;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node;
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
