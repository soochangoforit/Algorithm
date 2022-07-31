# https://www.acmicpc.net/problem/10828

import sys
n = int(sys.stdin.readline())

stack=[]
for i in range(n):
    command = sys.stdin.readline().split()

    if command[0]=='push':
        stack.append(command[1])
    elif command[0]=='pop': 
        if len(stack)==0:
            print(-1)
        else:
            print(stack.pop()) # 맨뒤의 정수를 밴다.
    elif command[0] == 'size':
        print(len(stack))
    elif command[0] == 'empty':
        if len(stack)==0:
            print(1)
        else:
            print(0)
    elif command[0] == 'top':
        if len(stack)==0:
            print(-1)
        else:
            print(stack[-1]) # 맨 마지막의 원소를 출력한다.