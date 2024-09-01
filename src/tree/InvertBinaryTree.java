package tree;

/**
 * {@link: <a href="https://leetcode.com/problems/invert-binary-tree/description/">Invert binary tree</a>}
 * */

public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}