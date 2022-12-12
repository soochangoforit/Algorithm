# 다익스트라 알고리즘 학습


# 다익스트라 알고리즘 구현 using heapq
import heapq
import sys

input = sys.stdin.readline
INF = int(1e9)

# 노드의 개수, 간선의 개수 입력받기
V, E = map(int, input().split())

# 시작 노드 번호 입력받기
start = int(input())

# 각 노드에 연결되어 있는 노드에 대한 정보를 담는 리스트 만들기
# index 1에는 1번 노드가 연결되어 있는 인접 노드들의 정보가 담겨있음
graph = [[] for _ in range(V + 1)]

# 최단 거리 테이블을 모두 무한으로 초기화
# 무한으로 초기화하는 이유는, 최단 거리를 구하는 알고리즘을 사용하기 때문에, 최단 거리를 구하기 위해서는 무한으로 초기화해야 한다.
# 마치 최소값을 구하기 위해 처음에 최댓값을 최초 비교값으로 설정하는 것과 같은 이치
distance = [INF] * (V + 1)

# 모든 간선 정보 입력받기
for _ in range(E):
    a, b, cost = map(int, input().split())
    # a번 노드에서 b번 노드로 가는 비용이 cost라는 의미
    # heapq를 사용하기 위해, cost를 앞에 두고, b를 뒤에 둔다. (index 0이 기준점이 된다.)
    graph[a].append((cost, b))
    
# 전체 비용 계산    
total_cost = 0

# 다익스트라 알고리즘
def dijkstra(start):
    global total_cost

    q = []
    # 시작 노드로 가기 위한 최단 경로는 0으로 설정하여, 큐에 삽입
    heapq.heappush(q, (0, start))
    # 첫 노드에 대해서 INF -> 0으로 distance를 초기화
    distance[start] = 0

    # 큐가 비어있지 않다면
    while q: 

        # q에 들어오는 노드의 경우는 자신 노드에게 도달하기 위한 거리는 이미 계산되어 있으므로, 자신 노드에 달려있는 인접 노드의 거리를 계산한다.
        # 방문 가능한, 인접 노드 중에서 가장 최단 거리가 짧은 노드에 대한 정보 꺼내기
        dist, now = heapq.heappop(q)

        # while "이전의 노드 기준"으로 방문 가능한 now 노드(우측 dist)보다 더 작은 경로인 좌측 distance[now]가 이미 있다는 의미
        # while "이전의 노드 기준"으로 방분 가능한 now 노드가 "while 이전 노드 기준"으로 부터 출발하는 것보다, 
        # 다른 노드를 거쳐서 방문하는 거리가 더 짧다는 의미, 따라서 무시
        if distance[now] < dist:
            continue

        # if문을 통과 했다는 의미는, 현재 now 노드에 대해서, distance[now]가 최단 거리라는 의미
        # 이제, 현재 now 노드에 달려있는 인접 노드들에 대해서, 최단 거리를 계산한다.
        # 현재 now 노드의 인접 노드의 거리가가 기존의 distance(인접노드)보다 작은 경우, distance를 갱신할 필요가 있다.
        for i in graph[now]:
            # 현재 now 노드 기준, 인접노드의 최종 dist = 현재 now 노드의 dist + 인접 노드의 dist (i[0])
            cost = dist + i[0]

            # 현재 now 노드 기점으로 인접 노드의 최종 dist < 기존의 distance보다 작은 경우, distance를 갱신해준다.
            if cost < distance[i[1]]:
                # 새로운 최단 거리 dist를 갱신해준다.
                distance[i[1]] = cost

                # now 노드 기준으로 인접 노드의 최종 dist를 큐에 넣어준다.
                heapq.heappush(q, (cost, i[1]))
                # 최소 비용을 계산해준다.
                total_cost += cost



# 다익스트라 알고리즘 수행
dijkstra(start)

# 모든 노드로 가기 위한 최단 거리를 출력
for i in range(1, V + 1):
    # 도달할 수 없는 경우, 무한(INFINITY)이라고 출력
    if distance[i] == INF:
        print("INFINITY")
    # 도달할 수 있는 경우 거리를 출력
    else:
        print(distance[i])

print(total_cost)


# 입력 예시
# 6 11
# 1
# 1 2 2
# 1 3 5
# 1 4 1
# 2 3 3
# 2 4 2
# 3 2 3
# 3 6 5
# 4 3 3
# 4 5 1
# 5 3 1
# 5 6 2

# 출력
# 0
# 2
# 3
# 1
# 2
# 4

# 최소 비용
# 12
