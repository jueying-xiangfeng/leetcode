package 二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/maximum-width-of-binary-tree/
 * 
 * 
 * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。

每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。

示例 1:

输入: 

           1
         /   \
        3     2
       / \     \  
      5   3     9 

输出: 4
解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。
示例 2:

输入: 

          1
         /  
        3    
       / \       
      5   3     

输出: 2
解释: 最大值出现在树的第 3 层，宽度为 2 (5,3)。
示例 3:

输入: 

          1
         / \
        3   2 
       /        
      5      

输出: 2
解释: 最大值出现在树的第 2 层，宽度为 2 (3,2)。
示例 4:

输入: 

          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
输出: 8
解释: 最大值出现在树的第 4 层，宽度为 8 (6,null,null,null,null,null,null,7)。
注意: 答案在32位有符号整数的表示范围内。

 * 
 */
public class _662_二叉树最大宽度 {
	
    public int widthOfBinaryTree(TreeNode root) {
        
    	int width = 0;
    	if (root == null) return width;
    	
    	Queue<TreeNode> queue = new LinkedList<TreeNode>();
    	queue.offer(root);
    	int levelSize = 1;
    	LinkedList<Integer> indexList = new LinkedList<Integer>();
    	indexList.add(1);
    	
    	while (!queue.isEmpty()) {
    		TreeNode node = queue.poll();
    		levelSize --;
    		
    		int index = indexList.removeFirst();
    		
    		if (node.left != null) {
				queue.offer(node.left);
				indexList.add(index * 2);
			}
    		if (node.right != null) {
				queue.offer(node.right);
				indexList.add(index * 2 + 1);
			}
    		if (levelSize == 0) {
				if (indexList.size() >= 1) {
					width = Math.max(indexList.getLast() - indexList.getFirst() + 1, width);
				}
				levelSize = queue.size();
			}
		}
    	
    	return width;
    	
    	
//    	if (root == null) return 0;
//    	
//    	int width = 0;
//    	Queue<TreeNode> queue = new LinkedList<>();
//    	queue.offer(root);
//    	int levelSize = 1;
//    	
//    	ArrayList<Object> arrayList = new ArrayList<>();
//    	String placeholder = "a";
//    	
//    	while (!queue.isEmpty()) {
//			TreeNode node = queue.poll();
//    		levelSize --;
//    		
//    		boolean hasAdd = false;
//    		
//    		if (node.left != null) {
//				queue.offer(node.left);
//				hasAdd = true;
//				arrayList.add(node.left);
//			}
//    		if (node.right != null) {
//				queue.offer(node.right);
//				hasAdd = true;
//				arrayList.add(node.right);
//			}
//    		if (hasAdd == false) {
//    			arrayList.add(placeholder);
//			}
//    		
//    		if (levelSize == 0) {
//				levelSize = queue.size();
//				
//				
//				
//			}
//		}
//    	return width;
    }
}
