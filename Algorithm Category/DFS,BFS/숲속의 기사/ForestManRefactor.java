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

                    // ���� Ư�� ��ǥ�� Q�� �־�����, �ش� ��ǥ�� �������� BFS ���� �ʿ�
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

                                // ������ ����� �ʰ�, ���� ����, �湮�� ���� ���ٸ�
                                if(ny >= 0 && ny < ySize && nx >= 0 && nx < xSize && board[ny][nx] != 1 && ch[ny][nx] == 0) {
                                    ch[ny][nx] = 1;
                                    Q.offer(new int[]{ny, nx});
                                    dist[ny][nx] += moveCount + 1;
                                }
                            }
                        }

                        // ���� cur �� ������ �ִ� ���� �ڽ� ������ ��� Q�� �־���⿡
                        // moveCount�� ���� ����, ���� ���� �ڽ� ������ �������� �ش� ���� �� cur�� �ȴ�.
                        moveCount++;
                    }

                    // Ư�� 2�� 3�� �������� BFS�� �����ߴٸ� 
                    // �ֻ���� for���� ���ؼ� ���� board�� ��ġ ������ �̵��Ѵ�.
                    // �̵��ϸ鼭 �� �ٽ� 2�� 3�� ������ �Ǹ�, �湮 �迭 �ʱ�ȭ + �ʱ� ��ǥ Q ���� + �ʱ� ��ǥ �湮 ó��
                    // �ش� ��ǥ�� �������� �� �ٽ� BFS�� �����ϸ鼭 dist�� ���� �����Ѵ�.

                }
            }
        }

        // ��ü board ũ�� ��ŭ dist �迭�� ��ȸ�Ѵ�.
        for(int y = 0; y < ySize; y++) {
            for(int x = 0; x < xSize; x++) {
                // ��ȸ�� �ϸ鼭 ����Ⱑ �����ϴ� ��ǥ�� dist ���� �ѹ��� �湮���� ���� 0�� �����ϰ�
                if(board[y][x] == 4 && dist[y][x] != 0) {
                    // ���� �ּ� ���� ���� dist ���� answer�� �־��ش�.
                    // �ش� ����� ��ġ�� �ּҰ��� �� ���� => ����� => ��� �� �ּ� �Ÿ��� �ǹ��Ѵ�.
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