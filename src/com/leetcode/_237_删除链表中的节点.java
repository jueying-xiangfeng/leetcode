package com.leetcode;

/**
 * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/submissions/
 *  
 */
public class _237_删除链表中的节点 {
	
	public void deleteNode(ListNode node) {
		node.val = node.next.val;
		node.next = node.next.next;
    }
}