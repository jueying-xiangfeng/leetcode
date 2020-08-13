package 链表;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list-ii/ 
 * 
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * 1 ≤ m ≤ n ≤ 链表长度。
 */
public class _92_反转链表II {
	
	/**
	 * 迭代 -- 头插法
	 */
	public ListNode reverseBetween1(ListNode head, int m, int n) {
		
		ListNode sentinel = new ListNode(-1, head);
		ListNode prev = sentinel;
		
		for (int i = 1; i < m; i++) {
			prev = prev.next;
		}
		head = prev.next;
		
		for (int i = m; i < n; i++) {
			ListNode temp = head.next;
			head.next = temp.next;
			temp.next = prev.next;
			prev.next = temp;
		}
		return sentinel.next;
    }
	
	
	
	
	
	
	
	
	
	
	/**
	 * 递归
	 */
	public ListNode reverseBetween(ListNode head, int m, int n) {
		
		if (m == 1) {
			return reverseBetweenN(head, n);
		}
		head.next = reverseBetween(head.next, m - 1, n - 1);
		return head;
    }
	
	/*********** 翻转链表延伸 -- 翻转前 n 个节点 ************/
	
	// 后继节点
	ListNode successor = null;
	public ListNode reverseBetweenN(ListNode head, int n) {
		
		if (head == null || n <= 0) {
			return null;
		}
		
		if (n == 1) {
			successor = head.next;
			return head;
		}
		ListNode last = reverseBetweenN(head.next, n - 1);
		head.next.next = head;
		head.next = successor;
		return last;
    }
}
