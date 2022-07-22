# https://www.acmicpc.net/problem/2869

import math


A,B,V = map(int , input().split())


# (A-B)*day + A >= V --> True : print(day + 1) / False : day += 1
#  day >= (V - A) / (A-B) -> day의 최솟값 구하기

# print( 2 if int((V-A) / (A-B)) == 0 else int((V-A) / (A-B)) + 1) # 올림하는 math 모듈 사용하자
print(math.ceil((V-A)/(A-B))+1)
    
