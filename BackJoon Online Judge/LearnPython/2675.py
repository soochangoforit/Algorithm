from re import A
from typing import final


count = int(input())

for i in range(count):
    str=input().split()

    A=int(str[0])
    B=str[1]

    text = ''

    for k in B: # B가 str로 들어오지만 하나 하나 차례대로 실행이 가능하다. 
        text += A*k
    
    print(text)