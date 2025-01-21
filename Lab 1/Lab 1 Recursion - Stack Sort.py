"""
This Program sorts a stack of given integers from smallest to largest using recursion. 
I used w3schools.com as a reference for the stack class and methods.
"""

# Lab 1: Recursion
class Stack:
    def __init__(self):
        self.stack = []

    def push(self, element):
        self.stack.append(element)

    def pop(self):
        if self.isEmpty():
            return "Stack is empty"
        return self.stack.pop()

    def peek(self):
        if self.isEmpty():
            return "Stack is empty"
        return self.stack[-1]
    
    def isEmpty(self):
        return len(self.stack) == 0
    
    def size(self):
        return len(self.stack)
    
# End of Stack class    

# Method to insert element into stack sorted
def sortInsert(stack, element):

    # Base case, stack is empty or new element is greater than current top element of stack
    if stack.size() == 0 or element > stack.peek():
        stack.push(element)
        return
    else:

        # If element is less than item in stack, remove top item in stack and call recursion
        temp = stack.pop()
        sortInsert(stack, element)

        # After recursion, place item back on top of stack
        stack.push(temp)

# Method to sort stack
def sortStack(stack):

    # Base case check if stack is empty
    if stack.size() != 0:

        # Remove top item
        temp = stack.pop()

        # Sort stack
        sortStack(stack)
        
        # Put top item back on stack sorted
        sortInsert(stack, temp)


# Initialize our stack
stack = Stack()

# Populate the stack
stack.push(30)
stack.push(-5)
stack.push(18)
stack.push(14)
stack.push(-3)

# Print pre-sorted stack
print("Unsorted Stack: ", stack.stack)

# Call sortStack to sort the stack
sortStack(stack)

# Print sorted stack
print("Sorted Stack: ", stack.stack)


