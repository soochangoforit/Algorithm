# https://www.acmicpc.net/problem/11650

import sys

case = int(sys.stdin.readline().strip())

xy_list=[ tuple(map(int, sys.stdin.readline().rstrip().split(" "))) for _ in range(case)]

xy_list.sort(key = lambda x: (int(x[0]) , int(x[1])))

for i in xy_list:
    print(i[0],i[1])