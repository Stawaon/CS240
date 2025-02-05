#CS 240 DisplayPrimeNum 

""""
The program efficiently finds and prints the first 50 prime numbers 
in descending order using a stack (LIFO) class. 
It uses push, pop, and size operations, checks for prime numbers, 
generates and stores primes, and displays them in descending order.

"""



import math

class Stack:
    """A simple stack implementation"""
    def __init__(self):
        self.stack = []

    def push(self, item):
        """Push an item onto the stack"""
        self.stack.append(item)

    def pop(self):
        """Pop an item from the stack"""
        if not self.is_empty():
            return self.stack.pop()
        return None

    def is_empty(self):
        """Check if the stack is empty"""
        return len(self.stack) == 0

    def size(self):
        """Return the size of the stack"""
        return len(self.stack)

def is_prime(n):
    """Returns True if n is a prime number"""
    if n < 2:
        return False
    for i in range(2, int(math.sqrt(n)) + 1):
        if n % i == 0:
            return False
    return True

def generate_primes(count):
    """Generates the first 'count' prime numbers"""
    primes = []
    num = 2
    while len(primes) < count:
        if is_prime(num):
            primes.append(num)
        num += 1
    return primes

# Create a stack instance
prime_stack = Stack()

# Generate first 50 prime numbers
primes = generate_primes(50)

# Push all prime numbers to stack
for prime in primes:
    prime_stack.push(prime)

# Display the prime numbers in descending order
print("\n🔥 The First 50 Prime Numbers in Descending Order 🔥\n")
print("=" * 50)
row = 0

while not prime_stack.is_empty():
    print(f"{prime_stack.pop():>4}", end=" ")  # Pop and print from stack
    row += 1
    if row % 10 == 0:  # Print in a structured table format
        print()

print("\n" + "=" * 50)
print("✅ Stack emptied: All prime numbers displayed!\n")
