import java.util.*;

class BreakWall {
    public int solution(int[][] board) {
        
        // 상하좌우 이동 방향
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int ySize = board.length;
        int xSize = board[0].length;

        // 특정 좌표에서 시작해서 모든 노드까지 대한 최단 거리를 저장한 테이블
        int[][] cost = new int[ySize][xSize];

        // 최단 거리 테이블을 최대값으로 초기화
        for(int y = 0; y < ySize; y++) {
            Arrays.fill(cost[y], Integer.MAX_VALUE);
        }

        // 접근할 수 있는 노드 중, 가중치가 낮은 값을 먼저 방문하고자 한다. (오름차순)
        // {방문 가능한 좌표, 도달하기 위한 가중치}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2] - b[2]);
        pq.add(new int[]{0,0,0});
        cost[0][0] = 0;

        
        while(!pq.isEmpty()) {
            int Qsize = pq.size();

            // pq에 들어가 있어 도달할 수 있는 후보 간선들에 의해 노드 거리가
            // 최대 거리 Integer.MAX_VALUE에서 특정한 값으로 초기화 되는 경우
            // 해당 노드까지 걸리는 최소 거리이다. (더 이상 바뀌지 않는다)
            // pq에서 모든 간선 후보들을 다루고 있고, 특정 노드로 도달하기 위한
            // 최소 가중치에 대해서만 poll 하고 있기 때문에.
            for(int i = 0; i < Qsize; i++) {
                int[] cur = pq.poll();

                int curY = cur[0];
                int curX = cur[1];
                int curMoveCount = cur[2];

                // 현재 노드에 대해 Integer.MAX_VALUE에서 다른 값으로
                // 초기화 하는 경우를 제외하면 이미 최소값이 현재 노드에 대한 전체 Table에 Update 되어 있다.
                // Integer.MAX_VALUE말고 다른 값으로 이미 초기화 되어있다면 해당 값이 최소값이라서
                // 더 이상 pq에 들어간 해당 간선은 더 이상 고려하지 않아도 좋다 (이미 거쳐갔기에)
                if(curMoveCount > cost[curY][curX]) continue;

                for(int k = 0; k < 4; k++) {
                    int ny = cur[0] + dy[k];
                    int nx = cur[1] + dx[k];

                    // 범위에서 벗어나면 continue
                    if(ny < 0 || ny >= ySize || nx < 0 || nx >= xSize) continue;

                    // next 좌표가 아무것도 없는 경우
                    if(board[ny][nx] == 0 && cur[2] < cost[ny][nx]){
                        cost[ny][nx] = cur[2];
                        pq.offer(new int[]{ny, nx, cost[ny][nx]});
                    }
                    // next 좌표에 벽이 있는 경우 (+1 가중치를 더 해서 비교)
                    else if(board[ny][nx] == 1 && cur[2] + 1 < cost[ny][nx]) {
                        cost[ny][nx] = cur[2] + 1;
                        pq.offer(new int[]{ny, nx, cost[ny][nx]});
                    }
                }
            }
        }

        // 마지막 좌표에 도달하기 위한 결과 출력
        return cost[ySize -1][xSize -1]; 
    }

	public static void main(String[] args){
		BreakWall T = new BreakWall();
		System.out.println(T.solution(new int[][]{{0, 1, 1, 0}, {1, 0, 0, 1}, {0, 1, 0, 0}}));	
		System.out.println(T.solution(new int[][]{{0, 1, 1, 0},{1, 1, 0, 1},{0, 0, 1, 0}, {0, 1, 1, 1}, {0, 1, 1, 0}}));	
		System.out.println(T.solution(new int[][]{{0, 1, 1, 0, 1, 1},{0, 1, 1, 1, 1, 1},{1, 0, 0, 0, 1, 1}, {1, 1, 0, 1, 1, 1}, {1, 1, 0, 1, 1, 0}, {1, 0, 0, 1, 1, 1}, {1, 1, 1, 1, 1, 0}}));	
		System.out.println(T.solution(new int[][]{{0, 1, 1, 0, 1, 1, 1}, {1, 1, 1, 0, 1, 1, 1}, {1, 0, 0, 0, 0, 1, 1}, {1, 1, 1, 0, 1, 1, 1}, {1, 1, 1, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 0}}));
		System.out.println(T.solution(new int[][]{{0, 0, 1, 0, 1, 1, 1},{1, 1, 0, 0, 1, 1, 1},{1, 1, 0, 1, 0, 1, 1}, {0, 0, 1, 0, 1, 1, 1}, {1, 0, 1, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 1, 1}, {1, 0, 0, 1, 1, 1, 1}, {1, 1, 0, 0, 1, 1, 1}, {1, 1, 0, 1, 1, 1, 0}}));	
	}
}