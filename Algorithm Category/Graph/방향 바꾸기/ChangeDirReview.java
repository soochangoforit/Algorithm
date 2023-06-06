import java.util.*;

class ChangeDirReview {


    public int solution(int[][] board) {
        
        // board에 적힌 이동을 의미하는 숫자와 , 이동 dy-dx 배열을 같은 index로 맞추기 위해 (방향 설정 작업 중요)
        // 1. 우측, 2. 좌측, 3. 밑, 4. 상
        // 이렇게 맞춰주지 않으면 if문을 총 4번 사용해서 board에 적힌 값이 1일때 우측을 나타내는 dy-dx index를 적용 시켜줘야 한다.
        // 일종의 if문을 줄이기 위한 트릭이라고 할 수 있다.(센스)
        int[] dy = {0, 0, 1, -1};
        int[] dx = {1, -1, 0, 0};

        int ySize = board.length;
        int xSize = board[0].length;

        // (중요) 최단 거리 테이블은 초기에 제일 큰 값으로 초기화
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

                // (중요) 주로, 가중치의 우선순위가 낮은 노드들이 우선 순위 높은 노드들에 의해 먼저 방문이 되어서 이 continue로 빠져 나간다.
                if(cur_moveCount > cost[cur_y][cur_x]) continue;

                for(int k = 0; k < 4; k++){
                    int ny = cur_y + dy[k];
                    int nx = cur_x + dx[k];

                    // (중요)
                    if (ny < 0 || ny >= ySize || nx < 0 || nx >= xSize) continue;

                    // 격자 움직일 필요가 없는 경우
                    if(cur_dir == k) {
                        // (중요) 이동하고자 하는 좌표에 대해, 최소 거리 값이 보장되는 경우만 접근
                        // (중요) 이를 통해, 자신의 출발지로 중복으로 되돌아가지 않을 수 있다. (방문 배열을 만들지 않아도 되는 이유)
                        if (cur_moveCount < cost[ny][nx]) {
                            cost[ny][nx] = cur_moveCount;
                            pq.offer(new int[]{ny, nx, cur_moveCount});
                        }
                    }
                    // 격자를 움직일 필요가 있는 경우
                    else {
                        // (중요) 이동하고자 하는 좌표에 대해, 최소 거리 값이 보장되는 경우만 접근
                        // (중요) 이를 통해, 자신의 출발지로 중복으로 되돌아가지 않을 수 있다. (방문 배열을 만들지 않아도 되는 이유)
                        if(cur_moveCount + 1 < cost[ny][nx]) {
                            cost[ny][nx] = cur_moveCount + 1;
                            pq.offer(new int[]{ny, nx, cur_moveCount + 1});
                        }
                    }
                }
            }
        }

        return cost[ySize - 1][xSize - 1];
    }

	public static void main(String[] args){
		ChangeDirReview T = new ChangeDirReview();
		System.out.println(T.solution(new int[][]{{3, 1, 3}, {1, 4, 2}, {4, 2, 3}}));
		System.out.println(T.solution(new int[][]{{3, 2, 1, 3}, {1, 1, 4, 2}, {3, 4, 2, 1}, {1, 2, 2, 4}}));
		System.out.println(T.solution(new int[][]{{3, 2, 1, 3, 1, 2}, {2, 1, 1, 1, 4, 2}, {2, 2, 2, 1, 2, 2}, {1, 3, 3, 4, 4, 4}, {1, 2, 2, 3, 3, 4}}));
		System.out.println(T.solution(new int[][]{{3, 2, 1, 3, 1, 2, 2, 2}, {2, 1, 1, 1, 4, 2, 1, 1}, {2, 2, 2, 1, 2, 2, 3, 4}, {1, 3, 3, 4, 4, 4, 3, 1}, {1, 2, 2, 3, 3, 4, 3, 4}, {1, 2, 2, 3, 3, 1, 1, 1}}));
		System.out.println(T.solution(new int[][]{{1, 2, 3, 2, 1, 3, 1, 2, 2, 2}, {1, 2, 2, 1, 1, 1, 4, 2, 1, 1}, {3, 2, 2, 2, 2, 1, 2, 2, 3, 4}, {3, 3, 1, 3, 3, 4, 4, 4, 3, 1}, {1, 1, 1, 2, 2, 3, 3, 4, 3, 4}, {1, 1, 1, 2, 2, 3, 3, 1, 1, 1}}));
	}
}