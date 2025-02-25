public class AVLTreeExample {

    public static void main(String[] args) {
        AVLTree tree1 = new AVLTree();
        AVLTree tree2 = new AVLTree();
        /* Constructing tree given in the above figure */
        tree1.root = tree1.insert(tree1.root, 10);
        tree1.root = tree1.insert(tree1.root, 20);
        tree1.root = tree1.insert(tree1.root, 30);
        tree1.root = tree1.insert(tree1.root, 40);
        tree1.root = tree1.insert(tree1.root, 50);
        tree1.root = tree1.insert(tree1.root, 25);

        tree2.root = tree2.insert(tree2.root, 9);
        tree2.root = tree2.insert(tree2.root, 5);
        tree2.root = tree2.insert(tree2.root, 10);
        tree2.root = tree2.insert(tree2.root, 0);
        tree2.root = tree2.insert(tree2.root, 6);
        tree2.root = tree2.insert(tree2.root, 11);
        tree2.root = tree2.insert(tree2.root, 1);


        System.out.println("Inorder traversal of the constructed AVL tree is: ");
        tree1.inorderTraversal(tree1.root);
        System.out.println();

        System.out.println("Preorder traversal of the constructed AVL tree is: ");
        tree1.preorderTraversal(tree1.root);
        System.out.println();

        System.out.println("Postorder traversal of the constructed AVL tree is: ");
        tree1.postorderTraversal(tree1.root);
        System.out.println();

        tree1.printTree(tree1.root);

        System.out.println("Inorder traversal of the constructed AVL tree is: ");
        tree1.inorderTraversal(tree2.root);
        System.out.println();

        tree2.printTree(tree2.root);

        AVLTree mergedTree = tree1.merge(tree1, tree2);
        System.out.println("Inorder traversal of the merged AVL tree is: ");
        mergedTree.inorderTraversal(mergedTree.root);
        System.out.println();

        mergedTree.printTree(mergedTree.root);
    }
}