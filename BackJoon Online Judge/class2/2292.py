# https://www.acmicpc.net/problem/2292

n = int(input())
cnt = 1 # 초기에 채워진 꿀벌집
cnt_six = 6 # 동그란 라인별로 증가하는 꿀법집 수
count = 1 # 최소 접근 횟수

while n > cnt:
    cnt += cnt_six # 현재 cnt는 최대한 채울수 있는 벌집의 수이다.
    cnt_six += 6 
    count += 1
                # 최대로 채워진 cnt 벌집의 수보다 n이 숫자가 더 크면 한번도 바깥 라인을 채워준다.

print(count)

