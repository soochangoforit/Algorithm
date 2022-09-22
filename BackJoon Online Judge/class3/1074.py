# https://www.acmicpc.net/problem/1074


import sys

def bfs(x,y,size):
    global count

    # 2x2 사이즈의 행렬이 될때까지 반복
    if size == 2:
        # 2x2 행렬의 각 요소를 확인
        for i in range(x,x+2):
            for j in range(y,y+2):
                # 찾는 요소를 찾았다면, count를 출력하고 종료
                if i == r and j == c:
                    print(count)
                    sys.exit(0)
                # 찾는 요소가 없었다면 count +1 추가
                count += 1
    else:
        # 2x2 사이즈의 행렬이 될때까지 반복
        # 위치에 따른 다른 사분면 선택
        # 초기 x,y는 0,0부터 시작
        # 찾고자 하는 좌표가 1사분면에 존재하는 경우
        if r < x + size // 2 and c < y + size // 2:
            bfs(x,y,size//2)
            
        # 찾고자 하는 좌표가 2사분면에 존재하는 경우
        elif r < x + size // 2 and c >= y + size // 2:
            count += (size // 2 * size // 2)
            bfs(x,y+size//2,size//2)
        
        # 찾고자 하는 좌표가 3사분면에 존재하는 경우
        elif r >= x + size // 2 and c < y + size // 2:
            count += size * size // 2
            bfs(x+size//2,y,size//2)

        # 찾고자 하는 좌표가 4사분면에 존재하는 경우
        else:
            count += size * size // 4 * 3
            bfs(x+size//2,y+size//2,size//2)
 

N, r, c = map(int, sys.stdin.readline().split())

count = 0

# 시작점은 0,0부터 크기는 2^N
bfs(0,0,2**N)
