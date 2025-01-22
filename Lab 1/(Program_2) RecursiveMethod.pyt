#CS240 Lab 1 - Character Count Using Recursion
""" 
This program takes a string and a character as input, and uses recursion to 
count how many times the character appears in the string.
It includes a recursive function `count` and a helper function `count_occurrences`. 
The result is printed along with the character being searched. 
"""



def count(chars, ch, high):
    """Recursive function to count occurrences of a character in a list."""
    # Base case: If the index is below 0, stop recursion
    if high < 0:
        return 0
    # Check if the current character matches and add 1 if it does
    # Combines the result of the current character check with the recursive call
    return (1 if chars[high] == ch else 0) + count(chars, ch, high - 1)

def count_occurrences(chars, ch):
    """Helper function to initiate the recursive count."""
    return count(chars, ch, len(chars) - 1)

# Main program
if __name__ == "__main__":
    # Prompt the user for input
    input_string = input("Enter a string: ")
    search_char = input("Enter a character to count: ")

    # Convert the string to a list of characters
    char_list = list(input_string)

    # Count occurrences
    occurrences = count_occurrences(char_list, search_char)

    # Output the result
    print(f"{search_char} appears {occurrences} times")
