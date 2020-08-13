package 链表;

/**
 * https://leetcode-cn.com/problems/remove-linked-list-elements/ 
 * 
 * 删除链表中等于给定值 val 的所有节点。
 * 
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 */
public class _203_移除链表元素 {
	
	/**
	 * 添加哨兵节点 
	 * 这里的难点就是如果 val 的值一直都是第一个的话该怎么处理
	 * 添加哨兵节点，让 head 变成非第一个节点
	 */
	public ListNode removeElements(ListNode head, int val) {
		
		if (head == null) {
			return head;
		}
		ListNode sintinel = new ListNode(0);
		sintinel.next = head;
		
		ListNode prev = sintinel;
		ListNode cur = head;
		
		while (cur != null) {
			if (cur.val == val) {
				prev.next = cur.next;
			} else {
				prev = cur;
			}
			cur = cur.next;
		}
		
		return sintinel.next;
    }
	
	/**
	 * 迭代不设置虚拟节点
	 */
	public ListNode removeElements1(ListNode head, int val) {
		
		while (head != null && head.val == val) {
			head = head.next;
		}
		ListNode prev = head;
		if (prev != null) {
			
			while (prev.next != null) {
				if (prev.next.val == val) {
					prev.next = prev.next.next;
				} else {
					prev = prev.next;
				}
			}
		}
		return head;
    }
	
	/**
	 * 递归
	 */
	public ListNode removeElements2(ListNode head, int val) {
		
		if (head == null) {
			return head;
		}
		head.next = removeElements2(head.next, val);
		return head.val == val ? head.next : head;
    }
}
