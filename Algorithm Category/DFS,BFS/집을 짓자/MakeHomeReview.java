import java.util.*;

class MakeHomeReview {

	public int solution(int[][] board){


        int[] dy = {-1,0,1,0};
        int[] dx = {0,1,0,-1};

        int ySize = board.length;
        int xSize = board[0].length;

        // ��� ������ ���� �湮�� �Ÿ� ���� ���� ��� ���ϴ� �迭
        int[][] dist = new int[ySize][xSize];

        Queue<int[]> Q = new LinkedList<>();

        // emptyLand ���� �ٲٸ鼭 3���� ������ ���������� �̵��� �� �ִ� �κи�
        // �̵��ϰ��� �Ѵ�. ��ü������ �̵����� �������� �Ѵ�.
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
                        // �ڽ� ��忡 ���� �湮 Ƚ���� �����ϱ� ���� moveCount�� ������Ų��.
                        moveCount++;
                        
                    }

                    // Ư�� ������ ���� BFS�� �� �����ϰ� ���� ���� ������ ���� emptyLand ���� ���ҽ�Ų��.
                    emptyLand--;
                }
                
            }
        }

        int answer = Integer.MAX_VALUE;

        for(int y = 0; y < ySize; y++) {
            for(int x = 0; x < xSize; x++) {
                // ���� ������ �湮�� ��ǥ���� ���ؼ��� ����ؼ� ���� ������
                // �湮�ϰ��� �ϱ⿡, �� ���� �ּҰ��� ���ϸ� �� ���� ������
                // �ش� �������� ��ǥ�� ��� ������ �湮�� ��ǥ��� ���� ������ �� ����.
                // ������ ��ǥ�� Ư�� ���� ���忡���� �ش� ��ǥ�� �̵����� �� �� �� �ִ�.
                // && ���� dist[y][x] > 0 �� ������ġ�̴�. ���� ��� �����̶� ���������� ��� ������ �̵��ߴٸ�
                // ��� ������ �湮�� ��ǥ���� ���� dist�� ������ 0���� ũ�⿡ ������ġ��� �� �� �ִ�.
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