# https://www.acmicpc.net/problem/7568

# 몸무게 x , 키 y

import sys

case = int(sys.stdin.readline())
stu_list = []

for i in range(case):
    weight , height = map(int, input().split())
    stu_list.append((weight, height)) # 만약, 딕셔너리로 넣게 되면, 몸무게가 같아지면 제대로 된 결과값을 가져오지 못 한다.

for stu in stu_list:
    rate = 1 
    for sss in stu_list:
        if stu[0] < sss[0] and stu[1] < sss[1]: # 리스트 각각 있는 튜플 인덱싱 , 비교 대상보다 모든 값이 작아야지만 등수가 내려간다.
            rate += 1
    
    print(rate, end=" ")







