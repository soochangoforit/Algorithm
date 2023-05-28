import java.util.*;

class MakingHome {
	public int solution(int[][] board){
		int answer = 0;
        
		// �����¿� �̵� ����
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};

		// ����, ���� ����
		int n = board.length;

		// ������ �������� �ּ� �Ÿ��� �̵��� �Ÿ����� ���� ������ �迭
		int[][] dist = new int[n][n];

		// ������ �������� �湮�� ���� üũ�� �迭
		int[][] ch = new int[n][n];

		// ������ �������� �������� �湮�� ���� üũ�� �迭
		int[][] commonCh = new int[n][n];

		// ������ ����
		int buildingCount = 0;

		Queue<int[]> Q = new LinkedList<>();

		answer = Integer.MAX_VALUE;

		for(int y = 0; y < n; y++){
			for(int x = 0; x < n; x++){
				// �����̸�
				if(board[y][x] == 1){
					// ������ ������ ����, ���Ŀ� ������ ������ŭ Ư�� ��ǥ�� �湮�ߴ��� üũ�� ���� (�׷��� �ǹ̴� �ش� ��ǥ�� ��� �������� �湮�� ���̶�� �ǹ�)
					buildingCount++;

					// ������ ��ǥ�� ť�� �ִ´�.
					Q.offer(new int[]{y, x});

					// ������ ������ �����Ҷ�����, ������ Ƚ���� 0���� �ʱ�ȭ
					int moveCount = 0; 

					while(!Q.isEmpty()){

						moveCount++;

						int len = Q.size();	

						for(int r = 0; r < len; r++){
							int[] cur = Q.poll();

							for(int k = 0; k < 4; k++){
								int next_y = cur[0] + dy[k];
								int next_x = cur[1] + dx[k];

								// ������ ����� �ʰ�, ��(0)���� ������ �� �ְ�, �湮�� ���� ���ٸ�
								if(next_y >= 0 && next_y < n && next_x >= 0 && next_x < n && board[next_y][next_x] == 0 && ch[next_y][next_x] == 0){
									// �ش� ���� �������� �湮�� �ߴٰ� üũ
									ch[next_y][next_x] = 1;
									// ��� ������ �湮�� ��ǥ���� ���� ���������� �����ش�. ���� ������ ������ŭ �湮�� ��ǥ���� ���ؼ��� �ּҰ��� ���ϰ��� �Ѵ�.
									commonCh[next_y][next_x]++;
									// �ش� ��ǥ���� �����ϱ� ���� ������ Ƚ���� �����ش�.
									dist[next_y][next_x] += moveCount;
									Q.offer(new int[]{next_y, next_x});
								}

							}
						}
					}

					// �ϳ��� ������ ���� BFS�� �����ٸ�, ���� ������ ���� �湮�� ��ǥ���� �ʱ�ȭ�Ѵ�.
					ch = new int[n][n];
				
				}	
				
			}
		}

		// [��� ������ �湮�� ��ǥ]�� ���ؼ� �ּҰ��� ���Ѵ�.
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				// �� (0) ��ǥ�� ���ؼ��� ����� �ؾ��ϰ� ������ ������ŭ �湮�� ��ǥ���� ���ؼ��� �ּҰ��� ���ϰ��� �Ѵ�.
				if(board[i][j] == 0 && commonCh[i][j] == buildingCount){
					answer = Math.min(answer, dist[i][j]);
				}
			}
		}

		// ����, ��� ������ �湮�� ��ǥ���� ���ؼ� �ּҰ��� ������ ���ߴٸ�, ������ answer�� Integer.MAX_VALUE�� ���̴�. �̶��� -1�� �����Ѵ�.
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