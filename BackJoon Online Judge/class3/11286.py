# https://www.acmicpc.net/problem/11286

from collections import deque
import sys

N = int(sys.stdin.readline().rstrip())

abs_heap = []

def insert_heap(num):
    # 맨마지막 위치에 값 넣기
    abs_heap.append(num)
    # 방금 추가한 num의 위치
    idx = len(abs_heap) - 1

    # num의 idx가 부모 노드까지 와서 0이 되는 순간 while를 종료한다.
    while idx > 0:
        # 부모 노드의 idx
        parent = (idx - 1) // 2
        # 부모 노드의 절대값이 자식 노드의 절대값보다 크면 바로 SWAP
        if abs(abs_heap[parent]) > abs(abs_heap[idx]):
            abs_heap[parent], abs_heap[idx] = abs_heap[idx], abs_heap[parent]
            idx = parent
        # 부모 노드의 절대값이 자식 노드의 절대값과 같으면
        elif abs(abs_heap[parent]) == abs(abs_heap[idx]):
            # 부호를 땐 부모 노드가 자식 노드보다 더 크면 바로 SWAP
            if abs_heap[parent] > abs_heap[idx]:
                abs_heap[parent], abs_heap[idx] = abs_heap[idx], abs_heap[parent]
                idx = parent
            # 부호를 땠는데 부모 노드가 자식 노드보다 더 작은 경우는 그냥 종료
            else:
                break
        # 부모 노드의 절대값이 자식 노드의 절대값보다 작은 경우는 그냥 종료
        else:
            break

def pop_heap():
    # 배열의 젤 처음위치를 반환값으로 저장하기 위해 result로 저장
    result = abs_heap[0]
    # 배열의 마지막 위치의 값을 젤 처음 위치로 옮기기
    abs_heap[0] = abs_heap[-1]
    # 배열의 마지막 위치의 값을 삭제 (재배치를 하기 위해)
    abs_heap.pop()

    # root까지 올라온 재배치 하고자 하는 노드의 idx
    idx = 0
    # 재배치 하고자 하는 노드의 idx가 배열의 마지막 위치보다 작은 동안 while을 반복한다.
    while idx < len(abs_heap)-1:
        # 자식 노드의 idx
        child = idx * 2 + 1
        # 자식 노드의 idx가 배열의 마지막 위치보다 크면 while을 종료한다. 범위를 벗어난 경우
        if child >= len(abs_heap):
            break
        # 자식 노드의 우측 값이 존재하고, 우측 값이 좌측 값보다 작으면 우측 값으로 변경
        if child + 1 < len(abs_heap) and abs(abs_heap[child]) > abs(abs_heap[child + 1]):
            child += 1
        # 자식 노드의 절대값이 둘다 같은 경우
        elif child + 1 < len(abs_heap) and abs(abs_heap[child]) == abs(abs_heap[child + 1]):
            # 실제로 더 작은 값을 가지는 자식 노드로 변경
            if abs_heap[child] > abs_heap[child + 1]:
                child += 1

        # 부모 노드의 절대값이 자식 노드의 절대값보다 크면 바로 SWAP
        if abs(abs_heap[idx]) > abs(abs_heap[child]):
            abs_heap[idx], abs_heap[child] = abs_heap[child], abs_heap[idx]
            idx = child
        # 부모 노드의 절대값이 자식 노드의 절대값과 같으면, 실제 값을 비교필요
        elif abs(abs_heap[idx]) == abs(abs_heap[child]):
            # 실제 부호를 땐 부모 노드가 자식 노드보다 더 크면 바로 SWAP
            if abs_heap[idx] > abs_heap[child]:
                abs_heap[idx], abs_heap[child] = abs_heap[child], abs_heap[idx]
                idx = child
            # 부호를 땐 부모 노드가 자식 노드보다 더 작으면 이상적인 모습이므로 그냥 종료
            else:
                break
        # 부모 절대값이 자식 절대값보다 이미 먼저 작은 경우 이상적인 모습이므로 그냥 종료
        else:
            break

    # 배열의 맨첫번째 위치의 값을 반환
    return result


# heap을 만드는데 절대값이 가장 작은 것을 우선순위로 두고 만든다.
# 입력 값을 모두 받아서 Heap을 만드는것보단. 입력값을 받으면서 Heap 규칙에 맞게 만들어준다.
for i in range(N):
    num = int(sys.stdin.readline().rstrip())
    if num == 0:
        if len(abs_heap) == 0:
            print(0)
        else:
            print(pop_heap())
    else:
        insert_heap(num)



