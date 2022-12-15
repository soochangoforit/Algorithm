# https://www.acmicpc.net/problem/1912
# 다이나믹 프로그래밍의 사용 조건을 만족하는지 확인해보자
# 1. 큰 문제를 작은 문제로 나눌 수 있다. (최적 부분 구조)
# 2. 작은 문제에서 구한 정답은 그것을 포함하는 큰 문제에서도 동일하다. (중복되는 부분 문제)
# 대표적으로 피보나치 수열은 다이나믹 프로그래밍의 사용 조건을 만족한다.


import sys

input = sys.stdin.readline

N = int(input().rstrip())

arr = list(map(int, input().split()))

# dp[i] = i번째 수를 마지막으로 하는 연속합의 최대값

dp = [0] * N

dp[0] = arr[0]

# 보텀업 방식으로 구현 (기본 방식)
for i in range(1, N):
    # i번째 수를 마지막으로 하는(i번째 수를 반드시 포함하는) 연속합의 최대값은
    # i - 1번째 수를 마지막으로 하는 연속합의 최대값 + i번째 수와
    # i번째 수 중 큰 값이다.
    # 전제 조건은 i - 1번째 수를 마지막으로 연속된 숫자들의 합 중에서 최대 값을 구했다는 것이다.
    dp[i] = max(dp[i - 1] + arr[i], arr[i])

print(max(dp))