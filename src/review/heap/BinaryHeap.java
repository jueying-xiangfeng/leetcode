package review.heap;

import java.util.Comparator;
import node.tree.printer.BinaryTreeInfo;

@SuppressWarnings("unchecked")
public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo, com.mj.printer.BinaryTreeInfo {

	private E[] elements;
	private static final int DEFAULT_CAPACITY = 10;
	
	
	public BinaryHeap(E[] elements, Comparator<E> comparator) {
		super(comparator);
		
		if (elements == null || elements.length == 0) {
			this.elements = (E[]) new Object[DEFAULT_CAPACITY];
		} else {
			size = elements.length;
			int capacity = Math.max(elements.length, DEFAULT_CAPACITY);
			this.elements = (E[]) new Object[capacity];
			for (int i = 0; i < elements.length; i++) {
				this.elements[i] = elements[i];
			}
			heapify();
		}
	}
	
	public BinaryHeap(E[] elements)  {
		this(elements, null);
	}
	
	public BinaryHeap(Comparator<E> comparator) {
		this(null, comparator);
	}
	
	public BinaryHeap() {
		this(null, null);
	}

	@Override
	public void clear() {
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
	}

	@Override
	public void add(E element) {
		elementNotNullCheck(element);
		ensureCapacity(size + 1);
		elements[size++] = element;
		siftUp(size - 1);
	}

	@Override
	public E get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E remove() {
		emptyCheck();
		
		int lastIndex = --size;
		E root = elements[lastIndex];
		elements[0] = elements[lastIndex];
		elements[lastIndex] = null;
		
		siftDown(0);
		return root;
	}

	@Override
	public E replace(E element) {
		elementNotNullCheck(element);

		E root = null;
		if (size == 0) {
			elements[0] = element;
			size ++;
		} else {
			root = elements[0];
			elements[0] = element;
			siftDown(0);
		}
		return root;
	}
	
	
	// 批量建堆
	private void heapify() {
		
		// 自上而下的上滤
//		for (int i = 1; i < size; i++) {
//			siftUp(i);
//		}
		
		// 自下而上的下滤
		for (int i = (size >> 1) - 1; i >= 0; i--) {
			siftDown(i);
		}
	}
	
	
	
	// 下滤
	private void siftDown(int index) {
		
		E element = elements[index];
		int half = size >> 1;
		// 第一个叶子节点的索引 == 非叶子节点的数量
		// index < 第一个叶子节点的索引
		// 必须保证index位置是非叶子节点
		while (index < half) {
			// index的节点有2种情况
			// 1、只有左子节点
			// 2、同时有左右子节点
			
			// 左子节点
			int childIndex = (index << 1) + 1;
			E child = elements[childIndex];
			
			// 右子节点
			int rightIndex = childIndex + 1;
			
			// 选出左右子节点最大的那个
			if (rightIndex < size && compare(elements[rightIndex], child) > 0) {
				child = elements[childIndex = rightIndex];
			}
			
			if (compare(element, child) >= 0) break;
			
			// 将子节点存放到index位置
			elements[index] = child;
			index = childIndex;
		}
		elements[index] = element;
	}
	
	
	// 上滤
	private void siftUp(int index) {
//		E e = elements[index];
//		// node > 父节点 -- 与父节点进行交换
//		while (index > 0) {
//			int pIndex = (index - 1) >> 1;
//			E p = elements[pIndex]; 
//			if (compare(e, p) <= 0) return;
//			
//			E tmp = elements[index];
//			elements[index] = p;
//			elements[pIndex] = tmp;
//			
//			// 重新赋值 index
//			index = pIndex;
//		}
		
		E e = elements[index];
		// node > 父节点 -- 与父节点进行交换
		while (index > 0) {
			int pIndex = (index - 1) >> 1;
			E p = elements[pIndex]; 
			if (compare(e, p) <= 0) break;
			
			elements[index] = p;
			// 重新赋值 index
			index = pIndex;
		}
		elements[index] = e;
	}
	
	// 扩容
	private void ensureCapacity(int capacity) {
		int oldCapacity = elements.length;
		if (oldCapacity >= capacity) return;
		
		// 新容量为旧容量的1.5倍
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
	}
	
	private void emptyCheck() {
		if (size == 0) {
			throw new IndexOutOfBoundsException("Heap is empty");
		}
	}
	
	private void elementNotNullCheck(E element) {
		if (element == null) {
			throw new IllegalArgumentException("element must not be null");
		}
	}


	@Override
	public Object root() {
		return 0;
	}


	@Override
	public Object left(Object node) {
		int index = ((int)node << 1) + 1;
		return index >= size ? null : index;
	}


	@Override
	public Object right(Object node) {
		int index = ((int)node << 1) + 2;
		return index >= size ? null : index;
	}


	@Override
	public Object string(Object node) {
		return elements[(int)node];
	}
	
}
