# https://www.acmicpc.net/problem/11403


import sys


N = int(sys.stdin.readline().rstrip())

# 빈 2중 배열을 만든다.
graph = [ [] * N for _ in range(N) ]

# 그래프를 입력받는다.
for i in range(N):
    graph[i] = list(map(int, sys.stdin.readline().rstrip().split()))

# 플로이드 와샬 알고리즘을 사용한다.
# (2,0) <-> (0,1) 이 접근가능하면
# (2,1)도  접근가능하다.
# (2,1)이 접근 가능하면, (1,1)도 접근 가능하다.
for k in range(N):
    for i in range(N):
        for j in range(N):
            if graph[i][k] and graph [k][j]:
                graph[i][j] = 1

for i in range(N):
    for j in range(N):
        print(graph[i][j], end=" ")
    print()

