# https://www.acmicpc.net/problem/11279


import sys


N = int(sys.stdin.readline().rstrip())

max_heap = []


def pop_heap():
    # 제일 큰 숫자 뽑기
    num = max_heap[0]
    # 맨 뒤에 있는 원소 root로 올리기
    max_heap[0] = max_heap[-1]
    # 맨 뒤의 원소 제거
    max_heap.pop()

    # root로 올라온 새로운 노드의 위치
    idx = 0

    # heap의 마지막 원소까지 이동할 경우
    while idx <= len(max_heap) - 1:
        # 왼쪽 자식 노드 위치
        child = idx * 2 + 1

        # 왼쪽 자식이 없는 경우
        if child >= len(max_heap):
            break

        # 오른쪽 자식도 존재하고, 오른쪽 자식이 왼쪽 자식보다 더 클 경우 , child는 새롭게 할당
        if child + 1 < len(max_heap) and max_heap[child + 1] > max_heap[child]:
            child += 1
    
        if max_heap[idx] < max_heap[child]:
            max_heap[idx] , max_heap[child] = max_heap[child] , max_heap[idx]
            idx = child
        else:
            break

    
    # heap에서 root 위치에 존재하는 가장 큰 숫자 반환하기
    return num




def append_heap(num):
    max_heap.append(num)
    # 맨 마지막에 추가된 노드의 index
    idx = len(max_heap) - 1
   

    while idx > 0:
         # 부노 노드의 index
        parent=(idx-1)//2

        if max_heap[idx] > max_heap[parent]:
            # 스왑 실행
            max_heap[parent] , max_heap[idx] = max_heap[idx] , max_heap[parent]
            idx = parent
        else:
            # 그렇지 않다면 그대로
            break
    



for _ in range(N):
    x = int(sys.stdin.readline().rstrip())
    
    if x == 0:
        if len(max_heap) == 0:
            print(0)
        else:
            print(pop_heap())
    else:
        append_heap(x)
        