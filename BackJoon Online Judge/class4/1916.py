# https://www.acmicpc.net/problem/1916

import heapq
import sys

input = sys.stdin.readline

city = int(input().rstrip())
bus = int(input().rstrip())

# 다익스트라 알고리즘을 활용한다.

INF = int(1e9)

# 거리를 최대값으로 초기화
distance = [INF] * (city + 1) 

# 간선을 저장할 graph
graph = [ [] for _ in range(city+1) ]

# 간선을 입력 받는다. (초기화 과정)
for i in range(bus):
    a, b, cost = map(int, input().split())
    graph[a].append((cost, b))


def dijkstra(start):

    q = []
    # 초기 노드 값을 cost 0으로 넣어준다.
    heapq.heappush(q, (0, start))
    # 초기 스타트 노드는 거리를 0으로 초기화
    distance[start] = 0

    while q:
        # 가중치가 가장 낮은 간선을 꺼낸다.
        dist, now = heapq.heappop(q)

        # 현재 now 노드의 distance가 기존의 다른 노드에 의해서 방문 되어진 distance[now] 보다 크다면, 이미 지금보다 빠른 경로로 도달했다는 의미이므로 무시하고 넘어간다.
        if distance[now] < dist:
            continue

        # if문을 거쳐서 이까지 왔다는 의미는 해당 now노드가 최선의 거리로 거쳐왔고, 이게 주변 인접 노드에 대해서 distance를 새롭게 갱신시킬 가치가 있다.
        for i in graph[now]:

            cost = dist + i[0]

            if cost < distance[i[1]]:
                distance[i[1]] = cost
                heapq.heappush(q, (cost, i[1]))


start, end = map(int, input().split())

dijkstra(start)

print(distance[end])
