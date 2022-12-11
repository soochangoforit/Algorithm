# 최소 신장 트리 - 프림 알고리즘


import sys


INF = 999999999

INF = 999
adj_mat = [[0, 29, 0, 0, 0, 10, 0],
           [29, 0, 16, 0, 0, 0, 15],
           [0, 16, 0, 12, 0, 0, 0],
           [0, 0, 12, 0, 22, 0, 18],
           [0, 0, 0, 22, 0, 27, 25],
           [10, 0, 0, 0, 27, 0, 0],
           [0, 15, 0, 18, 25, 0, 0]]

node_num = len(adj_mat)
visited = [False] * node_num
# 최대값으로 초기화 하는 이유는 최소 신장 트리에서 인접한 좌표들만 우선 작은 숫자로 할당한다. (큰 숫자로 남아있는 경우는 아직 최소 신장 트리가 도달하지 못하는 정점이다.)
distances = [INF] * node_num

# 최소 신장 트리가 도달할 수 있는 정점에서 최소 거리를 가지는 "정점"을 반환하는 함수
def get_min_node(node_num):
    # 우선 방문하지 않은 정점 중 하나를 임의로 선택 후 break
    for i in range(node_num):
        if not visited[i]:
            v = i
            break
    
    # 본격적으로 방문하지 않은 정점 중 최소 거리를 가지는 정점을 찾는다
    for i in range(node_num):
        if not visited[i] and distances[i] < distances[v]:
            v = i

    # 최소 거리를 가지는 정점을 반환
    return v



def prim(start, node_num):
    # distances 배열과 visted 배열 초기화
    for i in range(node_num):
        visited[i] = False
        # 최대값으로 초기화 하는 이유는, 최소 신장 트리에서 인접한 좌표들만 우선 작은 숫자로 할당한다.
        # INF로 그대로 거리를 가지고 있는 정점들은, 최소 거리 정점을 찾는데 "대상(target)으로 간주되지 않게 하기 위해서"
        distances[i] = INF

    # 시작노드의 distance 값을 0으로 초기화
    distances[start] = 0

    for i in range(node_num):
        # 신장 트리 나무가 이동할 수 있는 인접 중에서 최소 거리를 갖고 있는 Node를 반환한다.
        node = get_min_node(node_num)
        visited[node] = True    # node 를 방문했다 표시

        # 방금전에 방문한 node 를 기준으로 이동할 수 있는 인접한 정점들의 거리를 갱신한다.
        for j in range(node_num):
            # 해당 node 기준으로 인접한 좌표이면서
            if adj_mat[node][j] != 0:
                # 방문하지 않은 정점이면서, 기존의 거리(INF 혹은 다른 정점에 의해서 측정된 거리 == 하나의 특정 정점으로 갈수있는 간선이 2개 이상 있는 경우)보다 현재 node를 거쳐서 가는 거리가 더 짧다면
                if not visited[j] and adj_mat[node][j] < distances[j]:
                    # 해당 정점의 거리를 갱신한다. (기존의 거리보다 짧게 도달할 수 있다는 의미)
                    distances[j] = adj_mat[node][j]


print(prim(0, node_num))
print("distances: ", distances)
print("minimum cost: ", sum(distances))