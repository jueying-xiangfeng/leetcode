package 二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 * 
 * 
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 
 * 给定二叉树 [3,9,20,null,null,15,7]，

    3
   / \
  9  20
    /  \
   15   7
返回它的最大深度 3 。
 *
 */
public class _104_二叉树的最大深度 {
	
    public int maxDepth(TreeNode root) {
    	
    	if (root == null) return 0;
    	
    	int maxDepth = 0;
    	int levelSize = 1;
    	Queue<TreeNode> queue = new LinkedList<>();
    	queue.offer(root);
    	
    	while (!queue.isEmpty()) {
			
    		TreeNode node = queue.poll();
    		levelSize --;
    		
    		if (node.left != null) {
				queue.offer(node.left);
			}
    		if (node.right != null) {
    			queue.offer(node.right);
    		}
    		
    		if (levelSize == 0) {
				levelSize = queue.size();
				maxDepth ++;
			}
		}
    	
    	return maxDepth;
    	
    	/**
    	 * 递归
    	 */
//    	return root == null ? 0 : (Math.max(maxDepath(root.left), maxDepth(root.right) + 1));
    }

	private int maxDepath(TreeNode left) {
		// TODO Auto-generated method stub
		return 0;
	}
}
