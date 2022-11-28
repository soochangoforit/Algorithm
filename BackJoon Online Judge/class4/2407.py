# https://www.acmicpc.net/problem/2407

import sys


def fac(n):
    num = 1
    for i in range(2,n+1):
        num *= i

    return num

n,m = map(int,sys.stdin.readline().split())

print(fac(n)//(fac(m)*fac(n-m)))


