// Define the AVL Tree with various operations

import java.util.ArrayList;
import java.util.List;

class AVLTree {
    AVLNode root;
    
    // Get the height of the node
    private int height(AVLNode node) {
        return (node == null) ? 0 : node.height;
    }

    // Calculate balance factor of the node
    private int getBalance(AVLNode node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    // Right rotate subtree rooted with y
    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // Left rotate subtree rooted with x
    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Insert a node into the AVL tree and balance it
    public AVLNode insert(AVLNode node, int key) {
        // Perform normal BST insertion
        if (node == null) {
            return new AVLNode(key);
        }
        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            return node; // Duplicate keys are not allowed in BST
        }   
        // Update height of this ancestor node
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Get the balance factor
        int balance = getBalance(node);

        // If the node is unbalanced, then there are 4 cases

        // Left Left Case
        if (balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }
        // Right Right Case
        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }
        // Left Right Case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node; // Return the (unchanged) node pointer
    }

    // Inorder traversal of the AVL tree
    public void inorderTraversal(AVLNode node) {
        if (node != null) {
            inorderTraversal(node.left);
            System.out.print(node.key + " ");
            inorderTraversal(node.right);
        }
    }

    private void inorderTraversal(AVLNode node, List<Integer> list) {
        if (node != null) {
            inorderTraversal(node.left, list);
            list.add(node.key);
            inorderTraversal(node.right, list);
        }
    }

    // Preorder traversal of the AVL tree
    public void preorderTraversal(AVLNode node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preorderTraversal(node.left);
            preorderTraversal(node.right);
        }
    }

    // Postorder traversal of the AVL tree
    public void postorderTraversal(AVLNode node) {
        if (node != null) {
            postorderTraversal(node.left);
            postorderTraversal(node.right);
            System.out.print(node.key + " ");
        }
    }

    // Print the AVL tree
    public void printTree(AVLNode root) {
        if (root == null) {
            return;
        }

        int height = root.height;
        int maxNodes = (int) Math.pow(2, height) - 1;
        List<AVLNode> currentLevel = new ArrayList<>();
        currentLevel.add(root);

        for (int level = 0; level < height; level++) {
            List<AVLNode> nextLevel = new ArrayList<>();
            int spaces = (int) Math.pow(2, height - level - 1) - 1;
            int betweenSpaces = (int) Math.pow(2, height - level) - 1;

            printSpaces(spaces);

            for (AVLNode node : currentLevel) {
                if (node != null) {
                    System.out.print(node.key);
                    nextLevel.add(node.left);
                    nextLevel.add(node.right);
                } else {
                    System.out.print(" ");
                    nextLevel.add(null);
                    nextLevel.add(null);
                }
                printSpaces(betweenSpaces);
            }
        System.out.println();
        currentLevel = nextLevel;
            }
    }

    // Helper method to print spaces for printTree method
    private void printSpaces(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }

    // Method to delete a node from the AVL tree
    public AVLNode delete(AVLNode root, int key) {
        if (root == null) {
            return root;
        }

        // If the key to be deleted is smaller than the root's key, then it lies in the left subtree
        if (key < root.key) {
            root.left = delete(root.left, key);
        }

        // If the key to be deleted is larger than the root's key, then it lies in the right subtree
        else if (key > root.key) {
            root.right = delete(root.right, key);
        }

        // If the key is same as root's key, then this is the node to be deleted
        else {
            // Node with only one child or no child
            if ((root.left == null) || (root.right == null)) {
                AVLNode temp = null;
                if (temp == root.left) {
                    temp = root.right;
                } else {
                    temp = root.left;
                }

                // No child case
                if (temp == null) {
                    temp = root;
                    root = null;
                } else {
                    root = temp; // One child case
                }
            } else {
                // Node with two children: Get the inorder successor (smallest in the right subtree)
                AVLNode temp = minValueNode(root.right);

                // Copy the inorder successor's data to this node
                root.key = temp.key;

                // Delete the inorder successor
                root.right = delete(root.right, temp.key);
            }
        }

        if (root == null) {
            return root;
        }

        root.height = Math.max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0) {
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0) {
            return leftRotate(root);
        }

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        
        return root;
    }

    // Helper function to find the node with the smallest key value for delete method
    private AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;
        
        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    // Method to merge two AVL trees
    public AVLTree merge(AVLTree tree1, AVLTree tree2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        // Perform inorder traversal of both trees to get sorted lists
        inorderTraversal(tree1.root, list1);
        inorderTraversal(tree2.root, list2);

        // Merge the sorted lists
        List<Integer> mergedList = mergeSortedLists(list1, list2);

        // Construct new balanced AVL tree from the merged and sorted list
        AVLTree mergedTree = new AVLTree();
        mergedTree.root = sortedListToAVL(mergedList, 0, mergedList.size() - 1);

        return mergedTree;
    }

    // Helper method to merge two sorted lists
    private List<Integer> mergeSortedLists(List<Integer> list1, List<Integer> list2) {
        List<Integer> mergedList = new ArrayList<>();
        int i = 0, j = 0;

        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i) < list2.get(j)) {
                mergedList.add(list1.get(i));
                i++;
            } else {
                mergedList.add(list2.get(j));
                j++;
            }
        }

        while (i < list1.size()) {
            mergedList.add(list1.get(i));
            i++;
        }

        while (j < list2.size()) {
            mergedList.add(list2.get(j));
            j++;
        }

        return mergedList;
    }

    // Helper method to construct a balanced AVL tree from sorted list
    private AVLNode sortedListToAVL(List<Integer> list, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        AVLNode node = new AVLNode(list.get(mid));

        node.left = sortedListToAVL(list, start, mid - 1);
        node.right = sortedListToAVL(list, mid + 1, end);

        node.height = Math.max(height(node.left), height(node.right)) + 1;

        return node;
    }
}