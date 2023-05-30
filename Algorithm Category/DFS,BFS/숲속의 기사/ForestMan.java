import java.util.*;

class Solution {

	public int solution(int[][] board){
		int answer = 0;

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int targetNumber = 3;

        int humanY = 0;
        int humanX = 0;


        // board�� ����, ���� ����
        int ySize = board.length;
        int xSize = board[0].length;

        // ����� ��ǥ ã��
        for(int y = 0; y < ySize; y++) {
            for(int x = 0; x < xSize; x++) {
                if(board[y][x] == 2){
                    humanY = y;
                    humanX = x;
                }
            }
        }

        // �ʱ� ��ǥ�� ����� ��ǥ�� �ִ´�. (ó������ ����⸦ ���� ���� �ʾƼ� 0���� �־��ش�.)
        Queue<int[]> Q = new LinkedList<>();
        Q.offer(new int[]{humanY, humanX, 0});
        int moveCount = 0;


        while(!Q.isEmpty()){
            int Qsize = Q.size();

            for(int i = 0; i < Qsize; i++) {
                int[] cur = Q.poll();
                
                for(int dir = 0; dir < 4; dir++) {
                    int ny = cur[0] + dy[dir];
                    int nx = cur[1] + dx[dir];
                    int hasFruit = cur[2];

                    // �켱 ������ �� �ִ� ��ǥ�� �̵��� �ϰ��� �Ѵ�.
                    if(ny >= 0 && ny < ySize && nx >= 0 && nx < xSize && board[ny][nx] != 1) {

                        // cur ��ǥ �������� ���� ny, nx�� ����⸦ ������ �ְ�, ��縦 ������ ���
                        if(board[ny][nx] == 3 && hasFruit > 0) {
                            // ����� cur ����������, ���� ��ǥ�� ny nx �� ���� ������ �����ϱ⿡ +1�� ���ش�.
                            answer = moveCount + 1;
                            return answer;
                        }

                        // ���� ��ǥ�� ���� �ƴϰ�, ����⸦ ���� �� �ִ� ��� 
                        if(board[ny][nx] == 4) {
                            // ����⸦ �����⿡, hasFruit�� 1�� �ٲ��ش�.
                            Q.offer(new int[]{ny, nx, 1});
                            // ����⸦ ����������, �ش� ��ǥ�� 0���� �ٲ��ش�.
                            // ���� 0���� �ٲ����� ������ ���ѷ����� ���� �� �ִ�.
                            // ����⸦ �������� ���� ���� �ö󰬴ٰ� �ٽ� �����Դµ�, �� ���� ����Ⱑ �־� �� �ö󰡴� ���ѷ����� ���� �� �ִ�.
                            // ���� ���� ª�� �Ÿ��� ����⸦ ã�Ƽ� ��縦 ������ �ϴµ� �ƹ��� �湮 �迭�� Ȱ���ϰ� ���� �ʴ�.
                            // �湮 �迭�� Ȱ������ �ʾƾ���, ����⸦ ��� ���� ������ ���� ���ƿ��� ��ν� ������ �� �ִ�.
                            // �׸��� ����⸦ ��� ���� ��~ ���Ƽ� 2�� ã�Ƽ� ��縦 ã�� ��찡 �и� �߻�������,
                            // �̹� �ϳ��� ����⸦ ��� ��縦 �湮�ϴ� ��� �̸� return�� ���ؼ� �ٷ� ª�� �Ÿ��� ã�� �� �ִ�.
                            board[ny][nx] = 0;
                        } else {
                            // ���� ��ǥ�� ��絵 �ƴϰ�, ����⵵ ���� ��� �̵��� �� �� �ְ�, ���� ����⸦ ������ �ִ� ���·� �����Ѵ�.
                            Q.offer(new int[]{ny, nx, hasFruit});
                        }
                    
                    }
                }
            }
            // �ѹ� �̵��� �߱⿡ moveCount�� ���������ش�. root ��忡�� �ڽ� ������ �ش� ++moveCount�� ������ ���� Q���� �̾�����.
            // �̷��� �ϸ� cur �������� if���� ���� return �� ��� �ٷ� return moveCount ���ָ� �ȴ�.
            moveCount++;
        }
		
		return answer;
	}
		
	public static void main(String[] args){
		Solution T = new Solution();
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