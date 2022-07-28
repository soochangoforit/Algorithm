# https://www.acmicpc.net/problem/4949

import sys


while True:
    text = sys.stdin.readline().rstrip()

    if text == '.':
        break

    stack = []
    flag = True

    for i in text:
        if i == '(' or i =='[': # 열린 괄호가 있으면 stack에 저장
            stack.append(i)
        elif i == ')': # 닫힌 괄호가 있으면 stack에서 꺼냄
            if not stack or stack[-1] == '[':
                flag = False
                break
            elif stack[-1] == '(':
                stack.pop()

        elif i == ']': # 닫힌 괄호가 있으면 stack에서 꺼냄
            if not stack or stack[-1] == '(':
                flag = False
                break
            elif stack[-1] == '[':
                stack.pop()

    if not stack and flag==True:
        print('yes')
    else:
        print('no')