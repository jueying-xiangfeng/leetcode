package 链表;

/**
 *  https://leetcode-cn.com/problems/reverse-linked-list/
 * 
 * 反转一个单链表。
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 */
public class _206_反转链表 {
	
	public ListNode reverseList(ListNode head) {
        
		
		return null;
    }
	
	/**
	 * 递归
	 */
	public ListNode reverseList1(ListNode head) {
		
		if (head == null || head.next == null) {
			return head;
		}
		
		ListNode newNode = reverseList1(head.next);
		head.next.next = head;
		head.next = null;
		return newNode;
	}
	 
	/**
	 * 迭代
	 */
	public ListNode reverseList2(ListNode head) {
		
		if (head == null || head.next == null) {
			return head;
		}
		
		ListNode newNode = null;
		while (head != null) {
			ListNode temNode = head.next;
			head.next = newNode;
			newNode = head;
			head = temNode;
		}
		
		return newNode;
	}
	
}
