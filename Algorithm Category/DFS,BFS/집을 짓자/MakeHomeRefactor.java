import java.util.*;

class MakeHomeRefactor {
	public int solution(int[][] board){
		int answer = 0;
        
		// �����¿� �̵� ����
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};

		// ����, ���� ����
		int n = board.length;

		// ������ �������� �ּ� �Ÿ��� �̵��� �Ÿ����� ���� ������ �迭
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

                                //  board[next_y][next_x] == emptyLand �� ���� ������ �湮�� ��ǥ������ �湮�ϰ��� �Ѵ�. (������)
								if(next_y >= 0 && next_y < n && next_x >= 0 && next_x < n && board[next_y][next_x] == emptyLand){
                                    // ó���� �� �� �ִ� �Ÿ��� -1�� �����ش�. �̷��� ���� emptyLand�� ���� ���ƾ� �Ѵ�.
									board[next_y][next_x]--;
                                    // �ִ� �Ÿ��� ������ �����Ǿ ����ȴ�.
									dist[next_y][next_x] += moveCount;
									Q.offer(new int[]{next_y, next_x});
                                    // ���� �ٸ� �������� �����ؼ� �ִ� �Ÿ��� �̵��� Ƚ���� answer�� ���� (ù��° �õ������� answer�� ���� 1�� �ʱ�ȭ)
                                    // �� ���� ���������� answer�� �� �ٽ� MAX_VALUE�� �ʱ�ȭ �ǰ�, ���� �湮 Ƚ���� ���ؼ� ���ݱ��� �ִ� �Ÿ��� �̵��� Ƚ���� ����ȴ�.
                                    // ��, �̷��� �ڵ�� ������ ������ ��ȸ�ϸ鼭 ������ �������� �ִ� �Ÿ��� �̵��� Ƚ���� �����ؼ� �����ϴ� �� ���߿� �ּҰ��� ���Ѵ�.
                                    // �׸��� �ռ� if ������ �ռ� �ܰ迡 �湮�� ��ǥ�� ���ؼ��� �湮�� �ϰ��� �ϱ⿡, 
                                    // ���� ���������� ���� �������� �湮�� ��ǥ�� ���ؼ��� �湮�� �ϰ� �ȴ�. �̷��� �Ǹ� ��� �������� �湮�� ���� ��ǥ ����
                                    // �ּҰ��� ã�� �� �ִ�.
									answer = Math.min(answer, dist[next_y][next_x]);
								}
							}
						}
					}

                    // emptyLand�� board�� �Ҵ��ϴ� ���� ���� ���� �����ؾ� �Ѵ�.
                    // ������ �ؾ�����, �� �������� �湮�� ��ǥ�� ���ؼ��� �湮�� �ϰ� �ȴ�.
					emptyLand--;
				}	
			}
		}


        // ������ ��� ������ ���������� �湮�� ���� �ִٸ� �װ��� ��ȯ�ϰ�
        // 5�� ���� �� 4�������� ��� �湮�ؼ� �������� ���ߴµ� 5��° �������� if���� ���� ���ͼ� answer�� MAX_VALUE�� �ʱ�ȭ�� ������
        // 5��° �������� ������ ���� ������ �湮�� emptyLand�� ������ answer�� MAX_VALUE�� �����ȴ�. �̷��� ��쿡�� -1�� ��ȯ�Ѵ�.
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