
# 세로, 가로 길이가 주어진다.
# 얼음판이 0이면 틀이 있고, 1이면 칸막이가 있다.
# 만들 수 있는 얼음의 개수는?
N, M = map(int, input().split())

# 얼큼칸을 입력 받는다.
graph = []
for i in range(N):
    graph.append(list(map(int, input())))


def dfs(y,x):
    # 범위를 벗어나지 않는지 확인
    if y <= -1 or y >= N or x <= -1 or x >= M:
        return False
    
    # 아직 아무도 도달하지 않았으면서, 얼음칸이 있는 경우
    if graph[y][x] == 0:
        # 방문 처리를 해주기 위해서 1로 초기화
        # 1로 초기화가 되었다는 의미는 얼음칸에 물을 넣을 수 있다는 이야기
        graph[y][x] = 1
        dfs(y-1,x)
        dfs(y,x-1)
        dfs(y+1, x)
        dfs(y, x+1)
        return True
    
    # 이미 방문하여 계산된 경우, 혹은 애초에 칸막이인 경우
    return False


result = 0
for i in range(N):
    for j in range(M):
        if dfs(i,j) == True:
            result += 1
        
print(result)