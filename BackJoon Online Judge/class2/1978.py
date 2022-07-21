
import sys

case = int(sys.stdin.readline())

num_list = list(map(int , input().split()))

total = 0

for i in num_list: # i == 5
    for k in range(2 ,i+1 ): # 2,3,4,5
        if i % k == 0: # 1를 제외한 숫자를 한번씩 나누어보고 나머지가 0인 몫을 찾는다.
            if i == k: # 몫을 찾았는데 자기 자신이면 1개 count
                total += 1
            break
        
print(total)

    
            



