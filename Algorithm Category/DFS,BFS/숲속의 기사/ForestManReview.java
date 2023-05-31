import java.util.*;

class ForestManReview {

	public int solution(int[][] board){

		int[] dy = {-1,0,1,0};
        int[] dx = {0,1,0,-1};

        int ySize = board.length;
        int xSize = board[0].length;

        int[][] dist = new int[ySize][xSize];

        Queue<int[]> Q = new LinkedList<>();

        for(int y = 0; y < ySize; y++) {
            for(int x = 0; x < xSize; x++) {

                // ��� Ȥ�� ���� ������ ���, ����� ���� BFS ����
                // �������� �����ϸ鼭 �̵��ߴ� �ּ� �Ÿ��� dist�� ��� �� ���ش�.
                if(board[y][x] ==  2 || board[y][x] == 3) {

                    int[][] ch = new int[ySize][xSize];

                    Q.offer(new int[]{y,x});
                    ch[y][x] = 1;

                    int moveCount = 0;

                    while(!Q.isEmpty()) {
                        int Qsize = Q.size();

                        for(int i = 0; i < Qsize; i++) {
                            int[] cur = Q.poll();

                            for(int k = 0; k < 4; k++) {
                                int ny = cur[0] + dy[k];
                                int nx = cur[1] + dx[k];

                                if(ny >= 0 && ny < ySize && nx >= 0 && nx < xSize && ch[ny][nx] == 0 && board[ny][nx] != 1) {
                                    ch[ny][nx] = 1;
                                    Q.offer(new int[]{ny,nx});
                                    dist[ny][nx] += moveCount + 1;

                                }

                            }
                        }
                        moveCount++;
                    }
                } // end of if

                // ���� if���� ������, ��� Ȥ�� ���� �ֺ��� �������� ������
                // �ּ����� �Ÿ��� ã�� �� �ִ�.
            } 
        }

        int answer = Integer.MAX_VALUE;

        for(int y = 0; y < ySize; y++) {
            for(int x = 0; x < xSize; x++) {
                if(board[y][x] == 4 && dist[y][x] > 0) {
                    answer = Math.min(answer, dist[y][x]);
                }
            }
        }
		
		return answer;
	}
		
	public static void main(String[] args){
		ForestManReview T = new ForestManReview();
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