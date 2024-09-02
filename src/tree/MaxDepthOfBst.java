package tree;

/**
 * {@link: <a href="https://leetcode.com/problems/maximum-depth-of-binary-tree/description/">Max depth of binary tree</a>}
 * */

public class MaxDepthOfBst {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return traversTree(root, 0, 0);
    }

    public int traversTree(TreeNode root, int maxDepth, int currentDepth) {
        if (root == null) return Math.max(currentDepth, maxDepth);

        maxDepth = traversTree(root.left, maxDepth, currentDepth + 1);

        maxDepth = traversTree(root.right, maxDepth,currentDepth + 1);
        return maxDepth;
    }
}
