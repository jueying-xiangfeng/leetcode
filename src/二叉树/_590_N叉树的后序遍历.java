package 二叉树;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/
 * 
 * 
 * 给定一个 N 叉树，返回其节点值的后序遍历。

例如，给定一个 3叉树 :

返回其后序遍历: [5,6,3,2,4,1].
 * 
 */
public class _590_N叉树的后序遍历 {
	
    public List<Integer> postorder(Node root) {
        List<Integer> list = new LinkedList<>();
        postorder(root, list);
        return list;
    }
    
    /**
     * 迭代
     */
    private void postorder(Node node, List<Integer> list) {
    	
    	if (node == null) return;
    	
    	Stack<Node> stack = new Stack<Node>();
    	stack.push(node);
    	
    	Node pre = null;
    	
    	// 1, 2, 3
    	
    	while (!stack.isEmpty()) {
    		Node top = stack.peek();
    		
    		if ((top.children == null && top.children.size() == 0) || top.children.contains(pre)) {
    			node = stack.pop();
				list.add(node.val);
				pre = node;
			} else {
				if (node.children != null && node.children.size() > 0) {
					for (int i = node.children.size() - 1; i >= 0; i--) {
						if (node.children.get(i) != null) {
							stack.push(node.children.get(i));
						}
					}
				}
			}
		}
    }
    
    /**
     * 递归
     */
    @SuppressWarnings("unused")
	private void postorder1(Node node, List<Integer> list) {
    	
    	if (node == null) return;
    	
    	for (int i = 0; i < node.children.size(); i++) {
			postorder(node.children.get(i), list);
		}
    	list.add(node.val);
    }
}
