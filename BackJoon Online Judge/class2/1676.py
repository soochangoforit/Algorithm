# https://www.acmicpc.net/problem/1676

# factorial
import sys

def factorial(n):
    if n == 0:
        return 1
    else:
        return n * factorial(n-1)
    
num = int(sys.stdin.readline().rstrip())
result = [ x for x in str(factorial(num))]

zero_count = 0
for i in result[::-1]:
    if i == '0':
        zero_count += 1
    else:
        break
print(zero_count)

