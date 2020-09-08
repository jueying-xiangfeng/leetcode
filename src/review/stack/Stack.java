package review.stack;

import review.linkedlist.LinkedList;

public class Stack<E> {
	
   	private LinkedList<E> list;
   	
   	public Stack() {
		list = new LinkedList<>();
	}
   	
   	public void clear() {
		list.clear();
	}
   	
   	public int size() {
		return list.size();
	}
   	
   	public boolean isEmpty() {
		return list.isEmpty();
	}
   	
   	public void push(E element) {
		list.add(element);
	}
   	
   	public E pop() {
		return list.remove(list.size() - 1);
	}
   	
   	public E top() {
		return list.get(list.size() - 1);
	}
	
//   	@Override
//   	public String toString() {
//   		StringBuilder sb = new StringBuilder();
//   		sb.append(list);
//   		return sb.toString();
//   	}
}
