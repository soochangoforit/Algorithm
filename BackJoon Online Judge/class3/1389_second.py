# https://www.acmicpc.net/problem/1389


from collections import deque
import sys


def bfs(graph , start):
    # start 노드와 각각의 친구 노드들에 대한 케빈 베이컨 수를 저장한다.
    # 0 index는 사용하지 않는다.
    num = [0] * (N+1)
    # 방문했는지 검사하기 위해서
    visited = [0] * (N+1)

    Q=deque([start])
    # 방문했다고 while 들어가기 전에 표시
    visited[start] = 1

    # While문을 순회하면서 start 노드와 연결된 i 노드의 케빈 베이커 수를 구할때, start 노드까지 오는데 거친 관계(노드 수)를 저장한다.
    while Q:
        now = Q.popleft()
        # start 노드와 연결된 노드들을 방문한다.
        for i in graph[now]:
            # 방문하지 않았을 경우, 방문했다고 알려준다.
            if visited[i] == 0:
                visited[i] = 1
                # 핵심 - now와 연결된 각각의 i 노드들에 now와는 현재 1 차이가 나는 관계를 맺고 있다.
                num[i] = num[now] + 1
                Q.append(i)
    
    return sum(num)



N, M = map(int, sys.stdin.readline().split())

graph = [ [] for _ in range(N+1) ]

for i in range(M):
    a, b = map(int, sys.stdin.readline().split())
    graph[a].append(b)
    graph[b].append(a)

# 각 노드별 케빈 베이컨 수를 저장할 배열
result = []

for i in range(1, N+1):
    result.append(bfs(graph,i))

# result에 저장할때는 0 index 부터 저장했기 때문이다.
# 0번째가 문제에서 1번째라고 응답해야 한다.
print(result.index(min(result))+1)
