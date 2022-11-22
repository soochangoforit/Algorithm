import sys
from collections import deque

input = sys.stdin.readline

# 먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
# 먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.

N = int(input())

# 먹이가 존재하는 배열 (바다)
sea = []
for _ in range(N):
    sea.append(list(map(int,input().split())))


# 이동 방향
dx = [0,0,1,-1]
dy = [1,-1,0,0]

# 상어의 크기
shark_size = 2

# 최단 거리를 위한 값
INF = 1e9


# 아기 상어의 현재 좌표
now_y, now_x = 0,0

# 상어의 위치를 찾기
for i in range(N):
    for j in range(N):
        if sea[i][j] == 9:
            # 상어의 초기 위치 초기화
            now_y, now_x = i,j
            # 배열에서 초기 위치를 먹이가 없다는 의미인 0으로 초기화
            sea[i][j] = 0


# 현재 위치에서 각 물고기까지의 거리를 반환, 지나가는 경로마다 값을 저장
def BFS():
    # 튜플 형식으로 데이터 저장
    Q = deque([(now_y,now_x)])

    # 방문 여부
    # -1은 방문전, 0은 방문 후, 그 외 숫자들은 해당 좌표까지 도달하기 위해서 거친 거리
    visited = [[-1]*N for _ in range(N)]
    visited[now_y][now_x] = 0

    while Q:
        y,x = Q.popleft()

        # 움직일 수 있는 방향으로 이동
        for i in range(4):
            next_y, next_x = y + dy[i] , x + dx[i]

            # 범위를 벗어나지 않는지 판단
            if 0 <= next_y < N and 0 <= next_x < N:
                # 상어가 이동 가능한지 판단, 자신보다 같거나 작으면 이동 가능 그리고 방문하지 않아야 한다.
                # 크기가 같은 경우에 대해서는 먹을 순 없지만, 이동 함으로써 주위의 먹이를 먹을 가능성이 존재해서 "=" 등호를 포함해서 조건부여
                if shark_size >= sea[next_y][next_x] and visited[next_y][next_x] == -1:
                    # 방문이 가능하면, 방문처리를 해준다.
                    # 방문 처리를 할때는 , 이전에 방문을 했던 값을 그대로 가져와서 1를 더해줘야 한다. 최종 목적지까지 도달하는데 걸린 최소 거리를 구할 수 있기 때문에
                    visited[next_y][next_x] = visited[y][x] + 1
                    Q.append((next_y, next_x))
    
    # 아기 물고기가 그 어떤 물고기도 먹지 않은 상태에서(size가 2인 상황), 초기 위치인 9가 있는 좌표에서 이동 가능한 좌표에 대해서 최단 거리를 반환한다.
    # visited에서는 자신보다 크기가 작아서 먹을 수 있는 크기의 물고기와 크기가 같아서 먹지 못한 물고기에 대한 데이터가 들어있다.
    # 그것들에 데이터는 해당 좌표까지 가기 위해 거쳐야 하는 거리와 같다.
    return visited


# visited에서 먹을 수 있는 물고기에 대해서는 이동하면서 먹고 크기를 키워야 한다.
def eatFish(visited):
    y,x = 0,0
    # 최단 거리이지만, 초기에는 최대한 큰 값을 할당해준다.
    min_distance = INF

    # 이동 가능하며, 먹을 수 있는 1마리 물고기에 대한 좌표와 도달하기 위한 거리값을 반환한다.
    # 문제에서 가장 가까운 거리가 여러개이면 위에 있는게 우선 순위가 높고 다음으로 좌측이다.
    # 이 말은 흔히 2중 배열을 일반적으로 방법으로 순환했을때와 같다.
    # min_distance 같은 값이 for문을 통해 여러번 나오더라도 부등호가 "<" 이기 때문에 가까운 거리가 같은게 여러개 있더라도, 위에 있는게 우선순위가 높다.
    # 위에 있는게 여러개라 하더라도, 2중 배열의 일반적인 순환으로 인해서 가장 좌측에 있는 것이 우선순위가 높게 적용 된다. 
    for i in range(N):
        for j in range(N):
            # visited가 아직 -1이 아니라는 의미는 이동이 가능한 좌표이다. + 아기 상어가 자신보다 크기가 작아야지만 먹을 수 있다.
            if visited[i][j] != -1 and 1 <= sea[i][j] < shark_size:
                if visited[i][j] < min_distance:
                    # 방문 가능하며, 먹을 수 있는 물고기 중에서 최소 거리
                    min_distance = visited[i][j]
                    y, x = i,j
    
    # 방문은 모두 가능했지만, 먹을 물고기는 없었던 경우
    if min_distance == INF:
        return False
    else:
        # 지금 단계에서 최소거리에 있는 물고기 거리를 반환하긴 하지만, 위측, 좌측에 있는 물고기인지는 판단하지 못 한다.
        return y,x,min_distance


# 이동한 거리
answer = 0
# 먹은 개수,, 곧 물고기의 size와 비교하여 size의 값을 증가시키는데 역할을 한다.
food = 0

# 
while True:
    result = eatFish(BFS())

    # 첫 좌표에서 부터 방문하기 시작하는데, 먹을 물고기가 하나도 없는 경우
    if not result:
        print(answer)
        break

    else:
        # 최단 거리로 생각되는 y,x 좌표 꺼낸다.
        now_y, now_x = result[0] , result[1]
        # 이동한 거리
        answer += result[2]
        # 물고기를 먹었음으로, 0으로 초기화
        sea[now_y][now_x] = 0
        # 물고기를 먹은 횟수 증가
        food += 1

    # 물고기를 먹은 횟수 >= 아기 상어 크기
    # 아기 상어의 크기가 1 증가한다.
    if food >= shark_size:
        # 아기 상어의 크기가 1 증가한다.
        shark_size +=1
        # 새롭게 증가된 몸무게에 대해서 새로운 먹이 횟수를 초기화 해줘야 한다.
        food = 0


