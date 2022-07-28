# 큰 수의 법칙은 다양한 수로 이루어진 배열이 있을 때 주어진 수들을  M번 더하여 가장 큰 수를 만드는 법칙이다.
# 단, 배열의 특정한 인덱스(번호)에 해당하는 수가 연속해서 K번을 초과하여 더재질 수 없는 것이 이 법칙의 특징이다.
# 예를 들어 순서대로 2,4,5,4,6으로 이루어진 배열이 있을때 M이 8이고 K가 3이라고 가정하자.
# 이 경우 특정한 인덱스의 수가 연속해서 세번까지만 더헤질수 있으므로 큰수의 법칙에 따른 결과로는 6+6+6+5+6+6+6+5 = 46이다.

import sys


N , M , K = map(int, sys.stdin.readline().rstrip().split(" "))

data = list(map(int, sys.stdin.readline().rstrip().split(" ")))

data.sort() # 입력 받은 수 정렬하기

first = data[N - 1]
second = data[N -2]



first_count = int(M / (K + 1)) * K # 가장 큰수가 반복되어야 하는 횟수
first_count += M % (K + 1)  # 딱 나누어지지 않을 경우, 나머지를 가장 큰수의 횟수에 더해준다.

second_count = M - first_count # 두번째로 큰수를 더해야 하는 횟수

result = 0
result += first * first_count
result += second * second_count

print(result)


#  M의 크기가 100억 이상처럼 커지면, 시간 초과 문제가 발생한다.
# while True:
#     for _ in range(K):
#         if M == 0:
#             break
#         result += first
#         M -= 1

#     if M == 0:
#         break

#     result += second
#     M -= 1

# print(result)    
    
