# https://www.acmicpc.net/problem/9012

import sys


count = int(sys.stdin.readline().rstrip())

for _ in range(count):
    text = sys.stdin.readline().rstrip()
    stack = []
    flag = True

    for i in text:
        if i == '(':
            stack.append(i)
        elif i == ')':
            if not stack:
                flag = False
                break
            elif stack[-1] == '(':
                stack.pop()
    if not stack and flag == True:
        print('YES')
    else:
        print('NO')