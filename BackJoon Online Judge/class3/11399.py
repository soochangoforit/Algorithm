# https://www.acmicpc.net/problem/11399

import sys


N = int(sys.stdin.readline())
time = list(map(int, sys.stdin.readline().split()))
sorted_time = sorted(time)

sum_list = []
for i in range(N):
    sum_list.append(sum(sorted_time[:i+1]))

print(sum(sum_list))
