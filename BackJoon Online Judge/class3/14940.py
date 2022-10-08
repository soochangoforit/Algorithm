# https://www.acmicpc.net/problem/5430

from collections import deque
import sys

# 테스트 케이스 수 입력
T = int(sys.stdin.readline().rstrip())

for _ in range(T):
    # 명령어 입력
    comand  = sys.stdin.readline().rstrip()
    # 배열 길이 입력
    count = int(sys.stdin.readline().rstrip())
    # 앞뒤, "[" "]" 제거 후 ","로 나눠 리스트로 변환
    nums = sys.stdin.readline().rstrip()[1:-1].split(",")
    # 덱을 만들어준다. pop하는데 일반 배열보다 훨씬 빠른 속도로 수행할 수 있다.
    queue = deque(nums)

    print(queue)

    # R 문자의 개수, R이 들어올때 마다 reverse를 하면 시간 초과 발생 개수에 따라서 reverse 할지 안 할지 결정 해주기 위한 기준
    r_cnt = 0

    # 아무런 숫자 혹은 문자 데이가 들어오지 않는 경우
    # "[]"라고 입력을 받아도 deque의 길이는 1이 되기 때문에 길이가 0인 부분에 대해서는 예외사항으로 빈 큐로 초기화를 해줘야 한다.
    if count == 0:
        # 빈 덱으로 초기화
         queue = []

    # 빈 배열을 D로 했을때 error을 출력할 수 있도록 flag 사용
    flag = 1

    for i in range(len(comand)):

        # R를 하는건 많은 자원이 소모되기 때문에, 짝수인 경우 뒤집지 않고, 홀수인 경우만 뒤집자
        if comand[i] == 'R':
            r_cnt += 1

        if comand[i] == 'D':
            # 빈 덱인 경우 error 출략
            if len(queue) == 0:
                flag = 0
                print('error')
                # break를 통해 다음 Test Case로 넘어가게 한다.
                break
                
            # 빈 덱이 아닌 경우
            else:
                # 명령어가 D가 나오기 전까지 짝수인 경우는 그대로 앞문자 출력, 홀수일 경우는 제일 뒷문자 제거
                if r_cnt % 2 == 0:
                    queue.popleft()
                else:
                    queue.pop()

    # D를 통해서 제거해줄 문자는 다 제거를 해주고 최종적으로 R의 개수에 따라서 바로 출력 혹은 뒤집어서 출력 할 수 있도록 한다.
    else:
        if r_cnt % 2 == 0:
            print('['+ ','.join(queue)+']')
        else:
            queue.reverse()
            print('['+ ','.join(queue)+']')
