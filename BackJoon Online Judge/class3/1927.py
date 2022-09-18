# https://www.acmicpc.net/problem/1927

from collections import deque
import sys


N = int(sys.stdin.readline().rstrip())

min_tree = []

def popleft():

    result = min_tree[0]

    # 마지막 원소를 root로 올린다.
    min_tree[0] = min_tree[-1]
    # 맨마지막 원소를 삭제한다.
    min_tree.pop()

    # root의 인덱스
    idx = 0

    # root 위치에 있는 원소와 자식의 원소를 대소비교를 한다.
    # root가 자식보다 크면 자식과 자리를 바꾼다.
    # while 반복 조건은 root에 위치한 원소의 index idx가 맨마지막 원소 index와도 비교를 해야하기 때문에 idx <= len(min_tree) - 1도 가능
    while idx <= len(min_tree)-1:
        # 왼쪽 자식 인덱스
        child = idx * 2 + 1

        # 자식이 없으면 더 이상 비교할 대상 X. While 탈출
        if child >= len(min_tree):
            break

        # 오른쪽 자식이 있으면 and 왼쪽 자식과 비교해서 더 작은 자식을 선택한다.
        if child + 1 < len(min_tree) and min_tree[child] > min_tree[child+1]:
            # 왼쪽 자식보다, 오른쪽이 더 작기 때문에 오른쪽 자식을 선택한다.
            child += 1

        # root가 좌 우측자식 중에 작은 자식보다 크면 자식과 자리를 바꾼다.
        if min_tree[idx] > min_tree[child]:
            # 자식과 root 서로 Swap
            min_tree[idx], min_tree[child] = min_tree[child], min_tree[idx]
            # child idx를 현재 idx로 바꿔준다.
            # 마지막에 child가 마지막 index와 idx가 바뀌면 더 이상 비교할 대상 X. While 탈출
            idx = child
        else:
            break

    return result



def addNum(num):
    min_tree.append(num)

    # 마지막에 추가된 숫자의 인덱스
    idx = len(min_tree) - 1

    # 마지막에 추가된 숫자가 부모보다 작으면 부모와 자리를 바꾼다.
    # 제일 마지막에 추가된 원소가 가장 작은 값이면 idx = 0인 index에 위치할때 while 빠져나가면 된다.
    while idx > 0:
        # 부모 인덱스
        parent = (idx - 1)//2
        if min_tree[parent] > min_tree[idx]:
            # 부모와 자식 서로 Swap
            min_tree[parent], min_tree[idx] = min_tree[idx], min_tree[parent]
            # parent idx를 현재 idx로 바꿔준다.
            # 마지막에 parent가 0인 index와 idx가 바뀌면 더 이상 비교할 대상 X. While 탈출
            idx = parent
        else:
            break




for i in range(N):
    num = int(sys.stdin.readline().rstrip())

    # 0이 입력되면 가장 작은 값을 출력하고, 해당 값을 트리에서 제거
    if num == 0:
        if len(min_tree) == 0:
            print(0)
        else:
            print(popleft())
        
    # 0이 아닌 수가 입력되면 해당 값을 트리에 추가
    else:
        addNum(num)
   