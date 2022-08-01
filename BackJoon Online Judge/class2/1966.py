# https://www.acmicpc.net/problem/1966

# 숫자가 높을수록 중요도가 높다. 먼저 출력 되어야 한다.

# 4 2
# 1 2 3 4 
# 초기 문서의 상태는 1 2 3 4 이고 첫번째 줄에서 "2"라는 의미는 인덱스의 개념이고
# 인덱스 2인 "3"이 몇번째로 출력이 되는지 궁금하다는 문제이다.

# 중요도 숫자가 높은 대상이 맨앞으로 오도록 정렬이 필요하다.

import sys


test_case = int(sys.stdin.readline().rstrip())

for _ in range(test_case):
    
    length , index = map(int, sys.stdin.readline().rstrip().split())
    q = list(map(int , sys.stdin.readline().split())) # 초기 숫자 목록

    # q에서 index에 해당하는 위치 값을 알고 있어야 한다. 정렬이 진행될때마다 해당 index값이 바뀌어야 한다.
    q_index = [ 0 for x in range(length)]
    q_index[index] = 'target'

    order = 0

    while True:
        if q[0] == max(q): # 첫번째 숫자가 max이면 곧 해당 숫자를 pop하고 순서를 증가시켜야 한다.
            order += 1

            if q_index[0] == 'target': # 제일 앞에 중요도가 큰 숫자가 왔고, 출력하고자 하는 0번째 인덱스에 'target'이 있다는 의미는 현 배열에서 'target'이 가장 큰 중요도를 갖고 있다는 의미
                print(order)
                break
            else:
                q.pop(0)
                q_index.pop(0)

            
        else:
            q.append(q.pop(0)) # 그냥 pop하면 맨뒤의 요소 꺼내고, 특정 index를 넣어주면 해당 index 값을 반환한다.
            q_index.append(q_index.pop(0)) # target의 index도 정렬될때마다 함께 움직여야 한다.

    







