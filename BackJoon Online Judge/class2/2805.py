# https://www.acmicpc.net/problem/2805

import sys

H, M = map(int, sys.stdin.readline().split())

trees = list(map(int,sys.stdin.readline().split()))

# Min을 1로 하지 않고 일반 Min(trees)로 하면 나무의 개수가 1개일때 더 이상 진행할 수 없다.
Min = 1
Max = max(trees)

# 정렬을 따로 하지않는 이유는 우리는 주어진 데이터에서만 이분탐색을 하는게 아니다.
# 주어진 데이터를 이분 탐색 양끝의 범위라고 생각하고 mid를 찾아야 한다. 나무를 자라는 높이는
# 주어진 데이터에서만 찾는게 아니다.

while Min <= Max:
    total = 0
    Mid = (Min + Max)//2
    
    for tree in trees:
        temp = tree - Mid
        
        if temp > 0:
            total += temp

    if total == M:
        print(Mid)
        break

    # 예상보다 너무 짧게 나온경우
    if total < M:
        Max = Mid -1
    else:
        Min = Mid +1

else:
    print(Max)


    


    






