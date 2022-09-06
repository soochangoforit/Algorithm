# https://www.acmicpc.net/problem/2606

from collections import deque


n = int(input()) # 컴퓨터 연결 개수
v = int(input()) # 연결선 개수

graph = [[] for i in range(n+1)] # 그래프 초기화

visited = [0] * (n+1) # 방문 여부 확인 , n+1은 index 1부터 시작하기 위함

for i in range(v):
    a, b = map(int, input().split())
    graph[a].append(b) # 양방향 그래프
    graph[b].append(a)

# 위까지는 DFS, BFS 공통
def dfs(v):
    visited[v] = 1 # 방문 처리

    for nx in graph[v]: # 하나와 연결된 노드부터 계속해서 방문
        if visited[nx] == 0:
            dfs(nx)

dfs(1) #1번 컴퓨터부터 시작 (재귀적으로 탐색)
print(sum(visited) -1)
