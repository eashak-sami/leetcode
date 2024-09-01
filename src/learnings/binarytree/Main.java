package learnings.binarytree;

public class Main {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTreeImpl();
        Node root = null;

//                         Creating the following BST
//                              12
//                             /  \
//                            5   15
//                           / \   / \
//                          3  7  13  17
//                         /   \   \    \
//                         1    9  14   20
//                             / \      /
//                            8  11    18

        root = tree.insert(root, 12);
        root = tree.insert(root, 5);
        root = tree.insert(root, 3);
        root = tree.insert(root, 1);
        root = tree.insert(root, 7);
        root = tree.insert(root, 9);
        root = tree.insert(root, 8);
        root = tree.insert(root, 11);
        root = tree.insert(root, 15);
        root = tree.insert(root, 13);
        root = tree.insert(root, 14);
        root = tree.insert(root, 17);
        root = tree.insert(root, 20);
        root = tree.insert(root, 18);

        System.out.println(tree.search(root, 13) != null ? "Found" : "Not found");

        System.out.println("*****************In Order Traversal*****************");
        tree.inOrderTraversal(root);
        System.out.println();

        System.out.println("****************Pre Order Traversal****************");
        tree.preOrderTraversal(root);
        System.out.println();

        System.out.println("****************Post Order Traversal****************");
        tree.postOrderTraversal(root);
        System.out.println();

        System.out.println("***************Level order traversal***************");
        tree.levelOrderTraversal(root);
        System.out.println();

        System.out.println("***********Delete Node Without any child***********");
        tree.delete(root, 11);
        System.out.println();

        System.out.println("*****Delete Node With only left or right child*****");
        tree.delete(root, 7);
        System.out.println();

        System.out.println("*****Delete Node With both left and right child*****");
        tree.delete(root, 15);
        System.out.println();
    }
}
