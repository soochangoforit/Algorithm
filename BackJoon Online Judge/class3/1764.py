# https://www.acmicpc.net/problem/1764

import sys


N, M = map(int, sys.stdin.readline().split())   

n_set = set()
m_set = set()

for i in range(N):
    n_set.add(sys.stdin.readline().rstrip())

for k in range(M):
    m_set.add(sys.stdin.readline().rstrip())

answer = sorted(list(n_set & m_set))

print(len(answer))
for i in answer:
    print(i)