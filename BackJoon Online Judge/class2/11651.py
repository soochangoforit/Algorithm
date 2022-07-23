# https://www.acmicpc.net/problem/11651

import sys

case = int(sys.stdin.readline().strip())

xy_list=[ tuple(map(int, sys.stdin.readline().rstrip().split(" "))) for _ in range(case)]

xy_list.sort(key = lambda x: (int(x[1]) , int(x[0])))

for i in xy_list:
    print(i[0],i[1])