# https://www.acmicpc.net/problem/1260

from collections import deque
import sys


N, M, V = map(int, sys.stdin.readline().split())

# index를 노드라고 판단하고 연결된 노드를 배열로 넣기
graph = [[] for _ in range(N+1)]

# 방문한 노드를 확인하기 위한 배열 -> 노드의 개수만큼
visited = [0]*(N+1)

# 간선의 개수
for _ in range(M):
    a, b = map(int, sys.stdin.readline().split())
    
    # 노드 양방향 연결
    graph[a].append(b)
    graph[b].append(a)

# 노드 번호가 작은 것부터 방문하기 위해서 오름차순 sort
for i in range(1,N+1):
    graph[i].sort()

# BFS
def bfs(v):
        
    # bfs하고자 하는 숫자를 deque로 관리
    # deque에 단일 값을 넣더라도 배열로 넣도록 한다.
    # 확장성 위해서
    Q=deque([v])
    visited[v] = 1
    

    # Q에는 배열이 들어간다.
    while Q:

        now = Q.popleft()
        print(now, end=' ')

        # now 노드와 연결된 노드를 확인
        for i in graph[now]:

            # 방문하지 않는 경우
            if visited[i] == 0:
                visited[i] = 1
                # 각 연결된 노드에서 또 연결된 노드를 넣는다.
                Q.append(i)
      
# DFS
def dfs(v):
    visited[v]=1
    print(v, end=' ')

    for i in graph[v]:
        if visited[i] == 0:
            dfs(i)

dfs(V)
# 방문 목록 초기화
visited=[0]*(N+1)
print()
bfs(V)



    






