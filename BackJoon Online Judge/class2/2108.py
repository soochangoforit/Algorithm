# https://www.acmicpc.net/problem/2108

from ast import operator
import sys
import math

case = int(sys.stdin.readline().strip())

nums = [ int(sys.stdin.readline().rstrip()) for _ in range(case)]

# 산술 평균
print(round(sum(nums)/len(nums))) # 두번째 인자를 생락하면 소수점 첫재자리에서 반올림 한다.

# 중앙값
nums.sort()
print(nums[int(len(nums) // 2)])

def countLetters(word):
    counter = {}
    for letter in word:
        if letter in counter:
            counter[letter] += 1
        else:
            counter[letter] = 1
    return counter

dic = countLetters(nums)

if len(nums) >= 2:
    # 선조건은 최빈수를 기준으로 내림차순으로 정렬을 하고, 빈도수가 같은 경우 2번째로 작은 값을 출력하기 위해서 오름차순 정렬
    dic = dict(sorted(dic.items(), key= lambda x : x[0] )) # 오름 차순 정렬 (후조건)
    dic = sorted(dic.items(), key = lambda x : x[1] , reverse = True) # 최딘수대로 내림차순 정렬 (선조건)
    
    # 최빈수를 기준으로 정렬을 하였으나, 최빈수가 같은 값이 나오지 않아서 오름차순 정렬이 안먹힐때가 있다. 그럴때는 최빈값이 1개 이므로 맨 첫번째 값 출력

    # 최빈수가 같은 경우에는 오름차순이 이미 먹혀있기 때문에 ,2번째로 작은 수가 뒤에 오게 된다. 그대로 출력해준다.
    if dic[0][1] == dic[1][1]:
        print(dic[1][0])

    else: # 최빈값이 1개일 경우, 단순히 맨 앞에꺼를 출력하면 된다.
        print(dic[0][0])

    # 난 처음에 if문으로 분기할 생각을 하지 못햇다. 
    # 단순히, print(dic[1][0])을 이용했지만
    # 여러 최빈값이 있는 경우에는 해당 코드만 있을 경우 올바른 값이 출력이 되지만
    # 최빈값이 1개일 경우에는 해당 코드는 올바른 결과 값을 내놓지 못한다 저런식으로 print(dic[1][0])을 해버리면 최빈값이 2번째로 낮은 수에 대해서 출력하는것이다.
    # 문제에서 요구하는거랑 맞지 않다. 그래서 if문을 한번더 분기하여, 최빈값이 여러개 있을 경우 오름차순까지 먹은 dic에서 2번째 값을 출력하고
    # 최빈값이 1개일 경우에는 1번째 값을 출력하도록 해야한다.

else:
    print(nums[0])

print(max(nums) - min(nums))




