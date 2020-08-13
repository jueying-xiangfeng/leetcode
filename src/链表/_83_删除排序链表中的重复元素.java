package 链表;

/**
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 *  
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 */
public class _83_删除排序链表中的重复元素 {
	
	/**
	 * 递归
	 * 
	 * 递归三要素：https://lyl0724.github.io/2020/01/25/1/
	 * 1、终止条件
	 * 2、返回值
	 * 3、本次递归要做什么
	 * 
	 */
    public ListNode deleteDuplicates(ListNode head) {
    	
    	if (head == null || head.next == null) {
			return head;
		}
    	head.next = deleteDuplicates(head.next);
    	if (head.val == head.next.val) {
			head = head.next;
		}
    	return head;
    }
    
    /**
	 * 迭代
	 */
    public ListNode deleteDuplicates1(ListNode head) {
    	
    	if (head == null || head.next == null) {
			return head;
		}
    	ListNode prev = head;
    	ListNode cur = head.next;
    	
    	while (cur != null) {
			
    		if (prev.val == cur.val) {
				prev.next = cur.next;
			} else {
				prev = cur;
			}
    		cur = cur.next;
		}
    	return head;
    }
}
