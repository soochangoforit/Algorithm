# https://www.acmicpc.net/problem/1874

import sys


n = int(sys.stdin.readline().rstrip()) # 반복할 횟수

count = 1 # 반복할 횟수만큼 stack에 저장될 숫자
stack = []

result = [] # 결과값을 저장하는 배열 -> + , -

no_message = True # 수열을 만들 수 없으면 False

for i in range(1 , n + 1): # N번 반복

    target = int(sys.stdin.readline().rstrip()) # 수열 한글자씩 받아옴

    while count <= target: # 수열의 숫자가 stack에 담기기 전까지 계속 추가
        stack.append(count)
        result.append("+")
        count += 1 # 다음 stack에 들어갈 숫자 +1
    
    if stack[-1] == target: # 수열의 숫자가 stack의 맨마지막에 담기는 경우
        stack.pop()
        result.append("-")
    else: # 해당 문제의 조건에서 수열은 target이 stack의 맨 위쪽 부분과 맡지 않으면 애초에 수열을 만들 수 없다. 반드시 같아야지 수열
         # 을 만들 수 있다. -- key point
        no_message = False
        break

if no_message == False:
    print('NO')
else:
    print("\n".join(result))

