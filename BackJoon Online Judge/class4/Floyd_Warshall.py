# 플로이드 - 워셜 알고리즘 학습

# 플로이드 - 워셜 알고리즘
# 모든 노드에서 다른 모든 노드까지의 최단 경로를 모두 계산
# 다이나믹 프로그래밍 유형에 속함
# 2차원 테이블에 최단 거리 정보를 저장

import sys


input = sys.stdin.readline

INF = int(1e9)

# 노드와 간선의 개수 입력 받기
V, E = map(int, input().split())

# 2차원 리스트(그래프 표현)를 만들고, 모든 값을 무한으로 초기화
graph = [[INF] * (V + 1) for _ in range(V + 1)]

# 자기 자신에서 자기 자신으로 가는 비용은 0으로 초기화
for a in range(1, V + 1):
    for b in range(1, V + 1):
        if a == b:
            graph[a][b] = 0


# 각 간선에 대한 정보를 입력 받아, 그 값으로 초기화 -> 초기에 한번에 도달하지 못하는 정점에 대해서는 INF로 초기화 되어 있다.
for _ in range(E):
    # A에서 B로 가는 비용은 cost라고 설정
    a, b, cost = map(int, input().split())
    graph[a][b] = cost

# 점화식에 따라 플로이드 워셜 알고리즘을 수행
# k는 거쳐가는 노드 (기준이 되는 노드이다)
for k in range(1, V + 1):
    # a는 출발 노드
    for a in range(1, V + 1):
        # b는 도착 노드
        for b in range(1, V + 1):
            graph[a][b] = min(graph[a][b], graph[a][k] + graph[k][b])

# 수행된 결과를 출력
for a in range(1, V + 1):
    for b in range(1, V + 1):
        # 도달할 수 없는 경우, 무한(INFINITY)이라고 출력
        if graph[a][b] == INF:
            print("INFINITY", end=" ")
        # 도달할 수 있는 경우 거리를 출력
        else:
            print(graph[a][b], end=" ")
    print()

# 입력 예시
# 4 7
# 1 2 4
# 1 4 6
# 2 1 3
# 2 3 7
# 3 1 5
# 3 4 4
# 4 3 2

# 출력 예시
# 0 4 8 6
# 3 0 7 9
# 5 9 0 4
# 7 11 2 0

# 시간 복잡도
# O(N^3) -> N은 노드의 개수
