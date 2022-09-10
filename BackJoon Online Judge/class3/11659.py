# https://www.acmicpc.net/problem/11659

import sys


N,M = map(int, sys.stdin.readline().rstrip().split())

nums = list(map(int, sys.stdin.readline().rstrip().split()))

dp = [0] * (N+1)

dp[1] = nums[0]


for i in range(2, N+1):
    dp[i] = dp[i-1] + nums[i-1]

for _ in range(M):
    i,j=map(int,sys.stdin.readline().rstrip().split())
    print(dp[j]-dp[i-1])
