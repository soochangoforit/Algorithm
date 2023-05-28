import java.util.*;

class MakingHome {
	public int solution(int[][] board){
		int answer = 0;
        
		// 상하좌우 이동 방향
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};

		// 가로, 세로 길이
		int n = board.length;

		// 각각의 빌딩에서 최소 거리로 이도한 거리들의 합을 저장할 배열
		int[][] dist = new int[n][n];

		// 각각의 빌딩에서 방문한 곳을 체크할 배열
		int[][] ch = new int[n][n];

		// 각각의 빌딩에서 공통으로 방문한 곳을 체크할 배열
		int[][] commonCh = new int[n][n];

		// 빌딩의 개수
		int buildingCount = 0;

		Queue<int[]> Q = new LinkedList<>();

		answer = Integer.MAX_VALUE;

		for(int y = 0; y < n; y++){
			for(int x = 0; x < n; x++){
				// 빌딩이면
				if(board[y][x] == 1){
					// 빌딩의 개수를 증가, 추후에 빌딩의 개수만큼 특정 좌표를 방문했는지 체크할 것임 (그러한 의미는 해당 좌표는 모든 빌딩에서 방문한 곳이라는 의미)
					buildingCount++;

					// 빌딩의 좌표를 큐에 넣는다.
					Q.offer(new int[]{y, x});

					// 각각의 빌딩을 시작할때마다, 움직인 횟수를 0으로 초기화
					int moveCount = 0; 

					while(!Q.isEmpty()){

						moveCount++;

						int len = Q.size();	

						for(int r = 0; r < len; r++){
							int[] cur = Q.poll();

							for(int k = 0; k < 4; k++){
								int next_y = cur[0] + dy[k];
								int next_x = cur[1] + dx[k];

								// 범위를 벗어나지 않고, 빈땅(0)으로 움직일 수 있고, 방문한 적이 없다면
								if(next_y >= 0 && next_y < n && next_x >= 0 && next_x < n && board[next_y][next_x] == 0 && ch[next_y][next_x] == 0){
									// 해당 빌딩 기준으로 방문을 했다고 체크
									ch[next_y][next_x] = 1;
									// 모든 빌딩이 방문한 좌표값에 값을 지속적으로 더해준다. 추후 빌딩의 개수만큼 방문한 좌표값에 대해서만 최소값을 구하고자 한다.
									commonCh[next_y][next_x]++;
									// 해당 좌표까지 도달하기 위해 움직인 횟수를 더해준다.
									dist[next_y][next_x] += moveCount;
									Q.offer(new int[]{next_y, next_x});
								}

							}
						}
					}

					// 하나의 빌딩에 대한 BFS가 끝났다면, 다음 빌딩을 위해 방문한 좌표값을 초기화한다.
					ch = new int[n][n];
				
				}	
				
			}
		}

		// [모든 빌딩이 방문한 좌표]에 대해서 최소값을 구한다.
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				// 빈땅 (0) 좌표에 대해서만 고려를 해야하고 빌디의 개수만큼 방문한 좌표값에 대해서만 최소값을 구하고자 한다.
				if(board[i][j] == 0 && commonCh[i][j] == buildingCount){
					answer = Math.min(answer, dist[i][j]);
				}
			}
		}

		// 만약, 모든 빌딩이 방문한 좌표값에 대해서 최소값을 구하지 못했다면, 여전히 answer는 Integer.MAX_VALUE일 것이다. 이때는 -1을 리턴한다.
		return answer == Integer.MAX_VALUE ? -1 : answer;
	}
		
	public static void main(String[] args){
		MakingHome T = new MakingHome();
		System.out.println(T.solution(new int[][]{{1, 0, 2, 0, 1}, {0, 0, 0, 0, 0}, {0, 2, 1, 0, 0}, {2, 0, 0, 2, 2}, {0, 0, 0, 0, 0}}));
		System.out.println(T.solution(new int[][]{{1, 0, 0, 1}, {0, 0, 2, 0}, {0, 0, 1, 0}, {2, 2, 0, 0}}));
		System.out.println(T.solution(new int[][]{{1, 2, 0, 0}, {0, 0, 1, 2}, {0, 2, 0, 0}, {0, 2, 1, 0}}));
		System.out.println(T.solution(new int[][]{{1, 0, 0, 1}, {0, 0, 2, 0}, {0, 0, 1, 0}, {2, 2, 0, 1}}));
	}
}