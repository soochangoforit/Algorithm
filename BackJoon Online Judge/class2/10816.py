# https://www.acmicpc.net/problem/10816
# https://chancoding.tistory.com/45

# 앞전 코드의 문제점을 해결하고자, 새로운 코드 작성
# M에 속해있는 갯수만 구하고자 한다. 이미 한번 카운트된 숫자는 다음 숫자의 카운트 시에 제외하고자 한다.

import sys


_ = sys.stdin.readline()
N = sorted(map(int , sys.stdin.readline().split()))
_ = sys.stdin.readline()
M = list(map(int , sys.stdin.readline().split())) # M에서 제공된 숫자에 맡게끔 출력을 해야하기 때문에 Sorted는 m_dic에 값을 넣을때 사용한다.

index = 0 # N 리스트의 index
m_dic = {} # M리스트에 있는 숫자의 개수를 dic 형태로 저장하고자 한다.

for m in sorted(M): 
    cnt = 0 # 찾고자 하는 숫자가 발견된 수
    if m not in m_dic: # m 숫자의 개수를 N 리스트에서 찾을려고 할때 한번에 모두 찾기 때문에, m에 대해서는 같은 숫자에 대해 반복적으로 찾지 않는다.
        while index < len(N):
            if m == N[index]: # 찾고자 하는 수가 N 리스트에 있을 때
                cnt += 1
                index += 1 # 있으면 다음 N리스트로 접근한다.
            elif m > N[index]: # 찾고자 하는 값이 N 리스트보다 클때, 다음 index로 넘어간다
                index += 1
            else: # m < N[index] -> else라는 의미는 우리가 찾고자 했던 m이 N 리스트에 존재하지 않는다는 것이다.
                break # break를 통해서 찾고자 했던 숫자 m에 대해서는 cnt가 0으로 가진다.
            
            #while문 종료, 찾고자 했던 m에 대한 cnt 값을 구한다.
        # if문 종료, m_dic에 찾고자 했던 숫자의 cnt값을 key 값에 맞춰서 넣어준다.    
        m_dic[m] = cnt

print(' '.join(str(m_dic[m]) for m in M))