# https://www.acmicpc.net/problem/1922
# using heapq
# 간선의 개수  < 노드의 개수 => 크루스칼 알고리즘을 사용하고
# 간선의 개수 > 노드의 개수 => 프림 알고리즘을 사용하면 된다.

import sys
import heapq
# 노드의 개수
n = int(sys.stdin.readline())
# 간선의 개수
m = int(sys.stdin.readline())

graph = [[] for _ in range(n+1)]

for _ in range(m):
    a, b, cost = map(int, sys.stdin.readline().split())
    graph[a].append((b, cost))
    graph[b].append((a, cost))

visited = [False] * (n+1)

q = []
heapq.heappush(q, (0, 1))

result = 0

while q:
    cost, node = heapq.heappop(q)

    if not visited[node]:
        visited[node] = True
        result += cost

        for node, distance in graph[node]:
            heapq.heappush(q, (distance, node))

print(result)



