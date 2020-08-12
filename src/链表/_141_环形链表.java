package 链表;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list/ 
 * 
 */
public class _141_环形链表 {
	
	/**
	 * 快慢指针
	 */
	public boolean hasCycle(ListNode head) {
        
        if (head == null || head.next == null) {
            return false;
        }

        ListNode fast = head.next;
		ListNode slow = head;
		
		while (fast != null && fast.next != null) {
			
			slow = slow.next;
			fast = fast.next.next;
			
			if (slow == fast) {
				return true;
			}
		}
		return false;
    }
}
