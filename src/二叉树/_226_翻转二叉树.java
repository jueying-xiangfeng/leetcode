package 二叉树;


/**
 * https://leetcode-cn.com/problems/invert-binary-tree/
 * 
 * 
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 
 * 输入：

     4
   /   \
  2     7
 / \   / \
1   3 6   9
输出：

     4
   /   \
  7     2
 / \   / \
9   6 3   1

 *
 */
public class _226_翻转二叉树 {
    public TreeNode invertTree(TreeNode root) {
    	
    	if (root == null) return root;
    	
    	TreeNode tmpNode = root.left;
    	root.left = root.right;
    	root.right = tmpNode;
    	
    	invertTree(root.left);
    	invertTree(root.right);
    	
    	return root;
    }
}
