# https://www.acmicpc.net/problem/5063
time = int(input())

for i in range(time):
    r , e , c = map(int , input().split())

    if e - c == r :
        print("does not matter")
    elif e - c > r :
        print("advertise")
    else:
        print("do not advertise")

