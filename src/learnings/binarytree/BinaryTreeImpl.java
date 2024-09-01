package learnings.binarytree;

public class BinaryTreeImpl implements BinaryTree {

    /**
     * Insert a node in binary tree.
     */
    public Node insert(Node root, int key) {

        if (root == null) return new Node(key);

        if (root.key == key) return root;

        if (key < root.key) root.left = insert(root.left, key);
        else root.right = insert(root.right, key);

        return root;
    }

    @Override
    public Node search(Node root, int key) {
        if (root == null || root.key == key) return root;

        if (root.key < key) return search(root.right, key);

        return search(root.left, key);
    }

    @Override
    public void inOrderTraversal(Node node) {
        if (node == null) return;

        inOrderTraversal(node.left);

        System.out.print(node.key + " ");

        inOrderTraversal(node.right);
    }

    @Override
    public void preOrderTraversal(Node node) {
        if (node == null) return;

        System.out.print(node.key + " ");

        preOrderTraversal(node.left);

        preOrderTraversal(node.right);
    }

    @Override
    public void postOrderTraversal(Node node) {
        if (node == null) return;

        postOrderTraversal(node.left);

        postOrderTraversal(node.right);

        System.out.print(node.key + " ");
    }

    @Override
    public Node delete(Node root, int key) {
        if (root == null) {
            return null;
        } else if (key < root.key) {
            root.left = delete(root.left, key);
        } else if (key > root.key) {
            root.right = delete(root.right, key);
        } else {
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.left == null) {
                Node temp = root;
                root = root.right;
                temp = null;
            } else if (root.right == null) {
                Node temp = root;
                root = root.left;
                temp = null;
            } else {
                Node temp = findMin(root.right);
                root.key = temp.key;
                root.right = delete(root.right, temp.key);
            }
        }
        return root;
    }

    public Node findMin(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }
}
