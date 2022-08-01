# https://www.acmicpc.net/problem/1929
import math
import sys

first , last = map(int, sys.stdin.readline().split())

def check(n):
    if n == 1:
        return False
    if n == 2:
        return True
    for i in range(2, int(math.sqrt(n)) + 1 ): # 소수인 경우 , 제곱근에 가까운 숫자 까지만 확인을 한다.
        if n%i == 0:
            return False # 소수 아님
    return True # 소수 확인

for i in range(first, last +1):
    if check(i):
        print(i)
    else:
        continue
