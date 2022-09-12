# https://www.acmicpc.net/problem/17626

import sys


n = int(sys.stdin.readline().rstrip())

dp = [0] * (max(5,n+1))

# 좌측 숫자를 제곱수의 합으로 표현하기 위해 최소의 개수를 우측에 표시
dp[1] = 1
dp[2] = 2
dp[3] = 3
dp[4] = 1

# 규칙은 dp[i]에 dp[n - (n보다 작거나 같은 최대 제곱수)] + 1을 더해주면 된다.
for i in range(5, n+1):
    dp[i] = 4 # i로 하든 최소 4로 하든 상관 없다. 어차피, j**2가 i보다 클때까지 계속해서 최소개수를 찾을려고 하기 때문에
    for j in range(1, i):
        if j**2 > i:
            break
        dp[i] = min(dp[i], dp[i-j**2] + 1)
    
print(dp[n])