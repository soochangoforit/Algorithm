from collections import deque

dx = [0,0,1,-1]
dy = [1,-1,0,0]

t = int(input())

def bfs(graph, a, b):
    queue = deque()
    queue.append((a,b))
    # 지나온 위치 0으로 바꾸기
    graph[a][b] = 0

    while queue:
        x, y = queue.popleft()
        # 4방향으로 1이면 0으로 바꾸기
        for i in range(4):
            nx = x+dx[i]
            ny = y+dy[i]
            if nx < 0 or nx >=M or ny < 0 or ny >=N :
                continue
            if graph[nx][ny] == 1:
                graph[nx][ny] = 0
                # bfs 방법이므로 4방향이 다 끝나고 접근하기 위해 queue에 넣는다.
                queue.append((nx, ny))
    return



for i in range(t):
    # 배추흰지렁이의 수
    cnt = 0
    M, N, K = map(int,input().split())

    # 가로 M줄, 세로 N줄
    # graph = [N][M]
    graph = [[0]*M for _ in range(N)]

    # 배추 위치 표시
    for j in range(K):
        # x: 열, y: 행
        x, y = map(int, input().split())
        graph[y][x] = 1

    # 배추흰지렁이 수 구하기
    # M : 열 , N : 행
    for a in range(N):
        for b in range(M):
            if graph[a][b] == 1:
                bfs(graph, a, b)
                cnt += 1
    print(cnt)