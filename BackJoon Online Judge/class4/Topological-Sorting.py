# 위상 정렬
# 입력 : 노드의 개수, 간선의 개수
# 다음줄 부터 , 간선을 개수만큼 공백으로 띄워져서 받는다.


from collections import deque
import sys


input = sys.stdin.readline

# 노드의 개수와 간선의 개수를 입력 받음
V, E = map(int, input().split())

# 모든 노드에 대한 진입 차수는 0으로 초기화
indegree = [0] * (V+1)

# 각 노드에 연결된 간선 정보를 담기 위한 연결 리스트
# index 0은 무시한다.
graph = [[] for _ in range(V+1)]

# 초기 설정
for _ in range(E):
    a, b = map(int, input().split())
    # 해당 인덱스의 진출 차수를 index 위치에 맞게 삽입
    graph[a].append(b)
    # 진출할 차수의 인덱스에 진입 차수 1 증가 
    indegree[b] += 1


# 시작 위치
start = 0

# 방문한 순서 저장 리스트, 출력의 대상
result = []

# 시작 위치 찾기 (진입 차수가 0인 지점)
for i in range(1,V+1):
    if indegree[i] == 0:
        start = i


# 위상정렬
def topology_sort(start):
    q = deque()
    q.append(start)

    while q:
        now = q.pop()
        # 방문 했기 때문에, 배열에 넣어줌
        result.append(now)

        # 해당 노드와 연결된 노드들의 진입 차수를 1씩 감소, 감소 했을 때 0이 되면 큐에 삽입
        for i in graph[now]:
            indegree[i] -= 1
            if indegree[i] == 0:
                q.append(i)


topology_sort(start)

# 출력
for i in result:
    print(i, end=' ')