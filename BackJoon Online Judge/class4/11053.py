# https://www.acmicpc.net/problem/11053
# 다이나믹 프로그래밍 학습을 위한 문제 풀이

import sys

input = sys.stdin.readline

N = int(input().rstrip())

arr = list(map(int, input().split()))

# 핵심 : dp[i] = i번째 원소를 {마지막 원소}로 가지는 부분 수열의 {최대 길이}

dp = [1] * N

for i in range(1, N):
    for k in range(i):
        # 해당 인덱스의 값보다 작은 값이 있다면,
        # 그 값을 마지막 원소로 가지는 부분 수열의 최대 길이와 지금까지 구한 길이를 비교하여
        # 더 큰 값을 저장한다.
        if arr[k] < arr[i]:
            # 2번째 for문이 끝날때까지 계속해서 업데이트 해주는 느낌이다
            dp[i] = max(dp[i], dp[k] + 1)

print(max(dp))



