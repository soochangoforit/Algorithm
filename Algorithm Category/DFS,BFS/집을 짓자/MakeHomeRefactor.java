import java.util.*;

class MakeHomeRefactor {
	public int solution(int[][] board){
		int answer = 0;
        
		// 상하좌우 이동 방향
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};

		// 가로, 세로 길이
		int n = board.length;

		// 각각의 빌딩에서 최소 거리로 이도한 거리들의 합을 저장할 배열
		int[][] dist = new int[n][n];

		Queue<int[]> Q = new LinkedList<>();
        int emptyLand = 0;

		for(int y = 0; y < n; y++){
			for(int x = 0; x < n; x++){

				if(board[y][x] == 1){

					answer = Integer.MAX_VALUE;

					Q.offer(new int[]{y, x});

					int moveCount = 0; 

					while(!Q.isEmpty()){

						moveCount++;
						int len = Q.size();	

						for(int r = 0; r < len; r++){
							int[] cur = Q.poll();

							for(int k = 0; k < 4; k++){
								int next_y = cur[0] + dy[k];
								int next_x = cur[1] + dx[k];

                                //  board[next_y][next_x] == emptyLand 는 앞전 빌딩이 방문한 좌표에서만 방문하고자 한다. (교집합)
								if(next_y >= 0 && next_y < n && next_x >= 0 && next_x < n && board[next_y][next_x] == emptyLand){
                                    // 처음에 갈 수 있는 거리를 -1로 낮춰준다. 이러한 값은 emptyLand와 값이 같아야 한다.
									board[next_y][next_x]--;
                                    // 최단 거리의 총합이 누적되어서 저장된다.
									dist[next_y][next_x] += moveCount;
									Q.offer(new int[]{next_y, next_x});
                                    // 서로 다른 빌딩에서 시작해서 최단 거리로 이동한 횟수를 answer에 저장 (첫번째 시도에서는 answer가 거의 1로 초기화)
                                    // 그 다음 빌딩에서는 answer가 또 다시 MAX_VALUE로 초기화 되고, 앞전 방문 횟수와 합해서 지금까지 최단 거리로 이동한 횟수가 저장된다.
                                    // 즉, 이러한 코드는 각각의 빌딩을 순회하면서 앞전의 빌딩에서 최단 거리로 이동한 횟수를 누적해서 저장하는 한 값중에 최소값을 구한다.
                                    // 그리고 앞서 if 문에서 앞서 단계에 방문한 좌표에 대해서만 방문을 하고자 하기에, 
                                    // 다음 빌딩에서는 이전 빌딩에서 방문한 좌표에 대해서만 방문을 하게 된다. 이렇게 되면 모든 빌딩에서 방문한 누적 좌표 값중
                                    // 최소값을 찾을 수 있다.
									answer = Math.min(answer, dist[next_y][next_x]);
								}
							}
						}
					}

                    // emptyLand와 board에 할당하는 값을 서로 같게 유지해야 한다.
                    // 유지를 해야지만, 앞 빌딩에서 방문한 좌표에 대해서만 방문을 하게 된다.
					emptyLand--;
				}	
			}
		}


        // 각각의 모든 빌딩에 누적합으로 방문한 값이 있다면 그것을 반환하고
        // 5개 빌딩 중 4개까지는 모두 방문해서 누적합을 구했는데 5번째 빌딩에서 if문을 통해 들어와서 answer는 MAX_VALUE로 초기화를 했지만
        // 5번째 빌딩에서 주위에 앞전 빌딩이 방문한 emptyLand가 없으면 answer는 MAX_VALUE로 유지된다. 이러한 경우에는 -1을 반환한다.
		return answer == Integer.MAX_VALUE ? -1 : answer;

	}
		
	public static void main(String[] args){
		MakeHomeRefactor T = new MakeHomeRefactor();
		System.out.println(T.solution(new int[][]{{1, 0, 2, 0, 1}, {0, 0, 0, 0, 0}, {0, 2, 1, 0, 0}, {2, 0, 0, 2, 2}, {0, 0, 0, 0, 0}}));
		System.out.println(T.solution(new int[][]{{1, 0, 0, 1}, {0, 0, 2, 0}, {0, 0, 1, 0}, {2, 2, 0, 0}}));
		System.out.println(T.solution(new int[][]{{1, 2, 0, 0}, {0, 0, 1, 2}, {0, 2, 0, 0}, {0, 2, 1, 0}}));
		System.out.println(T.solution(new int[][]{{1, 0, 0, 1}, {0, 0, 2, 0}, {0, 0, 1, 0}, {2, 2, 0, 1}}));
	}
}