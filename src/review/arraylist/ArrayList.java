package review.arraylist;

/**
 * 数组是一种顺序存储的线性表，所有元素的内存地址是连续的
 * 
 * 
 */

@SuppressWarnings("unchecked")
public class ArrayList<E> {
	/**
	 * 元素数量
	 */
	private int size;
	/**
	 * 所有元素
	 */
	private E[] elements;
	
	private static final int DEFAULT_CAPACITY = 10;
	private static final int ELEMENT_NOT_FOUND = -1;
	
	public ArrayList(int capacity) {
		capacity = (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
		elements = (E[]) new Object[capacity];
	}
	
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	
	
	/**
	 * 元素的数量
	 * @return
	 */
	public int size() {
		return size;
	}
	
	/**
	 * 是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * 清空数组
	 */
	public void clear() {
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
	}
	
	/**
	 * 是否包含某个元素
	 * @param element
	 * @return
	 */
	public boolean contains(E element) {
		return indexOf(element) != ELEMENT_NOT_FOUND;
	}
	
	/**
	 * 获取 index 位置的元素
	 * @param index
	 * @return
	 */
	public E get(int index) {
		rangeCheck(index);
		return elements[index];
	}
	
	/**
	 * 设置 index 位置的元素
	 * @param index
	 * @param element
	 * @return 原来位置的元素
	 */
	public E set(int index, E element) {
		rangeCheck(index);
		E old = elements[index];
		elements[index] = element;
		return old;
	}
	
	/**
	 * 添加元素到尾部
	 * @param element
	 */
	public void add(E element) {
		add(size, element);
	}
	
	/**
	 * 在对应位置插入一个元素
	 * @param index
	 * @param element
	 */
	public void add(int index, E element) {
		rangeCheckForAdd(index);
		
		ensureCapacity(size + 1);
		
		for (int i = size; i > index; i--) {
			elements[i] = elements[i - 1];
		}
		elements[index] = element;
		size ++;
	}
	
	/**
	 * 删除 index 位置的元素
	 * @param index
	 * @return 返回删除的元素
	 */
	public E remove(int index) {
		rangeCheck(index);
		
		E old = elements[index];
		for (int i = index + 1; i < size; i++) {
			elements[i - 1] = elements[i];
		}
		elements[--size] = null;
		return old;
	}
	
	/**
	 * 查看元素的位置
	 * @param element
	 * @return 元素的 index
	 */
	public int indexOf(E element) {
		if (element == null) {
			for (int i = 0; i < size; i++) {
				if (elements[i] == null) return i;
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (element.equals(elements[i])) return i;
			}
		}
		return ELEMENT_NOT_FOUND;
	}
	
	
	private void outOfBounds(int index) {
		throw new IndexOutOfBoundsException("index:" + index + ", Size=" + size);
	}
	
	private void rangeCheck(int index) {
		if (size < 0 || index >= size) {
			outOfBounds(index);
		}
	}
	
	private void rangeCheckForAdd(int index) {
		if (size < 0 || index > size) {
			outOfBounds(index);
		}
	}
	
	/**
	 * 动态扩容
	 * @param capacity
	 */
	private void ensureCapacity(int capacity) {
		int oldCapacity = elements.length;
		if (oldCapacity >= capacity) return;
		
		// 这里保证新容量为旧容量的 1.5 倍
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
		
		System.out.println(oldCapacity + "扩容为" + newCapacity);
	}
	
	
	@Override
	public String toString() {
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("size=").append(size).append(", [");
		
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				stringBuilder.append(", ");
			}
			stringBuilder.append(elements[i]);
		}
		stringBuilder.append("]");
		return stringBuilder.toString();
	}
}
