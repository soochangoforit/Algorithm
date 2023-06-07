import java.util.*;

class Solution {

	public int solution(int[][] board, int[] s, int[] e){
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};

        int ySize = board.length;
        int xSize = board[0].length;

        // (중요)
        int[][] cost = new int[ySize][xSize];
        for(int y = 0; y < ySize; y++) {
            Arrays.fill(cost[y], Integer.MAX_VALUE);
        }

        // (중요)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2] - b[2]);
        pq.offer(new int[]{s[0], s[1], 0});
        cost[s[0]][s[1]] = 0;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            int cur_y = cur[0];
            int cur_x = cur[1];
            int cur_moveCount = cur[2];

			// (중요)
            if(cur_moveCount > cost[cur_y][cur_x]) continue;

            for(int k = 0; k < 4; k++) {
                int move = 0;
                int temp_ny = cur_y;
                int temp_nx = cur_x;

				// 해당 while 문에서는 벽을 만나기 전까지 계속 이동하는 횟수 move를 찾는데 중점을 둔다.
                while(true) {
                    temp_ny += dy[k];
                    temp_nx += dx[k];
                    if (temp_ny < 0 || temp_ny >= ySize || temp_nx < 0 || temp_nx >= xSize) break;
                    if (board[temp_ny][temp_nx] == 1) break;
                    move++;
                }

                int ny = cur_y + dy[k] * move;
                int nx = cur_x + dx[k] * move;

				// (중요) 도달하기 위한 최종 이동거리가 기존의 최소 이동거리보다 작은 경우에서만 갱신한다.
                if (cur_moveCount + move < cost[ny][nx]){
                    cost[ny][nx] = cur_moveCount + move;
                    pq.offer(new int[]{ny, nx, cur_moveCount + move});
                }
            }
        }

        return cost[e[0]][e[1]] == Integer.MAX_VALUE ? -1 : cost[e[0]][e[1]];
    }




	public static void main(String[] args){
		Solution T = new Solution();
		System.out.println(T.solution(new int[][]{{0, 0, 1, 0, 0, 0}, {0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 1, 0}, {1, 0, 1, 1, 1, 0}, {1, 0, 0, 0, 0, 0}}, new int[]{1, 0}, new int[]{4, 5}));
		System.out.println(T.solution(new int[][]{{0, 0, 1, 0, 0, 0}, {0, 0, 1, 0, 0, 0}, {0, 0, 0, 0, 1, 0}, {1, 0, 1, 1, 1, 0}, {1, 0, 0, 0, 0, 0}}, new int[]{0, 0}, new int[]{4, 2}));
		System.out.println(T.solution(new int[][]{{1, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}}, new int[]{0, 3}, new int[]{4, 2}));
		System.out.println(T.solution(new int[][]{{0, 1, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0}, {0, 1, 1, 0, 1, 1}, {0, 0, 0, 0, 0, 0}}, new int[]{0, 0}, new int[]{4, 5}));
		System.out.println(T.solution(new int[][]{{0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 1, 0, 0, 0, 0, 0}}, new int[]{0, 0}, new int[]{4, 3}));
	}
}