# https://www.acmicpc.net/problem/16928

from collections import deque
import sys

# 사다리, 뱀의 개수를 입력 받는다.
bridge, snake = map(int, sys.stdin.readline().split())

# 0 ~ 100 숫자를 담는 배열 (총 101개)
# 1번째 index는 1값을 반환한다.
graph = [ i for i in range(101) ]

for _ in range(bridge+snake):
    x, y = map(int, sys.stdin.readline().split())
    graph[x] = y

# 각 노드를 방문했는지 체크한다.
# 추가적으로, 각 노드를 방문하면서 사용했던 주사위 갯수를 넣어준다. 주사위 갯수 값이 있으면 이미 방문한 위치라는 것을 판단 가능하다.
visited = [0] * 101

def bfs(start):
    Q = deque()
    Q.append(start)
    # start 위치는 주사위를 사용하지 않더라도 방문 가능하다. 1번 칸과 100번 칸은 뱀과 사다리의 시작 또는 끝이 아니다.
    visited[start] = 0

    while Q:
        target = Q.popleft()

        # 주사위 눈(1~6)만큼 이동 가능하다.
        for i in range(1, 7):
            next_dice_target = target + i

            # 100칸을 넘어가면 무시한다.
            if next_dice_target > 100:
                continue

            # 해당 graph에는 사다리 또는 뱀의 끝 위치가 저장되어 있다.
            # 또한 사다리 또는 뱀의 끝 위치가 아닌 경우는 (target + i) 위치를 graph에서 그대로 뽑아낸다.
            # 다시 말해, 사다리 또는 뱀의 끝 위치가 아닌 경우는 -> 기존의 target에서 주사위 눈만큼 이동한 위치(숫자)를 graph에서 그대로 뽑아낸다.
            next_board_target = graph[next_dice_target]

            # 주사위를 통해 나온 값을 바로 Q에 넣기 전에, 방문 검사를 한다.
            # 접근하지 않았던 경우, 미리 방문 처리를 해줌으로써 (방문 예정으로 표시한다는 개념 - BFS의 특징)
            # 이미 방문 예정인 노드에 대해서는 자신의 경우보다 더 빠른 경로가 있다는 의미이므로 접근하지 않는다.
            # 따라서 주사위를 사용해서 한번도 방문하지 않았던 경우에만 방문한다. (자신보다 더 빠르게 접근한 경로가 없는 경우 방문한다는 의미)
            if visited[next_board_target] == 0:
                # next_board_target이 방문 가능하다면, 그 전에 방문했던 주사위 개수에서 1개를 더 해준다. (주사위를 기존 target보다 한번 더 사용했으므로)
                visited[next_board_target] = visited[target] + 1
                # BFS의 특징상, 위의 코드처럼 방문 예정인 노드를 다시 Q에 넣어준다.
                Q.append(next_board_target)


# 보드판 1부터 시작
bfs(1)

# 100까지 도달하기 위해 사용한 주사위 개수를 출력한다.
print(visited[100])