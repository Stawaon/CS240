""" #CS240 Lab 1 - Using AVLtree_structures """ 
# The program solves the Towers of Hanoi problem using AVL trees to maintain balance and add/remove disks.
# It moves recursively between towers, outputs steps and final state, and adheres to puzzle rules.

class AVLNode:
    """A node in the AVL tree representing a disk."""
    def __init__(self, data):
        self.data = data
        self.left = None
        self.right = None
        self.height = 1

class Tower:
    """Simplified AVL tree implementation for a Tower."""
    def __init__(self):
        self.root = None

    def insert(self, node, data):
        """Insert a new disk into the AVL tree."""
        if not node:
            return AVLNode(data)
        if data < node.data:
            node.left = self.insert(node.left, data)
        else:
            node.right = self.insert(node.right, data)
        node.height = 1 + max(self.get_height(node.left), self.get_height(node.right))
        return self.balance(node, data)

    def delete(self, node, data):
        """Delete a disk from the AVL tree."""
        if not node:
            return node
        if data < node.data:
            node.left = self.delete(node.left, data)
        elif data > node.data:
            node.right = self.delete(node.right, data)
        else:
            if not node.left:
                return node.right
            elif not node.right:
                return node.left
            min_node = self.find_min(node.right)
            node.data = min_node.data
            node.right = self.delete(node.right, min_node.data)
        node.height = 1 + max(self.get_height(node.left), self.get_height(node.right))
        return self.balance(node, data)

    def push(self, data):
        """Add a disk to the tower."""
        self.root = self.insert(self.root, data)

    def pop(self):
        """Remove and return the smallest disk from the tower."""
        min_node = self.find_min(self.root)
        self.root = self.delete(self.root, min_node.data)
        return min_node.data

    def display(self):
        """Print the AVL tree in sorted order."""
        def in_order(node):
            return in_order(node.left) + [node.data] + in_order(node.right) if node else []
        print(in_order(self.root))

    def balance(self, node, data):
        """Balance the node after insertion or deletion."""
        balance_factor = self.get_balance(node)
        if balance_factor > 1:
            if data < node.left.data:
                return self.rotate_right(node)
            else:
                node.left = self.rotate_left(node.left)
                return self.rotate_right(node)
        if balance_factor < -1:
            if data > node.right.data:
                return self.rotate_left(node)
            else:
                node.right = self.rotate_right(node.right)
                return self.rotate_left(node)
        return node

    def rotate_right(self, y):
        """Perform a right rotation."""
        x = y.left
        y.left = x.right
        x.right = y
        y.height = 1 + max(self.get_height(y.left), self.get_height(y.right))
        x.height = 1 + max(self.get_height(x.left), self.get_height(x.right))
        return x

    def rotate_left(self, x):
        """Perform a left rotation."""
        y = x.right
        x.right = y.left
        y.left = x
        x.height = 1 + max(self.get_height(x.left), self.get_height(x.right))
        y.height = 1 + max(self.get_height(y.left), self.get_height(y.right))
        return y

    def get_height(self, node):
        return node.height if node else 0

    def get_balance(self, node):
        return self.get_height(node.left) - self.get_height(node.right) if node else 0

    def find_min(self, node):
        return node if not node.left else self.find_min(node.left)

# Recursive function to solve Towers of Hanoi
move_count = 0

def move_disks(n, source, destination, auxiliary):
    global move_count
    if n == 1:
        disk = source.pop()
        destination.push(disk)
        move_count += 1
        print(f"Move disk {disk} from Source to Destination")
    else:
        move_disks(n - 1, source, auxiliary, destination)
        disk = source.pop()
        destination.push(disk)
        move_count += 1
        print(f"Move disk {disk} from Source to Destination")
        move_disks(n - 1, auxiliary, destination, source)

# Main program
if __name__ == "__main__":
    num_disks = int(input("Enter the number of disks: "))

    A = Tower()
    B = Tower()
    C = Tower()

    for i in range(num_disks, 0, -1):
        A.push(i)

    print("Initial State:")
    print("Tower A: ", end="")
    A.display()
    print("Tower B: ", end="")
    B.display()
    print("Tower C: ", end="")
    C.display()

    move_disks(num_disks, A, C, B)

    print("\nFinal State:")
    print("Tower A: ", end="")
    A.display()
    print("Tower B: ", end="")
    B.display()
    print("Tower C: ", end="")
    C.display()
    print(f"Total moves: {move_count}")




