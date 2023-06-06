import java.util.*;

class BreakWallReview {
    public int solution(int[][] board) {
        int answer = 0;

        int[] dy = {-1,0,1,0};
        int[] dx = {0,1,0,-1};
        
        int ySize = board.length;
        int xSize = board[0].length;

        // (중요) 최대 값으로 초기화 
        int[][] cost = new int[ySize][xSize];
        for(int i = 0; i < ySize; i++) {
            Arrays.fill(cost[i], Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2] - b[2]);
        pq.offer(new int[]{0,0,0});
        cost[0][0] = 0;

        while(!pq.isEmpty()) {
            int pqSize = pq.size();

            for(int i = 0; i < pqSize; i++) {
                int[] cur = pq.poll();

                int cur_y = cur[0];
                int cur_x = cur[1];
                int cur_moveCount = cur[2];

                // (중요) 주로, 가중치의 우선순위가 낮은 노드들이 우선 순위 높은 노드들에 의해 먼저 방문이 되어서 이 continue로 빠져 나간다.
                if(cur_moveCount > cost[cur_y][cur_x]) continue;

                for(int k = 0; k < 4; k++) {
                    int ny = cur_y + dy[k];
                    int nx = cur_x + dx[k];

                    // (중요) 범위를 벗어나는 경우 continue 한다.
                    if(ny < 0 || ny >= ySize || nx < 0 || nx >= xSize) continue;

                    // 벽을 만나는 경우
                    if (board[ny][nx] == 1){
                        // (중요) 이동하고자 하는 좌표에 대해, 최소 거리 값이 보장되는 경우만 접근
                        // (중요) 이를 통해, 자신의 출발지로 중복으로 되돌아가지 않을 수 있다. (방문 배열을 만들지 않아도 되는 이유)
                        if (cur_moveCount + 1 < cost[ny][nx]) {
                            cost[ny][nx] = cur_moveCount + 1;
                            pq.offer(new int[]{ny, nx, cur_moveCount + 1});
                        }
                    }
                    // 벽을 만나지 않는 경우
                    else {
                        // (중요) 이동하고자 하는 좌표에 대해, 최소 거리 값이 보장되는 경우만 접근
                        // (중요) 이를 통해, 자신의 출발지로 중복으로 되돌아가지 않을 수 있다. (방문 배열을 만들지 않아도 되는 이유)
                        if (cur_moveCount < cost[ny][nx]) {
                            cost[ny][nx] = cur_moveCount;
                            pq.offer(new int[]{ny, nx, cur_moveCount});
                        }
                    }
                }
            }
        }



        return cost[ySize - 1][xSize - 1]; 
    }

	public static void main(String[] args){
		BreakWallReview T = new BreakWallReview();
		System.out.println(T.solution(new int[][]{{0, 1, 1, 0}, {1, 0, 0, 1}, {0, 1, 0, 0}}));	
		System.out.println(T.solution(new int[][]{{0, 1, 1, 0},{1, 1, 0, 1},{0, 0, 1, 0}, {0, 1, 1, 1}, {0, 1, 1, 0}}));	
		System.out.println(T.solution(new int[][]{{0, 1, 1, 0, 1, 1},{0, 1, 1, 1, 1, 1},{1, 0, 0, 0, 1, 1}, {1, 1, 0, 1, 1, 1}, {1, 1, 0, 1, 1, 0}, {1, 0, 0, 1, 1, 1}, {1, 1, 1, 1, 1, 0}}));	
		System.out.println(T.solution(new int[][]{{0, 1, 1, 0, 1, 1, 1}, {1, 1, 1, 0, 1, 1, 1}, {1, 0, 0, 0, 0, 1, 1}, {1, 1, 1, 0, 1, 1, 1}, {1, 1, 1, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 0}}));
		System.out.println(T.solution(new int[][]{{0, 0, 1, 0, 1, 1, 1},{1, 1, 0, 0, 1, 1, 1},{1, 1, 0, 1, 0, 1, 1}, {0, 0, 1, 0, 1, 1, 1}, {1, 0, 1, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 1, 1}, {1, 0, 0, 1, 1, 1, 1}, {1, 1, 0, 0, 1, 1, 1}, {1, 1, 0, 1, 1, 1, 0}}));	
	}
}