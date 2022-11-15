# https://www.acmicpc.net/problem/9019, problem solved with pypy3


from collections import deque
import sys


T = int(sys.stdin.readline())

def d_func(n):
    value = 2 * n
    if value > 9999:
        value = value % 10000
    return value

def s_func(n):
    value = n - 1
    if value < 0:
        value = 9999
    return value

# 왼쪽 쉬프트
def l_func(n):
    value = (n % 1000) * 10 + n // 1000
    return value

# 오른쪽 쉬프트
def r_func(n):
    value = (n % 10) * 1000 + n // 10
    return value

def bfs(start, end):
    queue = deque()

    # 방문처리를 위한 배열
    visited = [False] * 10000
    

    # 첫 start를 방문처리
    visited[start] = True

    # 방문할때마다, 그전에 어떤 명령어로 방문했는지 튜플 형태로 값을 저장
    queue.append((start, ''))

    while queue:
        value, command = queue.popleft()

        if value == end:
            return command

        d_value = d_func(value)
        # 이미 방문했다는 의미는 해당 숫작까지 도달하는데 나랑 똑같은 속도가 탐색하고 있는 친구가 있거나
        # 혹은 최종 목표까지 도달하는데 나보다 빠른 속도로 탐색하고 있는 친구가 있다는 의미.
        # 따라서 이미 방문한 값은 탐색하지 않는다.
        if not visited[d_value]:
            visited[d_value] = True
            queue.append((d_value, command + 'D'))

        s_value = s_func(value)
        if not visited[s_value]:
            visited[s_value] = True
            queue.append((s_value, command + 'S'))

        l_value = l_func(value)
        if not visited[l_value]:
            visited[l_value] = True
            queue.append((l_value, command + 'L'))

        r_value = r_func(value)
        if not visited[r_value]:
            visited[r_value] = True
            queue.append((r_value, command + 'R'))

for _ in range(T):
    start, end = map(int, sys.stdin.readline().split())
    print(bfs(start, end))