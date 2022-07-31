# https://www.acmicpc.net/problem/10816
# https://chancoding.tistory.com/45

import sys


_ = sys.stdin.readline()
N = sorted(map(int, sys.stdin.readline().split())) # 정렬 후 list 반환

_ = sys.stdin.readline()
M = list(map(int, sys.stdin.readline().split()))

def binary(n, N, start, end):
    m = (start+end)//2
    if n == N[m]:
        return N[start:end+1].count(n)
    elif n < N[m]:
        return binary(n, N, start, m-1)
    else:
        return binary(n, N, m+1, end)

# 리스트 N에 있는 요소들의 각각이 몇개가 있는지를 dictionary에 담는다.
n_dic = {}
for n in N:
    start = 0
    end = len(N) - 1
    if n not in n_dic: # 각각의 숫자에 도달할때마다 총 개수를 구하기 때문에 dic에 없는 경우에만 실행
        n_dic[n] = binary(n, N, start, end)

print(' '.join(str(n_dic[x]) if x in n_dic else '0' for x in M ))

