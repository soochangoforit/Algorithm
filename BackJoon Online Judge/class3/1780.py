# https://www.acmicpc.net/problem/1780

from cmath import sqrt
import sys


N = int(sys.stdin.readline().rstrip())

board = [ list(map(int, input().split())) for _ in range(N) ]

result_minus = 0
result_zero = 0
result_plus = 0

# 탐색 시작할 x,y 좌표 및 탐색 범위의 크기
def dfs(x,y,n):
    global result_minus, result_zero, result_plus

    # 우측 상단 좌표 저장
    num_check = board[x][y]

    # 모든 좌표 순차적으로 이동
    for i in range(x, x+n):
        for j in range(y, y+n):

            # 가장 우측 상단의 num_check랑 현재 움직이고 있는 좌표의 숫자가 다른 숫자가 나오면
            if num_check != board[i][j]:

                # 한번이라도 다른 숫자가 나오면, 9개의 작은 사각형으로 나누어서 다시 탐색
                for k in range(3):
                    for l in range(3):
                        # x,y는 초기에 0좌표로 들어온다. k와 l도 0부터 시작
                        dfs(x + k*n//3, y + l*n//3, n//3)
                return
    
    # 상단 if문에서 제한된 영역안에서 모든 숫자가 같은 경우
    if num_check == -1:
        result_minus += 1
    elif num_check == 0:
        result_zero += 1
    else:
        result_plus += 1

    # 마치 dfs처럼 동작한다. 하나의 영역에 대해서 같은 숫자의 사각형이 나올때까지 계속 탐색
    # 탐색이 끝나면 다름 구역 3*3의 영역에서 탐색을 시작한다.

dfs(0,0,N)
print(f'{result_minus}\n{result_zero}\n{result_plus}')