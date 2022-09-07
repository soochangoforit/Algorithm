# https://www.acmicpc.net/problem/9095

import sys


n = int(sys.stdin.readline().rstrip())

for _ in range(n):
    num = int(sys.stdin.readline().rstrip())
    dp = [0] * (max(4,num + 1)) # 0번째 index는 사용하지 않기 위해서, 최소한으로 공간 4개 할당

    dp[1], dp[2], dp[3] = 1, 2, 4 # 1, 2, 3경우의 수는 미리 계산을 한다.


    # 4 경우의 수를 계산하기 위해서는 3의 경우의 수를 활용할 수 있다.
    # 3의 (1,1,1), (1,2), (2,1) 에서 단순히 1를 더해주는 경우의 수 3가지
    # 4는 (4) 단일 숫자 4로 사용할 수 X, 1,2,3만 사용 가능
    # 숫자(3)만 사용되는 경우를 풀어서 생각하기 위해 (2 + 1)를 활용, 2의 경우의 수에서 +1를 하되, 숫자 2만 사용하는 경우를 빼고 +1만 하면 되기 때문에 dp[i-2]를 사용
    for i in range(4, num + 1):
        dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3]
    
    print(dp[num])

    

