package 二叉树;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 * 
 * 
 * 给定一个二叉树，返回它的 前序 遍历
 * 
 * 输入: [1,null,2,3]  
   1
    \
     2
    /
   3 

	输出: [1,2,3]
 * 
 */
public class _144_二叉树的前序遍历 {
	
	/**
	 * 迭代
	 * 
	 * 利用栈实现
	 * 
	 * 
	 * 
	 */
	public List<Integer> preorderTraversal(TreeNode root) {
		
		List<Integer> list = new LinkedList<>();
		preorder(root, list);
		return list;
    }
	
	/**
	 * 1、设置 node = root
	 * 2、循环执行一下操作
	 * 
	 * - node != null
	 * 		- 对 node 进行访问
	 * 		- 将 node.right 入栈
	 * 		- 设置 node = node.left
	 * 
	 * - node == null
	 * 		- 如果栈为空则结束遍历
	 * 		- 如果栈不为空，弹出栈顶元素并赋值给 node
	 * 
	 * @param node
	 * @param list
	 */
	public void preorder_1(TreeNode node, List<Integer> list) {

        if (node == null) return;
        
		Stack<TreeNode> stack = new Stack<>();
		
		do {
			if (node != null) {
				list.add(node.val);
				if (node.right != null) {
					stack.push(node.right);
				}
				node = node.left;
			} else {
				node = stack.pop();
			}
		} while (!stack.isEmpty() || node != null);
	}
	
	/**
	 * 1、将 root 入栈
	 * 2、循环执行一下操作，直到栈为空
	 * 
	 * - 弹出栈顶节点 & 进行访问
	 * - 将节点的 right入栈
	 * - 将节点的 left 入栈
	 * 
	 * @param node
	 * @param list
	 */
	public void preorder(TreeNode node, List<Integer> list) {
		
        if (node == null) return;
        
		Stack<TreeNode> stack = new Stack<>();
		stack.push(node);
		
		while (!stack.isEmpty()) {
			node = stack.pop();
			list.add(node.val);
			if (node.right != null) {
				stack.push(node.right);
			}
			if (node.left != null) {
				stack.push(node.left);
			}
		}
	}
	
	
	
	
	/**
	 * 递归
	 */
	public List<Integer> preorderTraversal1(TreeNode root) {
		
		List<Integer> list = new LinkedList<>();
		preorder1(root, list);
		return list;
    }
	
	public void preorder1(TreeNode node, List<Integer> list) {
		
		if (node == null) return;
		
		list.add(node.val);
		preorder1(node.left, list);
		preorder1(node.right, list);
	}
}
