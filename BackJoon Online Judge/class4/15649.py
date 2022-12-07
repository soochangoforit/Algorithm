# https://www.acmicpc.net/problem/15649
# 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
# 여기서는 "중복 없이"가 가지치기를 위한 조건이다.

import sys


input = sys.stdin.readline

N, M = map(int, input().split())

# 숫자들이 들어갈 케이스 리스트
case = []

def dfs():
    # 출력하는 조건
    if len(case) == M:
        print(' '.join(map(str, case)))
        # 출력을 했으면 return을 통해서 해당 깊이를 탈출한다.
        return
    

    # 모든 경우의 수를 탐색해야 한다.
    for i in range(1, N+1):
        # 다만 문제에서 제시한 조건대로, 숫자 중복은 방지한다.
        # 여기서의 가지치기에 해당한다.
        if i in case:
            continue

        case.append(i)
        dfs()
        # list를 마치 stack처럼 동작하기 위해서 pop 필요하다.
        # pop을 통해서 깊이만큼 들어간 숫자 모두 pop 해줌으로써 다음 for 진행할때는 case를 완전히 빈 상태로 만들어준다.
        case.pop()

dfs()

