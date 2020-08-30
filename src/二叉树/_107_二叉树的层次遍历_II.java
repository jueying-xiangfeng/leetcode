package 二叉树;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
 * 
 * 
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）

例如：
给定二叉树 [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回其自底向上的层次遍历为：

[
  [15,7],
  [9,20],
  [3]
]

 * 
 */
public class _107_二叉树的层次遍历_II {
	
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
    	
    	List<List<Integer>> list = new LinkedList<>();
    	if (root == null) return list;
    	
    	Queue<TreeNode> queue = new LinkedList<>();
    	queue.offer(root);
    	
    	List<Integer> integerList = new LinkedList<>();
    	int levelSize = 1;
    	
    	while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			levelSize --;
			integerList.add(node.val);
			
    		if (node.left != null) {
				queue.offer(node.left);
			}
    		if (node.right != null) {
				queue.offer(node.right);
			}
    		
    		if (levelSize == 0) {
				levelSize = queue.size();
				list.add(0, integerList);
				integerList = new LinkedList<>();
			}
		}
    	return list;
    }
}
