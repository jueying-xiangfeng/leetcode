package review.queue;

public class Main {

	static void test1() {
		
		Queue<Integer> queue = new Queue<Integer>();
		queue.enQueue(11);
		queue.enQueue(22);
		queue.enQueue(33);
		queue.enQueue(44);
		
		
		while (!queue.isEmpty()) {
			System.out.println(queue.deQueue());
		}
		
		System.out.println("-------------------------");
		
		Deque<Integer> deque = new Deque<Integer>();
		deque.enQueueFront(11);
		deque.enQueueFront(22);
		deque.enQueueRear(33);
		deque.enQueueRear(44);
		
		while (!deque.isEmpty()) {
			System.out.println(deque.deQueueRear());
		}
	}
	
	static void test2() {
		
		CircleQueue<Integer> queue = new CircleQueue<Integer>();
		
		
		for (int i = 0; i < 10; i++) {
			queue.enQueue(i);
		}
		
		for (int i = 0; i < 5; i++) {
			queue.deQueue();
		}
		
		for (int i = 15; i < 20; i++) {
			queue.enQueue(i);
		}
		
		
		
		while (!queue.isEmpty()) {
			System.out.println(queue.deQueue());
		}
		
		System.out.println(queue);
		System.out.println("-------------------------");
	}
	
	
	public static void main(String[] args) {
		
		test2();
		
		
		
	}

}
