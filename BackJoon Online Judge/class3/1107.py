# https://www.acmicpc.net/problem/1107

import sys

# 가고자 하는 숫자
target = int(sys.stdin.readline().rstrip())

# false면 부서진 버튼이 없다. true면 부서진 버튼이 있다. (0~9)
broken = [False] * 10

# 부서진 버튼의 개수
broken_case = int(sys.stdin.readline().rstrip())

# 부서진 버튼이 있는 경우
if broken_case > 0:
    broken_list = list(map(int, sys.stdin.readline().rstrip().split()))
    # 부서진 숫지를 true로 바꾼다.
    for i in broken_list:
        broken[i] = True


# n 숫자를 누르는데 부서진 버튼이 있는지 확인한다.
def checkIfBroken(n):
    for i in str(n):
        if broken[int(i)]:
            return True
    return False


mininum = abs(target - 100) #100부터 숫자로 바로 이동하지 않고 +,-로 이동하는 경우

# 0부터 1000000까지 숫자를 누르는 경우
for i in range(1000001):
    # i 숫자를 누르는데 부서진 버튼이 없다면
    if checkIfBroken(i) == False:
        # i버튼까지 가는데 +,-로만 이동하는 경우 vs i 숫자까지 리모콘으로 직접 이동 후 나머지는 +,-로 이동하는 경우
        mininum = min(mininum, len(str(i)) + abs(target-i))

print(mininum)
        






