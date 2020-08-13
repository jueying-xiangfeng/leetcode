package 链表;

/**
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/ 
 * 
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * 
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class _21_合并两个有序链表 {
	
	/**
	 * 递归
	 * 
	 */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    	
    	if (l1 == null) {
			return l2;
		}
    	if (l2 == null) {
			return l1;
		}
    	
    	if (l1.val < l2.val) {
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		} else {
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
    }
    
    /**
	 * 迭代 
	 * 哨兵对象
	 */
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
    	
    	if (l1 == null) {
			return l2;
		}
    	if (l2 == null) {
			return l1;
		}
    	
    	ListNode sentinel = new ListNode(-1);
    	ListNode prev = sentinel;
    	
    	while (l1 != null && l2 != null) {
			
    		if (l1.val <= l2.val) {
				prev.next = l1;
				l1 = l1.next;
			} else {
				prev.next = l2;
				l2 = l2.next;
			}
    		prev = prev.next;
		}
    	
    	prev.next = l1 == null ? l2 : l1;
    	
    	return sentinel.next;
    }
}
