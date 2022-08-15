# https://www.acmicpc.net/problem/18111
# 해당 문제의 유형은 브르투 포스 문제이므로 모든 경우의 수에 대해
# 생각을 한다면 풀이 전략을 떠올릴 수 있다.

import sys


N, M ,B = map(int, sys.stdin.readline().split())

# 이중 배열
graph = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]

# 초기 걸리는 시간 -> 지속적으로 최저 시간 업데이트 예정
answer= sys.maxsize

# 초기 기준이 되는 층수
idx = 0

# 0층 부터 256층까지 비교하면서 최저의 시간을 찾아낸다.
for target in range(257):
    # 인벤토리에서 블록을 꺼내야 하는 개수
    min_target = 0
    # 직접 블록을 깍아야 하는 개수
    max_target= 0

    for i in range(N):
        for j in range(M):

            # 현재 존재하는 블럭이 기준이 되는 층보다 높을 경우
            if graph[i][j] >= target:
                max_target += graph[i][j] - target # 블럭을 깍아내야 하는 블럭 수 증가

            else:
                min_target += target - graph[i][j]

    # 블록을 깍아내야 하는 개수(max_target)와 원래 있던 블록(B)의 합이 더 커야 층을 만들 수 있다.
    # 층을 만들 수 없는 경우에 대해서는 다음 for문으로 넘어가기 위함이다.
    if max_target + B >= min_target:
        
        # 층을 만들 수 있는 경우에 대해서는 최저의 시간을 업데이트 해준다.
        # 층을 만들 수 있다 하더라도 최저의 시간보다 더 작게 걸리는 경우에 대해서만 answer를 업데이트 한다.
        if min_target + (max_target * 2) <= answer:
            answer = min_target + (max_target * 2)
            idx = target

print(answer, idx)
