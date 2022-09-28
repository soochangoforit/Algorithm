# https://www.acmicpc.net/problem/1931

import sys

# 회의의 개수
N = int(sys.stdin.readline().rstrip())

# 회의의 시작시간과 끝나는 시간을 저장할 리스트
times = []

# 회의의 시작시간과 끝나는 시간을 입력받아 리스트에 저장
for _ in range(N):
    times.append(list(map(int, sys.stdin.readline().rstrip().split())))

# 많은 회의를 진행하기 위해서는 끝나는 시간을 기준으로 오름 차순 정렬이 되어 있어야 한다. 일찍 시작하더라도 늦게 끝나면 의미 없다.
times.sort(key=lambda x: (x[1], x[0]))

# 회의의 개수를 저장할 변수
count = 0

# 회의의 끝나는 시간을 저장할 변수
end_time = 0

# 회의의 개수만큼 반복
# 가장 일찍 끝나는 회의부터 먼저 진행이 되면, 앞전 회의의 종료시간보다 새로 시작할 시작 시간이 더 회의 가능하다.
for i in range(N):
    # 회의의 시작시간이 이전 회의의 끝나는 시간보다 크거나 같다면
    if times[i][0] >= end_time:
        # 회의의 개수를 1 증가
        count += 1
        # 회의의 끝나는 시간을 저장
        end_time = times[i][1]

# 회의의 개수를 출력
print(count)
