import java.util.*;

class ChangeDir {
    public int solution(int[][] board) {
        
        // 이동을 의미하는 숫자와 같은 index로 맞추기 위해 (방향 설정 작업 중요)
        // 1. 우측, 2. 좌측, 3. 밑, 4. 상
        int[] dy = {0, 0, 1, -1};
        int[] dx = {1, -1, 0, 0};

        int ySize = board.length;
        int xSize = board[0].length;

        // 최단 거리 테이블은 초기에 제일 큰 값으로 초기화
        int[][] cost = new int[ySize][xSize];
        for(int y = 0; y < ySize; y++) {
            Arrays.fill(cost[y], Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.offer(new int[]{0,0,0});
        cost[0][0] = 0;

        while(!pq.isEmpty()) {
            int pqSize = pq.size();

            for(int i = 0; i < pqSize; i++) {
                int[] cur = pq.poll();

                int cur_y = cur[0];
                int cur_x = cur[1];
                int cur_moveCount = cur[2];
                int cur_dir = board[cur_y][cur_x] - 1;

                // 도착지 cur 노드에 대해 이미 방문한 적이 있다면 continue
                if(cur_moveCount > cost[cur_y][cur_x]) continue;

                // 4가지 방향에 대해 탐색
                for(int k = 0; k < 4; k++) {
                    int ny = cur_y + dy[k];
                    int nx = cur_x + dx[k];

                    // 범위값에서 벗어나는 경우 continue (중요)
                    if(ny < 0 || ny >= ySize || nx < 0 || nx >= xSize) continue;

                    // 가고자 하는 k 방향이, 현재 cur 표시된 방향과 일치하면 이동하고자 하는 방향에는 아무런 가중치 부여 X
                    // 하지만, 가고자 하는 방향이 일치하여 가중치가 들지 않더라도 이미 다른 방향성에 의해,,,,
                    if (cur_dir == k) {
                        // if 문 (중요)
                        if (cur_moveCount < cost[ny][nx]) {
                            cost[ny][nx] = cur_moveCount;
                            pq.offer(new int[]{ny,nx, cur_moveCount});
                        }
                    }
                    else {
                        // if 문 (중요)
                        if (cur_moveCount + 1 < cost[ny][nx]) {
                            cost[ny][nx] = cur_moveCount + 1;
                            pq.offer(new int[]{ny, nx, cur[2] + 1});
                        }
                    }
                }
            }
        }

        return cost[ySize - 1][xSize - 1];
    }

	public static void main(String[] args){
		ChangeDir T = new ChangeDir();
		System.out.println(T.solution(new int[][]{{3, 1, 3}, {1, 4, 2}, {4, 2, 3}}));
		System.out.println(T.solution(new int[][]{{3, 2, 1, 3}, {1, 1, 4, 2}, {3, 4, 2, 1}, {1, 2, 2, 4}}));
		System.out.println(T.solution(new int[][]{{3, 2, 1, 3, 1, 2}, {2, 1, 1, 1, 4, 2}, {2, 2, 2, 1, 2, 2}, {1, 3, 3, 4, 4, 4}, {1, 2, 2, 3, 3, 4}}));
		System.out.println(T.solution(new int[][]{{3, 2, 1, 3, 1, 2, 2, 2}, {2, 1, 1, 1, 4, 2, 1, 1}, {2, 2, 2, 1, 2, 2, 3, 4}, {1, 3, 3, 4, 4, 4, 3, 1}, {1, 2, 2, 3, 3, 4, 3, 4}, {1, 2, 2, 3, 3, 1, 1, 1}}));
		System.out.println(T.solution(new int[][]{{1, 2, 3, 2, 1, 3, 1, 2, 2, 2}, {1, 2, 2, 1, 1, 1, 4, 2, 1, 1}, {3, 2, 2, 2, 2, 1, 2, 2, 3, 4}, {3, 3, 1, 3, 3, 4, 4, 4, 3, 1}, {1, 1, 1, 2, 2, 3, 3, 4, 3, 4}, {1, 1, 1, 2, 2, 3, 3, 1, 1, 1}}));
	}
}