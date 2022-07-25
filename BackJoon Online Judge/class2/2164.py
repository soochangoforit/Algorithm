# https://www.acmicpc.net/problem/2164


from collections import deque
import sys


count = sys.stdin.readline().rstrip()

cards = deque(x for x in range(1,int(count)+1))

for i in range(len(cards)-1): # 0 , 1 , 2 

     k = 0
     cards.popleft()
     a = cards[k]
     cards.popleft()
     cards.append(a)

    

print(*cards)