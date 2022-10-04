# https://www.acmicpc.net/problem/2667

from collections import deque
import sys

N = int(sys.stdin.readline().rstrip())

miro = []

for i in range(N):
    miro.append(list(map(int, sys.stdin.readline().rstrip())))

# 아래, 오른쪽, 위, 왼쪽
dy = (1,0,-1,0)
dx = (0,1,0,-1)

count = 0

result = []

def bfs(y,x):
    global count 
    Q = deque()
    # 방문했다는 의미로 0 할당
    miro[y][x] = 0
    Q.append((y,x))

    while Q:
        y,x = Q.popleft()

        for i in range(4):
            next_y = y + dy[i]
            next_x = x + dx[i]

            if 0 <= next_y < N and 0 <= next_x < N and miro[next_y][next_x] == 1:
                # deque에 넣을 원소에 대해서 미리 방문 처리를 해준다.
                # Q에 같은 노드가 중복으로 들어가는것을 방지하기 위해서 곧 방문할 노드에 대해서 미리 방문처리를 해준다. - 중요
                miro[next_y][next_x] = 0
                Q.append((next_y, next_x))
                count += 1



for i in range(N):
    for j in range(N):
        # 1일 경우에만 BFS를 실행한다.
        if miro[i][j] == 1:
            count += 1
            bfs(i,j)
            result.append(count)
            # 한번의 BFS가 끝나면 count를 초기화 해준다.
            # 다음으로 나오는 단지에 대한 집의 수를 구하기 위해서
            count = 0

print(len(result))
for i in sorted(result):
    print(i)
