# https://www.acmicpc.net/problem/2667

import sys


N = int(sys.stdin.readline().rstrip())

miro = []

for i in range(N):
    miro.append(list(map(int,sys.stdin.readline().rstrip())))

# 아래, 오른쪽, 위, 왼쪽
dy = (1,0,-1,0)
dx = (0,1,0,-1)

# 단지수를 저장한 변수
count = 0

# 단지별 집의 수를 저장할 리스트
result = []

def dfs(y,x):
    global count
    # 한번 방문했기 때문에 집의 수 +1 해준다.
    count += 1
    # 방문한 곳은 0으로 바꿔준다. 추가적인 방문처리하기 위한 메모리를 절약하기 위함
    miro[y][x] = 0
    # 4방향 탐색
    for i in range(4):
        next_y = y + dy[i]
        next_x = x + dx[i]
        # 범위를 벗어나지 않고, 집이 1로 존재해야 한다, 이미 방문한 곳에 대해서는 0으로 바꿨기 때문에 0이 아닌 곳을 방문한다.
        if 0<= next_y < N and 0<= next_x <N and miro[next_y][next_x] == 1:
            dfs(next_y,next_x)

for i in range(N):
    for j in range(N):
        if miro[i][j] == 1:
            dfs(i,j)
            result.append(count)
            # 단지별 집의 수 초기화
            count = 0

print(len(result))
for i in sorted(result):
    print(i)

