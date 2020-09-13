package review.queue;

import review.linkedlist.LinkedList;
import review.linkedlist.List;

public class Deque<E> {
	private List<E> list = new LinkedList<>();
	
	public int size() {
		return list.size();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public void clear() {
		list.clear();
	}
	
	/**
	 * 队尾入队
	 * @param element
	 */
	public void enQueueRear(E element) {
		list.add(element);
	}
	
	/**
	 * 队头入队
	 * @param element
	 */
	public void enQueueFront(E element) {
		list.add(0, element);
	}
	
	/**
	 * 队尾出队
	 * @param element
	 */
	public E deQueueRear() {
		return list.remove(list.size() - 1);
	}
	
	/**
	 * 队头出队
	 * @param element
	 */
	public E deQueueFront() {
		return list.remove(0);
	}
	
	public E front() {
		return list.get(0);
	}
	
	public E rear() {
		return list.get(list.size() - 1);
	}
}
