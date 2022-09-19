# https://www.acmicpc.net/problem/2630

import sys


N = int(sys.stdin.readline().rstrip())

# 종이 초기화
paper = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]

white = 0
blue = 0

# 재귀 함수를 통해서 탐색
def dfs(x,y,n):
    global white, blue

    # 초기 숫자
    init = paper[x][y]

    for i in range(x, x+n):
        for j in range(y, y+n):
            # 초기 숫자와 같은지 검사
            if paper[i][j] != init:
              
                # # 한번 나눌때 2등분해서 나누기 때문에
                for k in range(2):
                    for l in range(2):
                        dfs(x + k*n//2, y + l*n//2, n//2)
                # 재귀 함수 호출이 끝나고 나서는 중복 계산 방지를 위한 return 재귀 함수에서 return은 가장 중요
                return

    if init == 0:
        white += 1
    else:
        blue += 1
    

dfs(0,0,N)
print(f'{white}\n{blue}')