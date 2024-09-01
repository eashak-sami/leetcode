package learnings.binarytree;

public class BinaryTreeImpl implements BinaryTree{

    /**
     * Insert a node in binary tree.
     * */
    public Node insert(Node root, int key)
    {

        if (root == null)
            return new Node(key);

        if (root.key == key)
            return root;

        if (key < root.key)
            root.left = insert(root.left, key);
        else
            root.right = insert(root.right, key);

        return root;
    }

    @Override
    public Node search(Node root, int key) {
        if (root == null || root.key == key)
            return root;

        if (root.key < key)
            return search(root.right, key);

        return search(root.left, key);
    }

    @Override
    public void inOrderTraversal(Node node) {
        if(node == null) return;

        inOrderTraversal(node.left);

        System.out.print(node.key + " ");

        inOrderTraversal(node.right);
    }

    @Override
    public void preOrderTraversal(Node node) {
        if(node == null) return;

        System.out.print(node.key + " ");

        preOrderTraversal(node.left);

        preOrderTraversal(node.right);
    }

    @Override
    public void postOrderTraversal(Node node) {
        if(node == null) return;

        postOrderTraversal(node.left);

        postOrderTraversal(node.right);

        System.out.print(node.key + " ");
    }
}
