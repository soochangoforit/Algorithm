# 11279 최대힙 문제를 heapq 모듈처럼 코드를 구현하고 사용하고자 한다
# 해당 연습은 heapq의 내부 동작 방식을 이해하고
# 이를 바탕으로 최소 신장 트리에서 프림 알고리즘 과 다익스트라 알고리즘을 구현하는데 heqpq 모듈을 사용할 예정이다.
# 제공해주는 라이브러리를 사용하더라도, 내부 동작 방식을 이해하고 사용하는 것이 중요하다고 판단하기 때문에

import sys

# 최대힙

input = sys.stdin.readline


# 원소를 최대힙에 삽입시, 최대힙의 성질을 만족하도록 heapify
def up_heapify(idx, heap):
    child_index = idx
    # child_index가 0이면 부모노드가 없으므로 종료 (즉, 현재 heap에 들어있는 노드는 루트노드 1개 이라는 의미)
    while child_index != 0:
        # 부모 노드
        parent_index = (child_index - 1) // 2
        # 최대힙을 구성하는데, 자식 노드가 부모 노드보다 크면 swap
        if heap[child_index] > heap[parent_index]:
            heap[child_index], heap[parent_index] = heap[parent_index], heap[child_index]
            # 자식 위치의 노드가 최대힙을 만족할때까지 부모 노드의 위치로 올라간다.
            child_index = parent_index
        else:
            return


def find_bigger_child(index, heap_size):
    parent = index
    left_child = (parent * 2) + 1
    right_child = (parent * 2) + 2

    # 우선, 부모 노드와 왼쪽 자식과 비교해서 왼쪽 자식이 부모 노드와 비교해서 더 크면, 부모 노드 위치를 왼쪽 자식 노드 위치로 변경
    if left_child < heap_size and priority_queue[parent] < priority_queue[left_child]:
        parent = left_child
    
    # 마지막으로 부모 노드와 오른쪽 자식과 비교해서 오른쪽 자식이 부모 노드와 비교해서 더 크면, 부모 노드 위치를 오른쪽 자식 노드 위치로 변경
    if right_child < heap_size and priority_queue[parent] < priority_queue[right_child]:
        parent = right_child

    # 만약 parent가 어떤 if 문을 거치지 않았다는 의미는 parent가 최대 힙에 맞게 위치하고 있다는 의미이다.
    # 더 이상 자식 노드들과 비교해서 위치를 바꿀 필요가 없다는 의미이다.
    return parent



def down_heapify(index, heap):
    parent_index = index

    # 최대힙이기 때문에, 자식 노드들 중에서 큰 노드를 찾는다.
    bigger_child_index = find_bigger_child(parent_index, len(heap))

    # parent_index가 bigger_child_index와 같다는 의미는, find_bigger_child 함수에서 parent 보다 큰 자식 노드가 없다는 의미이다. (즉, 최대힙에 맞게 위치하고 있다는 의미)
    # 최대힙을 만족하지 않고 있을때만 while문을 돌아야 한다.
    while parent_index != bigger_child_index:
        # 부모 노드와 자식 노드를 swap
        heap[parent_index], heap[bigger_child_index] = heap[bigger_child_index], heap[parent_index]
        # 자식 노드가 마치 부모 노드의 입장이 되어서 최대힙을 만족할때까지 leap 노드로 내려가기 위해 (while 계속 돌기 위해서 - 최대힙 만족할때까지)
        parent_index = bigger_child_index
        # 자식 노드가 마치 부모 노드의 입장이 되어서 자신보다 큰 자식 노드들을 자신의 위치까지 다시 올려주기 위해서
        bigger_child_index = find_bigger_child(parent_index, len(heap))

    


def heap_pop(heap):
    # 최대힙이기 때문에, 루트 위치에 있는 최대값을 반환
    tmp = heap[0]
    # 맨 뒤에 있는 원소를 부모 노드로 올린다
    heap[0] = heap[-1]
    # 맨 뒤에 있는 원소가 부모로 올라갔으므로, 맨 뒤에 있는 원소를 삭제
    heap.pop()
    # 부모 노드에 새롭게 온 노드를 최대힙에 만족하도록 down_heapify 해준다.
    down_heapify(0, heap)
    # tmp에 저장된 최대값 반환
    return tmp
   





N = int(input())
priority_queue = []
for _ in range(N):
    num = int(input())
    # num이 0이면 pop, 아니면 push
    if num != 0:
        priority_queue.append(num)
        up_heapify(len(priority_queue) - 1, priority_queue)
    else:
        if len(priority_queue) == 0:
            print(0)
        else:
            print(heap_pop(priority_queue))



