package learnings.binarytree;

public class Main {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTreeImpl();
        Node root = null;

        // Creating the following BST
        //      50
        //     /  \
        //    30   70
        //   / \   / \
        //  20 40 60 80

        root = tree.insert(root, 50);
        root = tree.insert(root, 30);
        root = tree.insert(root, 20);
        root = tree.insert(root, 40);
        root = tree.insert(root, 70);
        root = tree.insert(root, 60);
        root = tree.insert(root, 80);

        System.out.println(tree.search(root, 70) != null ? "Found" : "Not found");
    }
}
