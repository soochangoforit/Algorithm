
# 움직일 수 있는 총 경우의 수 8가지
import sys


steps = [(-2,-1), (-2,1), (-1,-2), (-1,2), (1,-2), (1,2), (2,-1), (2,1)]

data = sys.stdin.readline().rstrip()

row = int(data[1])

# column a를 숫자 1로 바꿔주는 작업을 거쳐보자
column = int(ord(data[0])) - int(ord('a')) + 1 # 영문자 a는 곧 1이며 , b는 2이다.

result = 0 # 움직일 수 있는 경우의 수

for step in steps:

    next_row = row + step[0]
    next_column = column + step[1]

    if next_row < 1 or next_column < 1 or next_row > 8 or next_column > 8:
        continue

    result += 1

print(result)

