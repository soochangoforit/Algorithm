# https://www.acmicpc.net/problem/17219

import sys


N , M = map(int, sys.stdin.readline().split())
site = {}

for i in range(N):
    site_name, site_pw = sys.stdin.readline().split()
    site[site_name] = site_pw

for i in range(M):
    print(site[sys.stdin.readline().strip()])

