# https://www.acmicpc.net/problem/11866

from collections import deque
import sys

n , k = map(int , sys.stdin.readline().rstrip().split(" "))

result = []

queue = deque( x for x in range(1,n+1) )
# queue = deque([ x for x in range(1,k+1)])

while queue:
    queue.rotate(-(k-1))
    a = queue.popleft()
    result.append(a)


last = result[n-1]
print("<" , end="")
for i in result:
    if(i == last):
        print(i , end="")
    else:
        print(i , end=", ")
print(">")


