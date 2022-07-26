# https://www.acmicpc.net/problem/1158

from collections import deque
import sys

input = sys.stdin.readline

n , k = map(int , input().rstrip().split(" "))

queue = deque( x for x in range(1,n+1))

result=[]

for i in range(n):
    queue.rotate(-(k-1))
    a = queue.popleft()
    result.append(a)

last = result[n-1]

print("<" , end="")
for i in result:
    if i == last:
        print(i , end="")
    else:
        print(i , end=", ")
print(">")

