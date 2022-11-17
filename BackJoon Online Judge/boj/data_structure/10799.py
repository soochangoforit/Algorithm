# https://www.acmicpc.net/problem/10799

import sys


steel = sys.stdin.readline().rstrip()

stack = []

result = 0

for i in range(len(steel)):
    # 스택에 추가
    if steel[i] == '(':
        stack.append(steel[i])

    # ")"인 경우    
    else:
        stack.pop()
        # 바로 직전의 괄호가 닫히는 부분 , 곧 레이저 발사 위치
        if steel[i-1] == '(':
            # "(" 로 열려 있는 개수만큼 잘라지기 때문에 결과값에 더해준다.
            result += len(stack)
        else:
            # 레이저가 아닌 한개의 막대기에 대한 끝 부분이기 때문에, 잘린 개수에 1를 더해준다. 왜냐하면 문제에서 쇠막대기는 자르는 레이저가 적어도 하나가 존재하기 때문이다.
            result += 1

print(result)