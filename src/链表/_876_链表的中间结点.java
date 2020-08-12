package 链表;

/**
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
 *  
 */
public class _876_链表的中间结点 {
	
	/**
	 * 快慢指针法
	 */
    public ListNode middleNode(ListNode head) {
    	
    	ListNode fast = head;
    	ListNode slow = head;
    	
    	while (fast != null && fast.next != null) {
			slow = slow.next;
    		fast = fast.next.next;
		}
    	return slow;
    }
}
