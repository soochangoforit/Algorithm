# BFS, 주변 간선에 대한 가중치가 모두 동일하면서 최단 거리를 구할때 유용하다.

from collections import deque


N, M = map(int, input().split())

graph = []
for i in range(N):
    graph.append(list(map(int, input())))

# BFS는 방향을 설정 해주자
dx = [-1,1,0,0]
dy = [0,0,1,-1]


def bfs(y,x):

    q = deque()
    q.append((y,x))

    while q:
        y,x = q.popleft()

        for i in range(4):
            ny = y +dy[i]
            nx = x + dx[i]

            # 범위를 벗어나는 경우, 다음으로 넘어간다.
            if ny < 0 or ny >= N or nx < 0  or nx >= M:
                continue

            if graph[ny][nx] == 0:
                continue
        
            if graph[ny][nx] == 1:
                graph[ny][nx] = graph[y][x] + 1
                q.append((ny,nx))
        
    return graph[N-1][M-1]


        

print(bfs(0,0))