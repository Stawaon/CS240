#CS240 Lab 1 - Towers of Hanoi
#This is a towers of Hanoi program that takes the number of disks as input and prints the moves and number of moves to solve the puzzle. 

#Helper function to move the disks
def move(n, src, dest, count):
    lastIdx = len(src)-1
    val = src[lastIdx]
    print("Move disk", val, "from", src, "to", dest)
    dest.append(val)
    src.pop(lastIdx)
    count.append(1)

#Main recursive function to solve the puzzle    
def stack(n, src, aux, dest, count):
    if (n == 0):
        return
    stack(n-1, src, dest, aux, count) 
    move(n, src, dest, count)
    stack(n - 1, aux, src, dest, count)
    return count

diskNum = int(input("Enter the number of disks: "))
src = []
aux = []
dest = []
count = []
#Creates the source stack
for i in range(diskNum):
    src.append(diskNum - i)
print("The moves are:")
#Calls the stack function to solve the puzzle
stack(len(src), src, aux, dest, count)
#Prints the total number of moves
print("Total number of moves:", len(count))
