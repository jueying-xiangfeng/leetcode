package 二叉树;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 * 
 * 
 * 给定一个二叉树，返回它的  后序 遍历。
 * 
 * 输入: [1,null,2,3]  
   1
    \
     2
    /
   3 

	输出: [3,2,1]
 * 
 */
public class _145_二叉树的后序遍历 {
	
    public List<Integer> postorderTraversal(TreeNode root) {
    	List<Integer> list = new LinkedList<>();
		postorder(root, list);
		return list;
    }
    
    /**
     * 迭代 -- 栈
     * @param node
     * @param list
     * 
     * 1、将 root 入栈
     * 2、循环执行一下操作 直到栈为空
     * 
     * - 如果栈顶节点是叶子节点 或者 上一次访问的节点是栈顶节点的子节点
     * 		- 弹出栈顶节点，进行访问
     * 
     * - 否则
     * 		- 将栈顶节点的 right、left 按顺序入栈
     * 
     */
    public void postorder(TreeNode node, List<Integer> list) {
		
    	if (node == null) return;
    	
    	Stack<TreeNode> stack = new Stack<>();
    	stack.push(node);
    	TreeNode preNode = null;
    	
    	while (!stack.isEmpty()) {
			
    		TreeNode top = stack.peek();
    		
    		if ((top.left == null && top.right == null) || (preNode != null && (preNode == top.left || preNode == top.right))) {
				list.add(top.val);
				preNode = top;
                stack.pop();
			} else {
				if (top.right != null) {
					stack.push(top.right);
				}
				if (top.left != null) {
					stack.push(top.left);
				}
			}
		}
	}

    /**
     * 递归
     * @param node
     * @param list
     */
    public void postorder1(TreeNode node, List<Integer> list) {
		
    	if (node == null) return;
    	
    	postorder(node.left, list);
    	postorder(node.right, list);
    	list.add(node.val);
	}
}
