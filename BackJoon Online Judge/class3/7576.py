# https://www.acmicpc.net/problem/7576

from collections import deque
import sys


M, N = map(int, sys.stdin.readline().rstrip().split())


# 아래, 오른쪽, 위쪽, 왼쪽 이동 가능
dy = (1,0,-1,0)
dx = (0,1,0,-1)

# 상자에 담긴 토마토
tomatos = [ list(map(int, sys.stdin.readline().rstrip().split())) for _ in range(N) ]

# 익지 않은 토마토 개수
raw = 0

# 익지 않은 토마토의 개수 파악
for row in tomatos:
    raw += row.count(0)


# bfs의 동작 방식을 잘 생각하면 초기 1이 위치했던 좌표에서 부터 1일 정도 인접한 토마토에 대해 방문처리 및 익음처리만 이루어지고
# 1일 동안 익은 자식(인접)인 토마토가 모두 Q에 들어가 있어, day가 각각의 초기 익은 좌표에서 동일하게 증가됨을 확인할 수 있다.
def bfs(tomatos):
    Q = deque()

    # 시간
    day = 0

    # 각 순회를 하면서 익은 토마토로 변경된 개수
    riped = 0

    # bfs 를 위해 상자에 존재하는 초기 익은 토마토의 좌표와 초기 일수 정보 추가
    for y in range(N):
        for x in range(M):
            if tomatos[y][x] == 1:
                Q.append((y,x, day))
    
    # 방문처리는 visited라는 리스트를 따로 만들지 않고 원래 tomatos 배열을 보고 판단한다.

    # 초기 익은 위치에서 1일 동안 익은 토마토에 대해 접근할때 같은 day를 갖고 접근한다.
    while Q:
        current_y, current_x, day = Q.popleft()

        # 4 방향으로 순회
        for i in range(4):
            next_y = current_y + dy[i]
            next_x = current_x + dx[i]

            # 범위값을 넘어가지 않으면서도 토마토가 익지 않은 경우에만 탐색, 이미 익어버린 경우에 대해서는 탐색하지 않는다.
            if 0 <= next_y < N and 0 <= next_x < M and tomatos[next_y][next_x]==0:
                # 다음 좌표에 대해서 토마토 익음으로 처리
                tomatos[next_y][next_x] = 1
                # 익은 개수 추가
                riped += 1
        
                # 큐에 추가하는데, 다음 좌표의 day 는 기존 날짜 보다 +1 추가
                # 지금 day +1 를 해주는 이유는 4방향에 대해서 접근할때는 모두 인접해서 하루만에 처리할 수 있다.
                # 모든 4방향에 대해서 다 탐색을 하고 나서 다시 While Q로 들어갈때, day에 대해서 +1 추가
                Q.append((next_y, next_x, day+1))

    return riped, day
    

# 초기 토마토가 모두 다 익은 경우라면, 0일 소요
if raw == 0:
    print(0)
else:
    riped, day = bfs(tomatos)
    # (초기 익지 않은 토마토 수) - (시간이 지나 익은 토마토 수) 가 0 이면
    if (raw-riped) == 0:
        print(day)
    else:
        print(-1)