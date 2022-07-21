# https://www.acmicpc.net/problem/2751

import sys


case = sys.stdin.readline().strip()

num_list = [ int(sys.stdin.readline().strip()) for _ in range(int(case))] # 문자열 리스트

num_list.sort()

for i in num_list:
    print(i)