package 二叉树;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 * 
 * 
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 * 
 * 二叉树：[3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回其层次遍历结果：

[
  [3],
  [9,20],
  [15,7]
]
 * 
 */
public class _102_二叉树的层序遍历 {
	
	/**
	 * 层序遍历 -- 使用队列
	 * @param root
	 * @return
	 */
    public List<List<Integer>> levelOrder(TreeNode root) {
    	List<List<Integer>> list = new LinkedList<>();
    	
    	if (root == null) return list;
    	
    	int levelSize = 1;
    	Queue<TreeNode> queue = new LinkedList<>();
    	queue.offer(root);
    	List<Integer> nestList = new LinkedList<>();
    	
    	while (!queue.isEmpty()) {
    		TreeNode tmpNode = queue.poll();
    		levelSize --;
    		nestList.add(tmpNode.val);
    		
    		if (tmpNode.left != null) {
				queue.offer(tmpNode.left);
			}
    		if (tmpNode.right != null) {
				queue.offer(tmpNode.right);
			}
    		
    		if (levelSize == 0) {
    			list.add(nestList);
				levelSize = queue.size();
				nestList = new LinkedList<>();
			}
		}
    	return list;
    }	
}
