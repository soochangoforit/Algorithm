# heapq 모듈의 동작 방식을 이해하고 나서, heapq 모듈을 이용한 프림 알고리즘을 사용하여 문제 해결
# https://www.acmicpc.net/problem/1197
# 시간 복잡도 : O(ElogE) (E는 간선의 개수)

import collections
import heapq
import sys


input = sys.stdin.readline

n,m = map(int, input().split())
graph = collections.defaultdict(list)
visited = [False] * (n+1)

for i in range(m):
    u, v, weight = map(int, input().split())
    # u를 key로 [weight, u, v]를 value로 하는 딕셔너리를 만든다.
    graph[u].append([weight, u,v])
    # v를 key로 [weight, v, u]를 value로 하는 딕셔너리를 만든다.
    graph[v].append([weight, v,u])

# 프림 알고리즘
def prim(graph, start_node):
    visited[start_node] = True
    candidate = graph[start_node]
    # 가중치 비교를 위해 우선순위 큐 사용 (이때, index[0]을 기준으로 최소힙을 구성하는게 default이다.)
    heapq.heapify(candidate)

    mst = [] #mst
    total_weight = 0

    while candidate:
        # 현재 노드에서 갈 수 있는 가중치가 낮은 간선을 뽑아낸다.
        weight, u, v = heapq.heappop(candidate)

        # 해당 v노드가 이미 방문한 좌표인지 확인한다. (사이클이 없어야 하기 때문에)
        if not visited[v]:
            visited[v] = True
            total_weight += weight
            # mst에 추가(딱히 필요는 없음 _ 그냥 담아두는거)
            mst.append((u,v))

            # 이제 최소 신장 트리에 v라는 노드가 추가 되었음으로
            # candidate에는 현재 첫 스타트 노드가 방문 가능한 간선이 들어있고, 이제 v노드를 방문했으니
            # v노드가 방문 가능한 간선 정보들을 candidate에 넣어준다.
            # v가 끝점일 수도 있기 때문에, 초기에 graph 배열에 대해서는 collections.defaultdict를 사용한다.
            # for문을 전부 순회하면서 v노드가 방문 가능한 간선 정보를 모두 candidate에 넣어준다.
            for edge in graph[v]:
                # index 2번이 방문할 수 있는 좌표 정보를 담고 있기 때문이다.
                if not visited[edge[2]]:
                    heapq.heappush(candidate, edge)

    return total_weight

print(prim(graph, 1))


