import java.util.*;

class MakeHomeReview {

	public int solution(int[][] board){


        int[] dy = {-1,0,1,0};
        int[] dx = {0,1,0,-1};

        int ySize = board.length;
        int xSize = board[0].length;

        // 모든 빌딩이 각각 방문한 거리 값의 합을 모두 더하는 배열
        int[][] dist = new int[ySize][xSize];

        Queue<int[]> Q = new LinkedList<>();

        // emptyLand 값을 바꾸면서 3개의 빌딩이 교집합으로 이동할 수 있는 부분만
        // 이동하고자 한다. 전체범위로 이동하지 않으려고 한다.
        int emptyLand = 0;
		
        for(int y = 0; y < ySize; y++) {
            for(int x = 0; x < xSize; x++) {

                if(board[y][x] == 1) {

                    Q.offer(new int[]{y,x});

                    int moveCount = 0;

                    while(!Q.isEmpty()) {
                        int Qsize = Q.size();

                        for(int i = 0; i < Qsize; i++) {
                            int[] cur = Q.poll();

                            for(int k = 0; k < 4; k++) {
                                int ny = cur[0] + dy[k];
                                int nx = cur[1] + dx[k];

                                if(ny >= 0 && ny < ySize && nx >=0 && nx < xSize && board[ny][nx] == emptyLand) {
                                    board[ny][nx]--;
                                    dist[ny][nx] += moveCount + 1;
                                    Q.offer(new int[]{ny,nx});
                                }
                            }
                            

                        }
                        // 자식 노드에 대해 방문 횟수를 기입하기 위해 moveCount를 증가시킨다.
                        moveCount++;
                        
                    }

                    // 특정 빌딩에 대한 BFS를 다 수행하고 나선 다음 빌딩을 위해 emptyLand 값을 감소시킨다.
                    emptyLand--;
                }
                
            }
        }

        int answer = Integer.MAX_VALUE;

        for(int y = 0; y < ySize; y++) {
            for(int x = 0; x < xSize; x++) {
                // 앞전 빌딩이 방문한 좌표값에 대해서만 계속해서 다음 빌딩이
                // 방문하고자 하기에, 그 값중 최소값만 구하면 될 수도 있지만
                // 해당 교집합의 좌표가 모든 빌딩이 방문한 좌표라는 것을 보장할 순 없다.
                // 교집합 좌표라도 특정 빌딩 입장에서는 해당 좌표로 이동조차 못 할 수 있다.
                // && 또한 dist[y][x] > 0 는 안전장치이다. 만약 어떠한 빌딩이라도 교집합으로 몇가지 빌딩이 이동했다면
                // 모든 빌딩이 방문한 좌표값에 대한 dist는 무조건 0보다 크기에 안전장치라고 볼 수 있다.
                if(board[y][x] == emptyLand && dist[y][x] > 0) {
                    answer = Math.min(answer, dist[y][x]);
                }
            }
        }



		return answer == Integer.MAX_VALUE ? -1 : answer;
	}
		
	public static void main(String[] args){
		MakeHomeReview T = new MakeHomeReview();
		System.out.println(T.solution(new int[][]{{1, 0, 2, 0, 1}, {0, 0, 0, 0, 0}, {0, 2, 1, 0, 0}, {2, 0, 0, 2, 2}, {0, 0, 0, 0, 0}}));
		System.out.println(T.solution(new int[][]{{1, 0, 0, 1}, {0, 0, 2, 0}, {0, 0, 1, 0}, {2, 2, 0, 0}}));
		System.out.println(T.solution(new int[][]{{1, 2, 0, 0}, {0, 0, 1, 2}, {0, 2, 0, 0}, {0, 2, 1, 0}}));
		System.out.println(T.solution(new int[][]{{1, 0, 0, 1}, {0, 0, 2, 0}, {0, 0, 1, 0}, {2, 2, 0, 1}}));
	}
}