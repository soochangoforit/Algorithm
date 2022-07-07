H,M,S = map(int, input().split())
second = int(input())

# 1시간 3600초
# 1분 60초

S += second
M += S // 60
H += M // 60

S%=60
M%=60
H%=24
print(H,M,S)
