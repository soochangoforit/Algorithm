# https://www.acmicpc.net/problem/2178

from collections import deque
import sys


N, M = map(int, sys.stdin.readline().rstrip().split())

miro = []

# 문자가 아닌 숫자로 받고자 한다. list(map(int, sys)) [[1, 0, 1, 1, 1, 1], [1, 0, 1, 0, 1, 0], [1, 0, 1, 0, 1, 1], [1, 1, 1, 0, 1, 1]]
# 문자로 받는 경우 list(sys) [['1', '0', '1', '1', '1', '1'], ['1', '0', '1', '0', '1', '0'], ['1', '0', '1', '0', '1', '1'], ['1', '1', '1', '0', '1', '1']]
# miro = [input() for _ in range(N)] ['101111', '101010', '101011', '111011']
for _ in range(N):
    miro.append(list(map(int, sys.stdin.readline().rstrip())))

# 방문한 노드는 1로 구성
visited = [ [0]*M for i in range(N)]


# 이동가능한 범위
# 밑, 오른쪽, 위, 왼쪽
dy = (1,0,-1,0)
dx = (0,1,0,-1)

# 영역 안에 존재하고, 1로 움직일 수 있는지 확인
def is_valid(next_y, next_x):
    # 문제에서는 N, M이 각각 1,1 부터 시작한다. 따라서 0 <= next_y <= N-1 도 가능
    return 0 <= next_y < N and 0 <= next_x < M and miro[next_y][next_x] == 1




def bfs(start_y,start_x):

    Q = deque()
    # 첫 방문
    cnt = 1  
    # 첫 노드를 방문했으니, 1로 변경   
    visited[0][0] = 1
    # 방문한 좌표를 deque에 넣는다.
    # 최소한의 이동 횟수를 구하기 위해 deque에 각 노드마다 이동했던 경로에 따른 방문 횟수 cnt를 함께 넣는다.
    # cnt를 공용 지역 변수로 활용하면, BFS의 움직임에 따라 cnt가 계속 증가하므로, 최종 좌표까지의 최적의 지나온 횟수를 구할 수 없다.
    # 그래서 좌표 이동할때 마다, 해당 좌표까지 오는데 거친, 최적의 cnt를 갖고 좌표를 노드마다 따로 따로 움직여야 한다.
    Q.append((start_y,start_x,cnt))

    while Q:
        y,x,cnt = Q.popleft()
        # 첫 노드가 만약 N,M이라면 (2,2)이라면, 문제에서는 index 1부터 시작 ----- 필요 없을거 같은데???
        # 첫 노드에서는 걸리진 않겠지만 Q에 append 되면서 최종좌표에 도달 했을때 cnt를 반환하려면 필요하다.
        if y == N-1 and x == M-1:
            return cnt
        # 갈 수 있는 4가지의 방향대로 순회를 해야한다.
        for k in range(4):
            next_y = y + dy[k]
            next_x = x + dx[k]

            # 여기서 곧 이동할 좌표 next_y 와 next_x가 영역을 넘어가진 않았는지, 0은 아닌지 확인해야 한다. 또한 이미 방문한 좌표는 아닌지 확인해야 한다.
            # 문제에는 정말 최적의 경로만 지나치는 정답이 이미 있다. 길을 가다가 막혀서 다시 돌아오는 경우는 애초에 최적의 경로가 아니므로, 방문한 좌표는 다시 방문하지 않는다.
            # 또한, 최종 좌표로 가는 갈림길마다 새로운 경우의 수가 나오고 새롭게 여러 경로가 나타나게 된다. 그래서 결과 한번 방문한 좌표를 다시 되돌아 가서 최적의 경우를 찾아갈 수 있다는 희망은 존재하지 않는다.
            if is_valid(next_y, next_x) and visited[next_y][next_x] == 0:
                # 이동도 가능하고, 이미 지나오지 않았더라면 다음 좌표로 이동
                visited[next_y][next_x] = 1
                # 이동할 좌표까지 오는데 거쳐온 경로의 수를 cnt에 1을 더해준다.
                Q.append((next_y,next_x,cnt +1))

        
print(bfs(0,0))