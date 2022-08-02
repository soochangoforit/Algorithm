# https://www.acmicpc.net/problem/1654

# 최대한 N의 개수에 가깝게 해서 랜선의 길이를 선정해야 한다.

import sys

input = sys.stdin.readline

# k : 이미 가지고 있는 랜선의 개수, n : 필요한 랜선의 개수
k, n = map(int, input().split())
lan_cable = [int(input()) for _ in range(k)]

answer = 0  # 랜선의 최대 길이

start, end = 1, max(lan_cable)
while start <= end: # 랜선의 길이를 정하는 반복문 , start가 end보다 커지기 시작하는 시점에 false로 앞서 찾았놨던 answer 반환
    mid = (start + end) // 2
    len_length = 0
    for i in lan_cable:  # mid 길이만큼 랜선 케이블들을 조각냄
        len_length += i // mid

    if len_length >= n:  # 랜선의 개수가 n이상이면, 더 긴 길이로 잘라야 한다. , temp_sum이 n이랑 같아 졌다고 최대 길이가 갖는건 아니다.
                         #  N이 11라고 하면 len_length가 10일되기 직전의 mid를 찾아야 한다.
                         # 10이 되었다가 11이 되며
        start = mid + 1
        answer = mid # (222 223 224) / (223 223 224) / (224 224 224) -> answer = 224 -> (225,224,244) -> while false -> 224 return
    else:  # 랜선의 개수가 n미만이면 , 더 짧은 길이로 잘라야 한다. 분기분이 len_length >= N으로 설정했기 때문에, else로 들어가면 len_length >= n를 맞추기 위해 범위를 최대한 맞춰본다.
        end = mid - 1

print(answer)


