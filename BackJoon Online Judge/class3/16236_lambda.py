# https://www.acmicpc.net/problem/16236


from collections import deque
import sys


input = sys.stdin.readline

n = int(input())
sea = []
for _ in range(n):
    sea.append(list(map(int,input().split())))


dx = [0,0,-1,1]
dy = [1,-1,0,0]

y,x,size = 0,0,2

# 상어 위치 설정
for i in range(n):
    for j in range(n):
        if sea[i][j] == 9:
            y = i
            x = j

#  아기 상어가 방문 가능하며, 먹을 수 있는 물고기 좌표의 배열을 우선 순위에 맞게 반환한다.
def canBite(y,x,size):
    # 방문하지 않으면, -1
    # 방문하면, 0 혹은 해당 좌표를 방문하기 위해 거친 거리 값
    visited = [[-1]*n for _ in range(n)]

    Q = deque()
    Q.append((y,x))
    # 초기 아기 상어 좌표를 0으로 방문처리, 이동한 거리 없음으로 0
    visited[y][x] = 0

    sea[y][x] = 0

    canEat = []

    while Q:
        y,x = Q.popleft()

        # 이동 가능한 범위
        for i in range(4):
            next_y = y + dy[i]
            next_x = x + dx[i]

            # 범위를 넘어서진 않는지 검사 및 이미 방문한 좌표인지 판단
            if 0 <= next_y < n and 0<= next_x <n and visited[next_y][next_x] == -1:
                # 방문 가능한 조건, 0인 값은 물고기는 없지만 이동은 가능하다 (단, 아직은 먹을 수 있는 좌표인지는 판단 X)
                if sea[next_y][next_x] <= size:
                    # 방문이 가능한 좌표는 현재 좌표까지 온 거리에서 +1를 해준다.
                    visited[next_y][next_x] = visited[y][x] + 1
                    # 방문이 가능하기 때문에 다음 좌표 이동을 위해 Q에 넣어준다.
                    Q.append((next_y,next_x))
                    # 방문도 가능하지만, 먹을 수도 있는 좌표인 경우 ( 먹을 수 있는 좌표의 크기는 size보다 작아야 한다.) 
                    # 또한 해당 좌표가 0처럼 아무것도 없으면 이동은 가능했지만, 먹을 수 있는 좌표는 아니다.
                    if sea[next_y][next_x] < size and sea[next_y][next_x] != 0:
                        # 먹을 수 있는 좌표 및 이동한 거리 값을 튜플 형태로 넣어준다.
                        canEat.append((next_y,next_x,visited[next_y][next_x]))
    
    # 가장 가까이 있는 좌표가 우선 순위가 높고
    # 그러한 값이 많으면 제일 위에 있는 값이 높고
    # 그러한 값이 많으면 가장 좌측에 있는 값이 우선순위가 높다.
    return sorted(canEat, key = lambda x: (x[2],x[0],x[1]))

# 물고기를 먹은 횟수
cnt = 0
# 아기 상어가 먹을 수 있는 모든 물고기를 먹었을때, 이동한 거리
result = 0

while True:
    sharkCanEat = canBite(y,x,size)

    # 아기 상어가 먹을 수 있는 물고기가 없는 경우, 엄마 상어에게 도움을 요청한다.
    if len(sharkCanEat) == 0:
        break
    
    # 먹을 수 있는 좌표중, 우선 순위가 높은 좌표를 반환, 곧 아기 상어가 먹기 위해 이동할 위치
    ny, nx, distance = sharkCanEat.pop(0)

    # 움직인 값이 곧 최종 result의 결과가 된다
    result += distance

    # 우선 순위가 가장 높은 좌표로 아기 상어가 이동
    y,x = ny,nx

    # 먹고 나서 먹은 위치를 0으로 초기화 해야하지만, while문을 통해서 canBite로 들어가면 알아서
    # 시작한 좌표에 대해 초기화를 시켜준다.
    # 따라서, 여기에 굳이 초기화 좌표가 또 들어가는게 의미가 없다.
    sea[y][x] = 0

    # 먹고 나서는 추후 아기 상어의 크기를 증가를 위한 여부를 확인하기 위해 cnt 값을 +1 증가
    cnt += 1

    # 만약 cnt(물고기를 먹은 횟수)가 현재 아기 상어의 size와 같다면, size +1 증가
    if cnt == size:
        size += 1
        # 상어의 size가 증가했고, 다음 size 증가 여부를 확인하기 위해 cnt 값은 0으로 초기화
        cnt = 0

print(result)




