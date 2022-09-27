# https://www.acmicpc.net/problem/1697

from collections import deque
import sys

# +1 , -1 , *2를 하면서 N부터 K까지 최단거리를 구하는 문제이다.
N, K = map(int,sys.stdin.readline().split())

if N>=K:
    print(N-K)
    sys.exit()
else:
    Q = deque([N])

    # 방문한 흔적도 남기고, 해당 노드까지 오는데 거친 노드의 개수(몇초)를 알 수 있다.
    visited = [0] * 100001
    # 시작 노드 N에 대해서는 거쳤다고 판단해서 1를 넣어준다.
    visited[N] = 1

    while Q:
        now = Q.popleft()
        # 다음 숫자를 방문하는 3가지 방법
        for i in [now-1, now+1, now*2]:
            # 방문을 하려는 숫자는 범위 안에 속해야 한다.
            if 0 <= i <= 100000:
                # 방문한적이 없는 숫자라면
                # 혹은 이미 방문한 흔적이 있다면 거치지 않게 한다. 그 이유는 이미 방문한 노드가 있으면 이미 간선(관계)의 수가 있다는 의미이고
                # 그 수가 now가 곧 방문해서 얻는 간선(관계)의 수보다 적다는것을 보장한다. 최소 접근 개수를 보장할 수 있다.
                # 최초로 접근한 경우에야 말로, 최소 간선의 수로 접근했다는것을 알 수 있다.
                if visited[i] == 0:
                    # visited[i] = 1로 함으로써 단순히, 방문했다라는 의미만 가지도록 하지 않고
                    # 해당 숫자까지 오는데 start 노드부터 얼마나 많은 관계를 거쳤는지 함께 나타낸다.
                    # 부모 노드인 now 와 새롭게 방문하고자 하는 숫자 노드는 1개의 관계로 이루어져 있어서 +1
                    visited[i] = visited[now] + 1
                    # 방문한 새로운 숫자에서 K까지 계속해서 접근하기 위해 deque에 넣어준다.
                    Q.append(i)

                    # 새롭게 방문한 숫자가 K라면 K가 거쳐온 관계를 출력한다.
                    if i == K:
                        # 1를 빼주는 이유는 최초의 start 노드에 대해서도 +1를 해줬기 때문에, 맨 마지막에는 -1를 해준다.
                        print(visited[K]-1)
                        sys.exit()


