package 链表;

/**
 *  https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 *  
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8 
 * 输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 */
public class _160_相交链表 {
	
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        
    	if (headA == null || headB == null) {
			return null;
		}
    	
    	ListNode pA = headA;
    	ListNode pB = headB;
    	
    	while (pA != pB) {
			pA = pA == null ? headB : pA.next;
			pB = pB == null ? headA : pB.next;
		}
    	return pA;
    }
    
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        
    	if (headA == null || headB == null) {
			return null;
		}
    	
    	ListNode lastB = headB;
    	while (lastB.next != null) {
			lastB = lastB.next;
		}
    	lastB.next = headB;
    	
    	ListNode fast = headA;
    	ListNode slow = headA;
    	
    	while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			// 如果有环则相遇
			if (fast == slow) {
				slow = headA;
				while (slow != fast) {
					slow = slow.next;
					fast = fast.next;
				}
				lastB.next = null;
				return fast;
			}
		}
    	lastB.next = null;
    	return null;
    }
}
