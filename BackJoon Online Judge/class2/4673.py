# https://www.acmicpc.net/problem/4673

def d(n):
    n = n + sum(map(int , str(n)))
    return n

# 리스트로 해도 괜찮지만 중복을 제거하기 위해 set 활용
con = set()

for i in range(1,10001):
    con.add(d(i))

for j in range(1,10001):
    if j not in con:
        print(j)