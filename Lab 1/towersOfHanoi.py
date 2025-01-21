def move(n, src, dest, count):
    lastIdx = len(src)-1
    val = src[lastIdx]
    print("Move disk", val, "from", src, "to", dest)
    dest.append(val)
    src.pop(lastIdx)
    count.append(1)
    
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
for i in range(diskNum):
    src.append(diskNum - i)
print("The moves are:")
stack(len(src), src, aux, dest, count)
print("Total number of moves:", len(count))
