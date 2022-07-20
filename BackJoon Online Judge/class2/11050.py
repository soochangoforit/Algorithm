# https://www.acmicpc.net/problem/11050

# a,b = map(int , input().split())

# son = 1
# parent = 1
# for i in range(b):
#     son *= a-i
#     parent *= i + 1

# print(int(son/parent))


# n과 k가 주어졌을때 nCk 즉, n! / k!(n - k)!를 해주면 된다.
from math import factorial

n, k = map(int, input().split())
b = factorial(n) // (factorial(k) * factorial(n - k))
print(b)