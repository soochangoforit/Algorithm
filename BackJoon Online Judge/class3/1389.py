# https://www.acmicpc.net/problem/1389

from collections import deque
import sys

# 케빈 베이컨 수는 자기 자신에서 다른 모든 사람까지의 최단 경로의 합이다.
# 케빈 베이컨 수가 가장 작은 사람을 구하라.

N, M = map(int, sys.stdin.readline().split())

# 노드별 연결된 수 저장, 0번째 index는 사용하지 않게 하기 위해서
graph = [[] for _ in range(N+1)]

for i in range(M):
    a, b = map(int, sys.stdin.readline().split())
    graph[a].append(b)
    graph[b].append(a)

# 노드별 케빈 베이컨 수를 저장할 배열
kevin = [0]*(N+1)

# BFS
def bfs(v):
    Q = deque([v])
    visited = [0]*(N+1)
    visited[v] = 1
    cnt = 0

    while Q:
        cnt += 1
        for i in range(len(Q)):
            now = Q.popleft()
            for j in graph[now]:
                if visited[j] == 0:
                    visited[j] = 1
                    kevin[j] += cnt
                    Q.append(j)


for i in range(1, N+1):
    bfs(i)

# 케빈 베이컨 수가 가장 작은 사람을 구한다.
min_kevin = min(kevin[1:])
print(kevin.index(min_kevin))