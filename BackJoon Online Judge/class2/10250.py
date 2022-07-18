# https://www.acmicpc.net/problem/10250

case = int(input())

for _ in range(case):
    h,w,n = map(int, input().split())
    num = (n//h)+1 # 고객 호수
    floor = n%h # 고객 층수

    if n % h == 0: #호첼의 층수 배수의 손님이 온 경우 +1 를 해주면 X
        num = n//h
        floor = h
    
    print(f"{floor*100 + num}")



