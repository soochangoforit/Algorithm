# https://www.acmicpc.net/problem/1012

# 재귀 limit 설정
import sys
sys.setrecursionlimit(10000)

dx = [0,0,1,-1]
dy = [1,-1,0,0]

t = int(input())

def dfs(x, y):

    # 방문한 곳 0으로 바꾸기
    graph[x][y] = 0

    # 4방향으로 1이면 0으로 바꾸기
    for i in range(4):
        nx = x+dx[i]
        ny = y+dy[i]

        if (0 <= nx < N) and (0 <= ny < M):
            if graph[nx][ny] == 1:
                dfs(nx, ny)




for i in range(t):
    # 배추흰지렁이의 수
    cnt = 0
    M, N, K = map(int,input().split())

    # 가로 M줄, 세로 N줄
    # graph = [N][M]
    graph = [[0]*M for _ in range(N)]

    # 배추 위치 표시
    for _ in range(K):
        # x: 열, y: 행
        x, y = map(int, input().split())
        graph[y][x] = 1

    # 배추흰지렁이 수 구하기
    # M : 열 , N : 행
    for a in range(N):
        for b in range(M):
            if graph[a][b] == 1:
                dfs(a, b)
                cnt += 1

    print(cnt)