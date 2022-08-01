import sys

n = int(sys.stdin.readline().rstrip())

x,y = 1,1 # 실제 x,y 좌표

plans = list(sys.stdin.readline().rstrip().split())

# L , R , U , D
dx = [0,0,-1,1]
dy = [-1,1,0,0]
move_types=['L','R','U','D']

for plan in plans:
    # 이동 후 좌표 구하기
    for i in range(len(move_types)):
        if plan == move_types[i]:
            nx = x + dx[i] # 움직임이 예상된 x,y 좌표
            ny = y + dy[i]

    if nx < 1 or ny < 1 or nx > n or ny > n:
        continue

    x, y = nx , ny

print(x,y)