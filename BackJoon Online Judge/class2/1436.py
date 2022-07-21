# https://www.acmicpc.net/problem/1436
# 666을 포함하는 숫자중에서 몇 번째 숫자인가 하는 문제이다.

n = int(input())

tsix = 666
count = 0
num = 1

while True:
    # 666부터 숫자를 증가하면 된다.
    if '666' in str(num):
        count += 1
        if count == n: # 666를 맨처음에 찾으면 1번째라고 카운트 , 666이 포함된 숫자 들어갈때마다 count하고 사용자 입력값이랑 맞는지 확인
            print(num)
            break
    
    num += 1



    
    
    



