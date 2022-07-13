k , n , m = map(int, input().split())

price = k*n
money = m
mom = 0

if(price > money):
    mom = price - money

print(mom)
