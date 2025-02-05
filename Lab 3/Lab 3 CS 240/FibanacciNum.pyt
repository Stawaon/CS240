
#CS 240 FibanacciNum
"""
This program uses an iterator to efficiently generate and print Fibonacci numbers up to a specified limit, 
efficiently storing them in memory without storing them all in memory.
"""

class FibonacciIterator:
    """An iterator to generate Fibonacci numbers up to a given limit."""
    
    def __init__(self, max_limit):
        """Initialize the iterator with a limit."""
        self.max_limit = max_limit
        self.a, self.b = 0, 1  # Standard Fibonacci sequence starts with 0, 1
    
    def __iter__(self):
        """Return the iterator object itself."""
        return self
    
    def __next__(self):
        """Generate the next Fibonacci number within the limit."""
        if self.a > self.max_limit:
            raise StopIteration  # Stop when exceeding max_limit
        fib_num = self.a
        self.a, self.b = self.b, self.a + self.b  # Move to the next Fibonacci number
        return fib_num

# Test program to display Fibonacci numbers up to 100000 in a formatted manner
max_value = 100000
print("\n🔥 Fibonacci numbers less than or equal to",max_value , "🔥\n")
print("=" * 90)

row_count = 0
for num in FibonacciIterator(max_value):
    print(f"{num:>7}", end="  ")  # Print numbers with right alignment
    row_count += 1
    if row_count % 10 == 0:  # Print in rows of 10
        print()

print("\n" + "=" * 90)
print("✅ Completed: All Fibonacci numbers displayed! 🎉\n")
