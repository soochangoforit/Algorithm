# https://www.acmicpc.net/problem/9375

import sys


count = int(sys.stdin.readline().rstrip())

for _ in range(count):
    n = int(sys.stdin.readline().rstrip())
    clothes = {}
    for _ in range(n):
        name, kind = sys.stdin.readline().rstrip().split()
        if kind in clothes:
            clothes[kind] += 1
        else:
            clothes[kind] = 1
    
    # 핵심 로직 : 각 옷 종류별로 안입는 경우의 수 +1을 해주고, 모두 안입는 경우의 수를 빼준다.
    answer = 1
    for kind in clothes:
        answer *= clothes[kind] + 1
    print(answer - 1)




    
    