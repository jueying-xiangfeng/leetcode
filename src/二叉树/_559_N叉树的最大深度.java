package 二叉树;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree/
 * 
 * 
 * 给定一个 N 叉树，找到其最大深度。

最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。

例如，给定一个 3叉树 :

 



 

我们应返回其最大深度，3。

说明:

树的深度不会超过 1000。
树的节点总不会超过 5000。

 * 
 */
public class _559_N叉树的最大深度 {
	
    public int maxDepth(Node root) {
        if (root == null) return 0;
    	
    	int maxDepth = 0;
    	Queue<Node> queue = new LinkedList<Node>();
    	queue.offer(root);
    	int levelSize = 1;
    	
    	while (!queue.isEmpty()) {
			
    		Node node = queue.poll();
    		levelSize --;
    		
    		if (!node.children.isEmpty()) {
				for (int i = 1; i <= node.children.size(); i++) {
					queue.offer(node.children.get(i - 1));
				}
			}
    		
    		// 下一层
    		if (levelSize == 0) {
				levelSize = queue.size();
				maxDepth ++;
			}
		}
    	
    	return maxDepth;
    }
    
    /**
     * 迭代
     */
    public int maxDepth1(Node root) {
    	
    	if (root == null) return 0;
    	
    	// 获取每个子树的最大深度
    	int max = 0;
    	for (Node node : root.children) {
			int depth = maxDepth1(node);
			max = Math.max(max, depth);
		}
    	return max + 1;
	 }
}
