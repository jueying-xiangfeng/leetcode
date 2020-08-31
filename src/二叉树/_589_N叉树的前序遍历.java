package 二叉树;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/
 * 
 * 
 * 给定一个 N 叉树，返回其节点值的前序遍历。

例如，给定一个 3叉树 :

返回其前序遍历: [1,3,5,6,2,4]。
 * 
 */
public class _589_N叉树的前序遍历 {
    public List<Integer> preorder(Node root) {
        List<Integer> list = new LinkedList<Integer>();
    	preorder(root, list);
    	return list;
    }
    
    /**
     * 迭代
     */
    private void preorder(Node node, List<Integer> list) {
    	
    	if (node == null) return;
    	
    	Stack<Node> stack = new Stack<Node>();
    	stack.push(node);
    	
    	while (!stack.isEmpty()) {
			node = stack.pop();
			// 访问 node
    		list.add(node.val);
    		
    		if (node.children != null && node.children.size() > 0) {
    			for (int i = node.children.size() - 1; i >= 0; i--) {
                	stack.push(node.children.get(i));
        		}
			}
		}
    }
    
    
    /**
     * 递归
     */
    @SuppressWarnings("unused")
	private void preorder1(Node node, List<Integer> list) {
    	
    	if (node == null) return;
    	
    	list.add(node.val);
    	
    	for (int i = 0; i < node.children.size(); i++) {
        	preorder(node.children.get(i), list);
		}
    }
}
