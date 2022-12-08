# https://www.acmicpc.net/problem/15650

import sys


input = sys.stdin.readline

N, M = map(int, input().split())

case = []

def dfs(start):
    # 출력 조건 및 한 단계의 dfs 탈출 조건
    if len(case) == M:
        print(' '.join(map(str,case)))
        return
    
    for i in range(start, N+1):

        # 가치 치지 1번째 조건(중복 X)
        if i in case:
            continue

        case.append(i)
        # 가지 치기 2번째 조건, 오름 차순을 만족하기 위해 dfs 수행할 다음 번호는 +1를 해준다.
        dfs(i+1)
        case.pop()

dfs(1)