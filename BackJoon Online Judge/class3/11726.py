# https://www.acmicpc.net/problem/11726

import sys


n = int(sys.stdin.readline().rstrip())

dp = [0] * (max(4,n+1))

dp[1] = 1
dp[2] = 2
dp[3] = 3

for i in range(4, n+1):
    dp[i] = dp[i-1] + dp[i-2]

print(dp[n] % 10007)



