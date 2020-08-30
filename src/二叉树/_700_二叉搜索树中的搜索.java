package 二叉树;

/**
 * https://leetcode-cn.com/problems/search-in-a-binary-search-tree/
 * 
 * 
 * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。

例如，

给定二叉搜索树:

        4
       / \
      2   7
     / \
    1   3

和值: 2
你应该返回如下子树:

      2     
     / \   
    1   3
在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL
 * 
 */
public class _700_二叉搜索树中的搜索 {
	
    public TreeNode searchBST(TreeNode root, int val) {
    	return node(root, val);
    }
    
    private TreeNode node(TreeNode node, int element) {
		if (node == null) return null;
    	
		int cmp = 0;
		
		while (node != null) {
			cmp = compare(node.val, element);
			if (cmp == 0) {
				return node;
			}
			if (cmp > 0) {
				node = node.left;
			} else {
				node = node.right;
			}
		}
		return null;
	}
    
    private int compare(int e1, int e2) {
		return e1 - e2;
	}
}
