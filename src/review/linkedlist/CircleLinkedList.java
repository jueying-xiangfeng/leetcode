package review.linkedlist;

public class CircleLinkedList<E> extends AbstractList<E> {
	
	private Node<E> first;
	private Node<E> last;
	private Node<E> current;
	
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
	
	public void reset() {
		current = first;
	}
	
	public E next() {
		if (current == null) return null;
		
		current = current.next;
		return current.element;
	}
	
	public E remove() {
		if (current == null) return null;
		
		Node<E> next = current.next;
		E element = remove(current);
		if (size == 0) {
			current = null;
		} else {
			current = next;
		}
		return element;
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
		
		if (index == size) {
			Node<E> oldLast = last;
			last = new Node<>(oldLast, element, first);
			// 链表为空 -- node 为第一个添加的元素
			if (oldLast == null) {
				first = last;
				first.next = first;
				last.prev = first;
			} else {
				oldLast.next = last;
				first.prev = last;
			}
		} else {
			Node<E> next = node(index);
			Node<E> prev = next.prev;
			Node<E> node = new Node<>(prev, element, next);
			next.prev = node;
			prev.next = node;
			// index = 0
			if (next == first) {
				first = node;
			}
		}
		
		size ++;
	}

	@Override
	public E remove(int index) {
		rangeCheck(index);
		return remove(node(index));
	}
	
	private E remove(Node<E> node) {
		if (size == 1) {
			first = null;
			last = null;
		} else {
			Node<E> prev = node.prev;
			Node<E> next = node.next;
			prev.next = next;
			next.prev = prev;
			// index = 0
			if (node == first) {
				first = next;
			}
			// index = size - 1
			if (node == last) {
				last = prev;
			}
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
