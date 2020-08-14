package com.mj;

import com.mj.AbstractList;

@SuppressWarnings("unused")
public class singleCircleLinkedList<E> extends AbstractList<E> {
	
	private Node<E> first;
	
	// 内部类
	private static class Node<E> {
		E element;
		Node<E> next;
		public Node(E element, Node<E> next) {
			this.element = element;
			this.next = next;
		}
	}

	@Override
	public void clear() {
		size = 0;
		first = null;
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
		if (index == 0) {
			Node<E> last = node(index);
			first = new Node<>(element, first);
			last.next = first;
		} else {
			Node<E> preNode = node(index - 1);
			preNode.next = new Node<>(element, preNode.next);
		}
		size++;
	}

	@Override
	public E remove(int index) {
		
		Node<E> node = first;
		if (index == 0) {
			if (size == 1) {
				first = null;
			} else {
				Node<E> last = node(size - 1);
				first = first.next;
				last.next = first;
			}
		} else {
			Node<E> preNode = node(index - 1);
			node = preNode.next;
			preNode.next = node.next;
		}
		size--;
		return node.element;
	}

	@Override
	public int indexOf(E element) {
		
		if (element == null) {
			Node<E> node = first;
			for (int i = 0; i < size; i++) {
				if (node.element == null) {
					return i;
				}
				node = node.next;
			}
		} else {
			Node<E> node = first;
			for (int i = 0; i < size; i++) {
				if (node.element.equals(element)) {
					return i;
				}
				node = node.next;
			}
		}
		return ELEMENT_NOT_FOUND;
	}
	
	/**
	 * 获取 index 对应 node
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
		// TODO Auto-generated method stub
		return super.toString();
	}
}
