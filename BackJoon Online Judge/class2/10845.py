# https://www.acmicpc.net/problem/10845

import sys
from collections import deque

N=int(sys.stdin.readline().rstrip())
queue=deque() # deque 사용, queue보다 양방향으로 접근 가능하고 queue보다 빠르다.

for i in range(N):
    comd=sys.stdin.readline().split()

    if comd[0]=='push':
        queue.append(int(comd[1]))
    elif comd[0]=='pop':
        if queue:
            print(queue.popleft()) # 첫번째 값을 뺀다, 맨뒤는 pop을 활용
        else: 
            print(-1)
    elif comd[0]=='size':
        print(len(queue))
    elif comd[0]=='empty':
        if queue: 
            print(0)
        else: 
            print(1)
    elif comd[0]=='front':
        if queue: 
            print(queue[0])
        else: 
            print(-1)
    elif comd[0]=='back':
        if queue: 
            print(queue[-1])
        else: 
            print(-1)



