import sys
input = sys.stdin.readline

def dfs(y, x, cnt, sum):
    global answer

    # 종료조건1) 탐색을 계속 진행하여도 최댓값에 못 미치는 경우
    # 블록을 더 붙이더라도 [이전에 구한 좌표값들의 총합 중 최댓값(answer)]을 넘지 못하는 경우
    # 블록을 붙이는데 모두 보드 중에서 최대값으로 구성되어 있다 가정했는데, 이전 도형들의 최댓값보다 작은 경우
    # 아무리 보드 중에서 최댓값이 온다고 해서 붙이더라도, 이전의 도형들의 최댓값을 넘기지 못 하기 때문에, 탐색할 의미가 없다.
    if sum + max_val*(4-cnt) <= answer:
        return

    # 종료조건2) 블록 4개를 모두 활용한 경우
    if cnt == 4:
        answer = max(answer, sum)
        return

    # 상하좌우 방향으로 블록 이어 붙여 나가기
    for dy, dx in movable:
        next_y = y + dy # 새로운 y 좌표
        next_x = x + dx # 새로운 x 좌표

        # 새로운 좌표가 유효한 범위 내 있고 탐색이력이 없는 경우
        if 0 <= next_y < N and 0 <= next_x < M and not visited[next_y][next_x]:

            # 일반적인 방문을 4번 수행하는 도형들에 대해 먼저 계산을 하지 않고, 특수항 도형 (ㅗ, ㅏ, ㅜ, ㅓ)에 대해 먼저 계산을 한다.
            # 2번째 블록 연결 후 'ㅏ','ㅓ','ㅗ','ㅜ' 만들기
            if cnt == 2:
                # 방문할 좌표에 대해서 방문처리
                visited[next_y][next_x] = True
                # cnt를 2개를 가지는 현재 좌표에서 새로운 좌표에서 탐색하지 않고 현재 좌표에서 한번 더 탐색 -> ㅗ,ㅜ,ㅓ,ㅏ 를 만들 수 있다.
                dfs(y, x, cnt+1, sum+board[next_y][next_x])
                # 다음 기준이 되는 좌표도 방문이 가능하게 하기 위해서 방문 초기화
                visited[next_y][next_x] = False

            # 특수항 도형에 대한 방문처리가 끝나고 나서, 일반적인 도형의 방문에 대해서 최대합을 구하면서 탐색한다.
            visited[next_y][next_x] = True
            dfs(next_y, next_x, cnt+1, sum+board[next_y][next_x])
            visited[next_y][next_x] = False

if __name__ == "__main__":
    N, M = map(int, input().split()) # 좌표의 행, 열 개수
    board = [list(map(int, input().split())) for _ in range(N)] # 좌표별 값

    max_val = max(map(max, board)) # 모든 좌표 중 최댓값

    movable = [(-1, 0), (1, 0), (0, -1), (0, 1)] # 좌표 내 상하좌우

    visited = [[False] * M for _ in range(N)] # 탐색여부 확인용

    # 최종 테트로미노에 들어있는 숫자들의 합을 통합 최댓값
    answer = 0 

    for y in range(N):
        for x in range(M):
            visited[y][x] = True # 탐색기록
            dfs(y, x, 1, board[y][x])
            visited[y][x] = False # 탐색기록 제거

    print(answer)