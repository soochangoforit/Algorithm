# https://www.acmicpc.net/problem/11724

from collections import deque
import sys


N , M = map(int, sys.stdin.readline().rstrip().split())

# 0 index는 사용하지 않을려고 한다.
graph = [ [] for _ in range(N+1)]

visited=[0] * (N+1)

count = 0

for _ in range(M):
    a,b = map(int, sys.stdin.readline().split())

    # 양방향 설정
    graph[a] += [b]
    graph[b] += [a]


def bfs(M):
    global count

    Q=deque([M])
    visited[M]=1
    
    # Q에 데이터가 들어오는 시점부터 방문 처리를 바로 해준다.
    while Q:
        now=Q.popleft()
        
        # now 노드와 연결된 노드를 확인
        for i in graph[now]:

            # 방문하지 않는 경우
            if visited[i] == 0:
                visited[i] = 1
              
                # 각 연결된 노드에서 또 연결된 노드를 넣는다.
                Q.append(i)

# 문제에서는 인접한 요소의 개수가 아닌, 간선으로 연결된 전체 연결 요소의 개수를 구하고자 한다.
for i in range(1, N + 1):
    # BFS를 통해 연결된 여러 노드를 방문처리하고, 방문된 노드들은 모두 1개의 선으로 연결되어 있다.
    # 다음 노드를 이미 방문했다면, 방문하지 않은 노드를 찾고 count 개수를 올려준다.
    if visited[i] == 0:
        bfs(i)
        count += 1

print(count)