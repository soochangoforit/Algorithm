# https://www.acmicpc.net/problem/2579

import sys

# 계단의 개수 입력 받는다.
count = int(sys.stdin.readline())

# 계단의 개수만큼 공간을 마련한다.
# 301개의 공간을 마련하는 이유는 0번째 인덱스를 사용하지 않기 위해서이다.
stairs = [0] * 301
dp = [0] * 301

# 계단의 점수를 입력 받는다. index 1번부터 계단의 점수를 입력 받는다.
for i in range(1,count+1):
    stairs[i] = int(input())

#dp에 들어가는 숫자는 해당 계단에 오르기 까지 최대 점수를 얻었을 경우의 점수이다.
dp[1] = stairs[1] # 1번째 계단에 오르기 까지 최대 점수는 1번째 계단의 점수이다.
dp[2] = stairs[1] + stairs[2] # 2번째 계단에 오르기 까지 최대 점수는 1번째 계단의 점수와 2번째 계단의 점수를 더한 값이다.
dp[3] = max(stairs[1], stairs[2]) + stairs[3] # 3번째 계단에 오르기 까지 최대 점수는 1번째 계단의 점수와 2번째 계단의 점수 중 큰 값과 3번째 계단의 점수를 더한 값이다.

# dp 상향식으로 접근
for i in range(4,count+1):
    dp[i] = max(dp[i-3] + stairs[i-1], dp[i-2]) + stairs[i] # dp[i-2]에서 2칸 올라오는 경우 혹은 dp[i]에서 한칸 바로 뒤에서 1칸 올라오는 경우는 dp[i-1]이 연속 3칸 올라온 경우의 수가 존재할 수 있기 때문에, 온전히 한칸 뒤의 점수를 받고 추가적으로 dp[i-2]에 해당하는 점수를 받아온다. (3계단 연속 올라가는것을 방지하기 위해) 

print(dp[count])



