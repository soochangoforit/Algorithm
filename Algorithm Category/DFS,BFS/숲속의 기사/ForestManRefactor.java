import java.util.*;

class ForestManRefactor {

	public int solution(int[][] board){

		int answer = Integer.MAX_VALUE;

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int ySize = board.length;
        int xSize = board[0].length;

        int[][] dist = new int[ySize][xSize];

        Queue<int[]> Q = new LinkedList<>();

        for(int y = 0; y < ySize; y++) {
            for(int x = 0; x < xSize; x++) {

                if(board[y][x] == 2 || board[y][x] == 3) {

                    Q.offer(new int[]{y,x});

                    // 이제 특정 좌표를 Q에 넣었으니, 해당 좌표를 기점으로 BFS 수행 필요
                    int[][] ch = new int[ySize][xSize];
                    ch[y][x] = 1;
                    int moveCount = 0;

                    while(!Q.isEmpty()){
                        int Qsize = Q.size();

                        for(int i = 0; i < Qsize; i++) {
                            int[] cur = Q.poll();

                            for(int k = 0; k < 4; k++) {
                                int ny = cur[0] + dy[k];
                                int nx = cur[1] + dx[k];

                                // 범위를 벗어나지 않고, 벽이 없고, 방문한 적이 없다면
                                if(ny >= 0 && ny < ySize && nx >= 0 && nx < xSize && board[ny][nx] != 1 && ch[ny][nx] == 0) {
                                    ch[ny][nx] = 1;
                                    Q.offer(new int[]{ny, nx});
                                    dist[ny][nx] += moveCount + 1;
                                }
                            }
                        }

                        // 현재 cur 가 가지고 있는 예비 자식 노드들은 모두 Q에 넣어줬기에
                        // moveCount를 증가 시켜, 다음 예비 자식 노드들이 꺼내질때 해당 값이 곧 cur이 된다.
                        moveCount++;
                    }

                    // 특정 2와 3을 기준으로 BFS를 수행했다면 
                    // 최상단의 for문을 통해서 다음 board의 위치 값으로 이동한다.
                    // 이동하면서 또 다시 2와 3을 만나게 되면, 방문 배열 초기화 + 초기 좌표 Q 삽입 + 초기 좌표 방문 처리
                    // 해당 좌표를 기점으로 또 다시 BFS를 수행하면서 dist에 값을 누적한다.

                }
            }
        }

        // 전체 board 크기 만큼 dist 배열을 순회한다.
        for(int y = 0; y < ySize; y++) {
            for(int x = 0; x < xSize; x++) {
                // 순회를 하면서 산딸기가 존재하는 좌표에 dist 값이 한번도 방문하지 않은 0을 제외하고
                if(board[y][x] == 4 && dist[y][x] != 0) {
                    // 가장 최소 값을 갖는 dist 값을 answer에 넣어준다.
                    // 해당 산딸기 위치의 최소값이 곧 영희 => 산딸기 => 기사 의 최소 거리를 의미한다.
                    answer = Math.min(answer, dist[y][x]);
                }
            }
        }


        
		
		return answer;
	}
		
	public static void main(String[] args){
		ForestManRefactor T = new ForestManRefactor();
		System.out.println(T.solution(new int[][]{{4, 1, 0, 0, 0, 0, 1, 0},
			{0, 0, 0, 1, 0, 1, 0, 0}, 
			{0, 2, 1, 1, 3, 0, 4, 0},
			{0, 0, 0, 4, 1, 1, 1, 0}}));
		System.out.println(T.solution(new int[][]{{3, 0, 0, 0, 1, 4, 4, 4}, 
			{0, 1, 1, 0, 0, 0, 1, 0}, 
			{0, 1, 4, 0, 1, 0, 0, 0}, 
			{0, 0, 0, 1, 0, 0, 0, 0}, 
			{1, 0, 1, 0, 0, 1, 1, 0}, 
			{4, 0, 0, 0, 1, 0, 0, 0}, 
			{4, 1, 0, 0, 1, 0, 0, 0}, 
			{4, 0, 0, 0, 0, 0, 1, 2}}));
		System.out.println(T.solution(new int[][]{{4, 1, 0, 1, 0}, 
			{0, 1, 0, 1, 0}, 
			{0, 0, 2, 3, 4}, 
			{0, 1, 0, 1, 0}}));
	}
}