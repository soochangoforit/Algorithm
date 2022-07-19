# https://www.acmicpc.net/problem/10989

import sys

n = int(sys.stdin.readline())
num_list = [0] * 10001 # 최대 숫자 10000만큼 들어올 수 있다. 인덱스를 좀더 쉽게 사용하기 위해 10001까지 list 생성

for _ in range(n):
    num_list[int(sys.stdin.readline())] += 1 # input에 의해 값이 들어오면 해당 인덱스로 +1 해준다. 9가 2번 나오면 9인덱스 값은 +2 된다.

for i in range(10001): # i는 인덱스로 활용
    if num_list[i] != 0: # 한번이라도 input으로 값이 호출되었으면
        for j in range(num_list[i]): # 출력을 i로 하는 이유는, 애초에 숫자를 받을때 해당 숫자를 인덱스로 생각하고 넣었기 때문에
            print(i) # 그래서 똑같이 인덱스 i번째에 있는 값을 빼준다.


