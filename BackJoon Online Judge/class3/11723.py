# https://www.acmicpc.net/problem/11723

import sys

m = int(sys.stdin.readline())
S = set()

for _ in range(m):
    temp = sys.stdin.readline().split()

    # command만 존재하는 경우
    if len(temp) == 1:
        if temp[0] == "all":
            S = set([i for i in range(1,21)])
        else:
            S = set() # 공집합으로 초기화
        continue

    #command와 target이 존재하는 경우
    command , target = temp[0] , temp[1]

    target = int(target)

    if command == "add":
        S.add(target)
    elif command == 'check':
        print(1 if target in S else 0)
    elif command == 'remove':
        S.discard(target)
    elif command == 'toggle':
        if target in S:
            S.discard(target)
        else:
            S.add(target)


