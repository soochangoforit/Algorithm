count  = int(input())


price = 0
money=[]

for i in range(count):
    a,b,c = map(int, input().split())

    if a == b == c :
        money.append(10000 + a*1000)

    elif (a == b and a != c):
        money.append(1000 + a*100)
    elif (a == c and a != b):
        money.append(1000 + a*100)
    elif (b == c and a != b):
        money.append(1000 + b*100)
    else:
        m=max(a,b,c)
        money.append(m*100)

print(max(money))