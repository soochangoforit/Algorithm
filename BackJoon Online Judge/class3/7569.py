# https://www.acmicpc.net/problem/7569

from collections import deque
import sys
from types import DynamicClassAttribute


M, N, H = map(int, sys.stdin.readline().rstrip().split())

tomatos = [[list(map(int, sys.stdin.readline().rstrip().split())) for _ in range(N) ] for _ in range(H)]

# 이동 가능한 6방향 : 2차원에서 아래, 오른쪽, 위쪽, 좌측, 3차원에서 위쪽, 아래쪽 
dy = (1,0,-1,0,0,0)
dx = (0,1,0,-1,0,0)
dz = (0,0,0,0,1,-1)

# 익지 않은 토마도
raw = 0

# 익은 토마토 개수
riped = 0


# 이미 익은 토마토 개수 파악
for h in range(H):
    for n in range(N):
        raw += tomatos[h][n].count(0)


# 익은 사과에서 공시 다발적으로 계산할 필요 있다.
# 익은 사과를 하나의 자식 node로 보고 bfs 탐색해야 한다.
def bfs(tomatos):
    global riped

    Q = deque()

    # 소요 시간
    day = 0

    # 익은 토마토의 좌표를 초기 Q에 넣어줘야 한다.
    for h in range(H):
        for n in range(N):
            for m in range(M):
                # [3차원,2차원(dy),2차원(dx)]
                if tomatos[h][n][m] == 1:
                    Q.append((h, n, m, day))

    # 익은 좌표에 대해서 동시적으로 BFS 접근
    while Q:
        h, n, m, day = Q.popleft()

        # 인근 토마토 탐색
        for i in range(6):
            next_h = h + dz[i]
            next_n = n + dx[i]
            next_m = m + dy[i]

            # 범위 안에 속하는지 확인
            if 0 <= next_m < M and 0 <= next_n < N and 0 <= next_h < H :
                # 이미 토마토가 없거나 (-1) 혹은 이미 익은 경우 (1)에 대해서는 탐색X
                if tomatos[next_h][next_n][next_m] == 0:
                    riped += 1
                    # 방문처리를 하기 위해 추가적인 visisted를 만들지 않고 기존의 배열 그대로 사용
                    tomatos[next_h][next_n][next_m] = 1
                    # 소요한 날짜 day에 대해서는 6번의 반복 for안에서 순환하기 때문에
                    # 초기 각각의 익은 토마토 위치에서 day를 공유할수 있다.
                    # 자식 노드로 탐색을 같이 시작할때 day +1가 이루어진다.
                    Q.append((next_h, next_n, next_m, day + 1))
    return day







# 처음부터 모드 익은 경우
if raw == 0:
    print(0)
else:
    # 익은 개수, 소요 시간
    day = bfs(tomatos)
    # 기한 안에 익은 개수 == 초기 익지 않는 토마토 개수
    if riped == raw:
        print(day)
    # 기한이 지나서 다 익지 못한 경우
    else:
        print(-1)
