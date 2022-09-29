# https://www.acmicpc.net/problem/1992

import sys


N = int(sys.stdin.readline().rstrip())

# 이중 배열 만들기
video = []

for _ in range(N):
    video.append(list(map(int, sys.stdin.readline().rstrip())))



# 재귀함수를 이용하여 쿼드트리를 만든다.
def quad_tree(x, y, n):
    
    # 가장 첫번째 숫자 저장
    init = video[x][y]

    # n이 1이라면, 즉 1x1 배열이라면
    if n == 1:
        # 해당 숫자를 반환
        return str(init)
    
    # n이 1이 아니라면, 즉 2x2 이상의 배열이라면
    else:
        for i in range(x, x+n):
            for j in range(y, y+n):

                if init == video[i][j]:
                    continue
                else:
                    # 4개의 쿼드트리를 만들어서 반환
                    return '(' + quad_tree(x, y, n//2) + quad_tree(x, y+n//2, n//2) + quad_tree(x+n//2, y, n//2) + quad_tree(x+n//2, y+n//2, n//2) + ')'

        # 만약 4개의 쿼드트리가 모두 같다면, 즉 4개의 쿼드트리가 모두 0이거나 모두 1이라면
        return str(init)

print(quad_tree(0,0,N))
