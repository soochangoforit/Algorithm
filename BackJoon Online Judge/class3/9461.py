# https://www.acmicpc.net/problem/9461

import sys


count = int(sys.stdin.readline().rstrip())

dp = [0] * 101
dp[1] = 1
dp[2] = 1
dp[3] = 1
dp[4] = 2
dp[5] = 2

for _ in range(count):
    num = int(sys.stdin.readline().rstrip())

    for i in range(6, num + 1):
        dp[i] = dp[i - 1] + dp[i - 5]
    
    print(dp[num])
