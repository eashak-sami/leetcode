package learnings.binarytree;

public interface BinaryTree {
    Node insert(Node root, int key);

    Node delete(Node node, int key);

    Node search(Node root, int key);

    void inOrderTraversal(Node node);

    void preOrderTraversal(Node node);

    void postOrderTraversal(Node node);
}
