#include <iostream>

template <typename T>
class TreeNode {
    public:
        T data;
        TreeNode* left;
        TreeNode* right;

        TreeNode(T value) : data(value), left(nullptr), right(nullptr) {}
};

template <typename T>
class Tree {
    private:
        TreeNode<T>* root;

        // method locates insertion point and inserts a new node.
        void insert(TreeNode<T>*& node, T value) {
            if (node == nullptr) {
                node = new TreeNode<T>(value);
            } else if (value < node->data) {
                insert(node->left, value);
            } else {
                insert(node->right, value);
            }
        }

        // helper method for in-order traversal
        void inOrderHelper(TreeNode<T>* node) const{
            if (node != nullptr) {
                inOrderHelper(node->left);
                std::cout << node->data << " ";
                inOrderHelper(node->right);
            }
        }

        // helper method for finding the minimum value in a tree
        TreeNode<T>* findMin(TreeNode<T>* node) {
            while (node->left != nullptr) {
                node = node->left;
            }
            return node;
        }

        // method removes a node with the given value from the tree.
        TreeNode<T>* removeNode(TreeNode<T>*& node, T value) {
            if (node == nullptr) {
                return node;
            }
            if (value < node->data) {
                node->left = removeNode(node->left, value);
            } else if (value > node->data) {
                node->right = removeNode(node->right, value);
            } else {
                if (node->left == nullptr) {
                    TreeNode<T>* temp = node->right;
                    delete node;
                    return temp;
                } else if (node->right == nullptr) {
                    TreeNode<T>* temp = node->left;
                    delete node;
                    return temp;
                }
                TreeNode<T>* temp = findMin(node->right);
                node->data = temp->data;
                node->right = removeNode(node->right, temp->data);
            }
            return node;
        }

        // helper method for pre-order traversal
        void preOrderHelper(TreeNode<T>* node) const {
            if (node != nullptr) {
                std::cout << node->data << " ";
                preOrderHelper(node->left);
                preOrderHelper(node->right);
            }
        }

        // helper method for searching a value in pre-order traversal
        bool preOrderSearchHelper(TreeNode<T>* node, T value) const {
            if (node == nullptr) {
                return false;
            }
            if (node->data == value) {
                return true;
            }
            return preOrderSearchHelper(node->left, value) || preOrderSearchHelper(node->right, value);
        }

        // helper method for searching a value in in-order traversal
        bool inOrderSearchHelper(TreeNode<T>* node, T value) const {
            if (node == nullptr) {
                return false;
            }
            if (node->data == value) {
                return true;
            }
            return inOrderSearchHelper(node->left, value) || inOrderSearchHelper(node->right, value);
        }

        // helper method for post-order traversal
        void postOrderHelper(TreeNode<T>* node) const {
            if (node != nullptr) {
                postOrderHelper(node->left);
                postOrderHelper(node->right);
                std::cout << node->data << " ";
            }
        }

        // helper method for searching a value in post-order traversal
        bool postOrderSearchHelper(TreeNode<T>* node, T value) const {
            if (node == nullptr) {
                return false;
            }
            if (node->data == value) {
                return true;
            }
            return postOrderSearchHelper(node->left, value) || postOrderSearchHelper(node->right, value);
        }

        // helper method for outputting the tree structure
        void outputTreeHelper(TreeNode<T>* node, int depth, int level) const {
            if (node == nullptr) {
                return;
            }
            outputTreeHelper(node->right, depth, level + 1);
            std::cout << std::string(depth * (level + 1), ' ') << node->data << std::endl;
            outputTreeHelper(node->left, depth, level + 1);
        }

        public:
            Tree() : root(nullptr) {}

            // method inserts a new value into the tree.
            void insert(T value) {
                insert(root, value);
            }

            // method performs in-order traversal of the tree.
            void inOrderTraversal() const {
                inOrderHelper(root);
                std::cout << std::endl;
            }

            // method removes a node with the given value from the tree.
            void remove(T value) {
                removeNode(root, value);
            }

            // method performs pre-order traversal of the tree.
            void preOrderTraversal() const {
                preOrderHelper(root);
                std::cout << std::endl;
            }

            // method performs pre-order search of the tree.
            bool preOrderSearch(T value) const {
                return preOrderSearchHelper(root, value);
            }

            // method performs in-order traversal of the tree.
            bool inOrderSearch(T value) const {
                return inOrderSearchHelper(root, value);
            }

            // method performs post-order traversal of the tree.
            void postOrderTraversal() const {
                postOrderHelper(root);
                std::cout << std::endl;
            }

            // method performs post-order search of the tree.
            bool postOrderSearch(T value) const {
                return postOrderSearchHelper(root, value);
            }

            // method outputs the tree structure.
            void outputTree(int depth) const {
                outputTreeHelper(root, depth, 0);
            }
            
};

int main() 
    {
        Tree<int> tree;
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);
        tree.insert(1);
        tree.insert(9);
        tree.insert(10);
        tree.inOrderTraversal();
        tree.preOrderTraversal();
        tree.postOrderTraversal();
        tree.outputTree(2);
        tree.remove(5);
        tree.inOrderTraversal();
        tree.preOrderTraversal();
        tree.postOrderTraversal();
        tree.outputTree(2);
        return 0;
    }