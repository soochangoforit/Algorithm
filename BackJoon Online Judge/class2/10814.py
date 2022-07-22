# https://www.acmicpc.net/problem/10814

import sys

case = int(sys.stdin.readline().rstrip())

list = [ tuple(sys.stdin.readline().rstrip().split(" ")) for _ in range(case)]


list.sort(key = lambda x : int(x[0]))

for i,k in list:
    print(i, k)