package learnings.binarytree;

/**
 * Create a new node for binary tree.
 * The node contains a key along with left and right child
 * during initialization of node, the left and right pointer will be null.
 * */
public class Node {
    int key;
    Node left;
    Node right;

    public Node(int key) {
        this.key = key;
        left = null;
        right = null;
    }
}
