
from curses.ascii import isalpha
import sys


S = [x for x in sys.stdin.readline().rstrip()]

value= 0
alpha = []

for i in S:
    if i.isalpha():
        alpha.append(i)
    else:
        value += int(i)

alpha.sort()

# 숫자가 전혀 없을 가능성이 있음
if value != 0:
    alpha.append(str(value))

print(''.join(alpha))