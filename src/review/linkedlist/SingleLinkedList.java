package review.linkedlist;

public class SingleLinkedList<E> extends AbstractList<E> {

	
	private Node<E> first;

	
	public static class Node<E> {
		E element;
		Node<E> next;
		
		public Node(E element, Node<E> next) {
			this.element = element;
			this.next = next;
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
			first = new Node<E>(element, first);
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
			first = first.next;
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
			stringBuilder.append(node.element);
			
			node = node.next;
		}
		stringBuilder.append("]");
		return stringBuilder.toString();
	}
}
