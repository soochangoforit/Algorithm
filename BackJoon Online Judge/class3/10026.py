# https://www.acmicpc.net/problem/10026

from collections import deque
import sys


N = int(sys.stdin.readline().rstrip()) 

# 문자를 하나씩 입력 받아서 2중 배열로 만든다.
colors = [ list(map(str, sys.stdin.readline().rstrip())) for i in range(N) ]

# 아래, 오른쪽, 위쪽, 좌측
dy = (-1, 0, 1, 0)
dx = (0, 1, 0, -1)

# 방문 표시를 하기 위해 사용, 만약 데이터가 0과 1로만 구성되었다면 원본 배열에서 바로 방문 처리 가능
visited = [ [0]*N for _ in range(N) ]

result = []

# 색깔 구분의 개수
count = 0

def bfs(y,x):
    global count
    
    # 원본 데이터가 0과 1의 조합이 아니기 때문에, 중복 방문을 체크하기 위한 방문 표시 배열을 사용
    visited[y][x] = 1
    
    # 초기 색깔
    color = colors[y][x]
    
    Q = deque()
    Q.append((y,x))

    while Q:
        y,x = Q.popleft()

        for k in range(4):
            new_y = y + dy[k]
            new_x = x + dx[k]

            # 범위를 벗어나지 않고, 아직 방문하지 않은 노드만 방문, 그리고 기준이 되는 색깔과 동일한 색깔이라면
            if 0 <= new_y < N and 0 <= new_x < N and not visited[new_y][new_x] and colors[new_y][new_x] == color:
                # 방문 처리후 Q에 삽입 , 중복된 탐색을 피하기 위해서
                visited[new_y][new_x] = 1
                Q.append( (new_y, new_x) )

    

# 일반인
for i in range(N):
    for j in range(N):
        # 방문하지 않는 경우에만 방문
        if visited[i][j] == 0:
            bfs(i,j)
            # 색깔 구분의 개수, 해당 if문이 끝나면 그 색깔에 대한 탐색은 끝났다.
            count += 1

print(count, end= ' ')

# 색약자 탐색을 위해서 R를 G로 변경
for i in range(N):
    for j in range(N):
        if colors[i][j] == 'R':
            colors[i][j] = 'G'

# 방문 노드 초기화
visited = [ [0]*N for _ in range(N) ]

# 색깔 구분의 개수 초기화
count = 0

# 색약자를 위한 bfs 수행
for i in range(N):
    for j in range(N):
        if visited[i][j] == 0:
            bfs(i,j)
            count += 1

print(count)