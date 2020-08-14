package 链表;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/palindrome-linked-list/ 
 * 
 * 输入: 1->2->2->1
 * 输出: true
 * 
 * 输入: 1->2
 * 输出: false
 * 
 */
public class _234_回文链表 {
	
	/**
	 * 快慢指针  --- 注意：在结束时需要将链表还原
	 * @param head
	 * @return
	 */
    public boolean isPalindrome(ListNode head) {
    	
    	if (head == null || head.next == null) {
			return true;
		}
    	// 利用快慢指针找到中间节点
    	ListNode fast = head;
    	ListNode slow = head;
    	while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
    	
    	ListNode invertHalf = slow;
    	while (slow.next != null) {
			ListNode temp = slow.next;
			slow.next = temp.next;
			temp.next = invertHalf;
			invertHalf = temp;
		}
    	
    	ListNode positive = head;
    	while (invertHalf != null && invertHalf.next != null) {
			
    		if (positive.val != invertHalf.val) {
				return false;
			}
    		positive = positive.next;
    		invertHalf = invertHalf.next;
		}
    	return true;
    }
	
	/**
	 * 数组方式
	 * @param head
	 * @return
	 */
    public boolean isPalindrome1(ListNode head) {
    	
    	if (head == null || head.next == null) {
			return true;
		}
    	
    	List<Integer> list = new ArrayList<Integer>(); 
    	ListNode node = head;
    	while (node != null) {
			list.add(node.val);
			node = node.next;
		}
    	
    	int pre = 0;
    	int last = list.size() - 1;
    	
    	while (pre < last) {
			if (!list.get(pre).equals(list.get(last))) {
				return false;
			}
			pre ++;
			last --;
		}
    	return true;
    }
}
