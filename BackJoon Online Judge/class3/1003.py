# https://www.acmicpc.net/problem/1003

import sys


T = int(sys.stdin.readline())

# 0, 1 호출 횟수를 index별로 차례대로 넣는다.
# 배열을 만들어서 값을 저장하는 이유는 '다이나믹 프로그래밍'을 통해 이미 구한 숫자를 또다시 구하는 일이 없도록 하여 시간을 단축시키기 위함이다.
zero = [1,0,1]
one = [0,1,1]

def fibonacci(num):
    length = len(zero) # 지금까지 구했던 피보나치 수열의 길이, 이미 한번 구했던 수열은 다시 구하지 않기 위해 사용한다.
    if num >= length: # 0,1,2는 이미 구했기 때문에 , 숫자 3부터 구할 수 있다. 배열에 존재하는 숫자의 길이와 num 사이의 숫자들을 순회하면서 zero ,one 횟수를 구하고 배열에 넣어준다.
        for i in range(length, num+1):
            zero.append(zero[i-1] + zero[i-2])
            one.append(one[i-1] + one[i-2])
    print(zero[num], one[num])


# 이렇게 함으로써 얻는 가장 큰 이점은 for문을 통해서 한번 순회한 경우, 그 기록이 배열에 남아있다.
# 그렇기 때문에 다음에 같은 숫자를 구할 때는 배열에 있는 값을 가져오면 되기 때문에 시간을 단축시킬 수 있다.
for i in range(T):
    fibonacci(int(sys.stdin.readline()))

