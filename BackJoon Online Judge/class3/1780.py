# https://www.acmicpc.net/problem/1780

from cmath import sqrt
import sys


N = int(sys.stdin.readline().rstrip())

board = [ list(map(int, input().split())) for _ in range(N) ]

result_minus = 0
result_zero = 0
result_plus = 0

def dfs(x,y,n):
    global result_minus, result_zero, result_plus

    num_check = board[x][y]
    # 모든 좌표 순차적으로 이동
    for i in range(x, x+n):
        for j in range(y, y+n):

            # 가장 우측 상단의 num_check랑 다른 숫자가 나오면
            if num_check != board[i][j]:
                for k in range(3):
                    for l in range(3):
                        dfs(x+k*n//3, y+l*n//3, n//3)
                return
    
    if num_check == -1:
        result_minus += 1
    elif num_check == 0:
        result_zero += 1
    else:
        result_plus += 1

dfs(0,0,N)
