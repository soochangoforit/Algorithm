# https://www.acmicpc.net/problem/10103

count = int(input())

aa = 100
bb = 100

for i in range(count):
    a,b = map(int , input().split())

    if(a>b):
        bb -= a
    elif(a<b):
        aa -= b
    else:
        continue

print(f"{aa} {bb}")
    
