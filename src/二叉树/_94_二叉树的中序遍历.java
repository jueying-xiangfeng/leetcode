package 二叉树;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 * 
 * 
 * 给定一个二叉树，返回它的 中序 遍历。
 * 
 * 输入: [1,null,2,3]  
   1
    \
     2
    /
   3 

	输出: [1,3,2]
 * 
 */
public class _94_二叉树的中序遍历 {
	
    public List<Integer> inorderTraversal(TreeNode root) {
    	List<Integer> list = new LinkedList<>();
		inorder(root, list);
		return list;
    }
    
    /**
     * 迭代 -- 利用栈实现
     * @param node
     * @param list
     * 
     * 1、设置 node = root
     * 2、循环执行一下操作
     * 
     * - node != null
     * 		- 将 node 入栈
     * 		- 设置 node = node.left
     * 
     * - node == null
     * 		- 如果栈为空，结束遍历
     * 		- 栈不为空，则弹出栈顶元素 & 赋值给 node
     * 		- 访问 node
     * 		- 设置 node = node.right		
     * 
     */
    public void inorder(TreeNode node, List<Integer> list) {
		
    	if (node == null) return;
    	
    	Stack<TreeNode> stack = new Stack<>();
    	do {
    		if (node != null) {
    			stack.push(node);
        		node = node.left;
			} else {
				node = stack.pop();
				list.add(node.val);
				node = node.right;
			}
		} while (!stack.isEmpty() || node != null);
	}
    
    
    /**
     * 递归
     * @param node
     * @param list
     */
    public void inorder1(TreeNode node, List<Integer> list) {
		
    	if (node == null) return;
    	
    	inorder(node.left, list);
    	list.add(node.val);
    	inorder(node.right, list);
	}
}
