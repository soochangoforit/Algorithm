# 크루스칼 알고리즘 학습


import sys


input = sys.stdin.readline

# 노드의 개수와 간선의 개수 입력받기
V, E = map(int, input().split())

# 부모 테이블 초기화
parent = [0] * (V + 1)

# 초기 부모 테이블에서 부모를 자기 자신으로 초기화
for i in range(1, V + 1):
    parent[i] = i

# 간선 정보 입력받기
edges = []

for _ in range(E):
    # a에서 b로 가는 비용이 cost
    a, b, cost = map(int, input().split())
    # 프림 알고리즘은 노드끼리의 접근을 하기 때문에, 하나의 간선에 대해서 양방향으로 입력을 해줘야 한다.
    # 하지만 크루스칼 알고리즘은 간선끼리의 접근을 하기 때문에, 양방향으로 입력을 해주지 않아도 된다.
    # 비용 순으로 오름차순 정렬하기 위해 튜플의 첫번째 원소를 비용으로 설정
    edges.append((cost, a, b))

# 간선을 index 0인 비용 순으로 정렬
edges.sort()


# 총 최소 비용(가중치)
result = 0

# 특정 원소의 최상위 부모 찾기
def find_parent(parent, x):
    # 루트 노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출
    if parent[x] != x:
        parent[x] = find_parent(parent, parent[x])
    return parent[x]

# 두 원소가 속한 집합 합치기
def union_parent(parent, a, b):
    a = find_parent(parent, a)
    b = find_parent(parent, b)
    # 더 작은 번호의 노드가 부모가 되도록
    if a < b:
        parent[b] = a
    else:
        parent[a] = b

# 간선을 하나씩 확인하며
for edge in edges:
    cost, a, b = edge
    # 사이클이 발생하지 않는 경우에만 집합에 포함
    if find_parent(parent, a) != find_parent(parent, b):
        union_parent(parent, a, b)
        result += cost

print(result)

# 입력 예시
# 7 9
# 1 2 29
# 1 5 75
# 2 3 35
# 2 6 34
# 3 4 7
# 4 6 23
# 4 7 13
# 5 6 53
# 6 7 25

# 출력 예시
# 159