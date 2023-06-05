import java.util.*;

class BreakWall {
    public int solution(int[][] board) {
        
        // �����¿� �̵� ����
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int ySize = board.length;
        int xSize = board[0].length;

        // Ư�� ��ǥ���� �����ؼ� ��� ������ ���� �ִ� �Ÿ��� ������ ���̺�
        int[][] cost = new int[ySize][xSize];

        // �ִ� �Ÿ� ���̺��� �ִ밪���� �ʱ�ȭ
        for(int y = 0; y < ySize; y++) {
            Arrays.fill(cost[y], Integer.MAX_VALUE);
        }

        // ������ �� �ִ� ��� ��, ����ġ�� ���� ���� ���� �湮�ϰ��� �Ѵ�. (��������)
        // {�湮 ������ ��ǥ, �����ϱ� ���� ����ġ}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2] - b[2]);
        pq.add(new int[]{0,0,0});
        cost[0][0] = 0;

        
        while(!pq.isEmpty()) {
            int Qsize = pq.size();

            // pq�� �� �־� ������ �� �ִ� �ĺ� �����鿡 ���� ��� �Ÿ���
            // �ִ� �Ÿ� Integer.MAX_VALUE���� Ư���� ������ �ʱ�ȭ �Ǵ� ���
            // �ش� ������ �ɸ��� �ּ� �Ÿ��̴�. (�� �̻� �ٲ��� �ʴ´�)
            // pq���� ��� ���� �ĺ����� �ٷ�� �ְ�, Ư�� ���� �����ϱ� ����
            // �ּ� ����ġ�� ���ؼ��� poll �ϰ� �ֱ� ������.
            for(int i = 0; i < Qsize; i++) {
                int[] cur = pq.poll();

                int curY = cur[0];
                int curX = cur[1];
                int curMoveCount = cur[2];

                // ���� ��忡 ���� Integer.MAX_VALUE���� �ٸ� ������
                // �ʱ�ȭ �ϴ� ��츦 �����ϸ� �̹� �ּҰ��� ���� ��忡 ���� ��ü Table�� Update �Ǿ� �ִ�.
                // Integer.MAX_VALUE���� �ٸ� ������ �̹� �ʱ�ȭ �Ǿ��ִٸ� �ش� ���� �ּҰ��̶�
                // �� �̻� pq�� �� �ش� ������ �� �̻� ������� �ʾƵ� ���� (�̹� ���İ��⿡)
                if(curMoveCount > cost[curY][curX]) continue;

                for(int k = 0; k < 4; k++) {
                    int ny = cur[0] + dy[k];
                    int nx = cur[1] + dx[k];

                    // �������� ����� continue
                    if(ny < 0 || ny >= ySize || nx < 0 || nx >= xSize) continue;

                    // next ��ǥ�� �ƹ��͵� ���� ���
                    if(board[ny][nx] == 0 && cur[2] < cost[ny][nx]){
                        cost[ny][nx] = cur[2];
                        pq.offer(new int[]{ny, nx, cost[ny][nx]});
                    }
                    // next ��ǥ�� ���� �ִ� ��� (+1 ����ġ�� �� �ؼ� ��)
                    else if(board[ny][nx] == 1 && cur[2] + 1 < cost[ny][nx]) {
                        cost[ny][nx] = cur[2] + 1;
                        pq.offer(new int[]{ny, nx, cost[ny][nx]});
                    }
                }
            }
        }

        // ������ ��ǥ�� �����ϱ� ���� ��� ���
        return cost[ySize -1][xSize -1]; 
    }

	public static void main(String[] args){
		BreakWall T = new BreakWall();
		System.out.println(T.solution(new int[][]{{0, 1, 1, 0}, {1, 0, 0, 1}, {0, 1, 0, 0}}));	
		System.out.println(T.solution(new int[][]{{0, 1, 1, 0},{1, 1, 0, 1},{0, 0, 1, 0}, {0, 1, 1, 1}, {0, 1, 1, 0}}));	
		System.out.println(T.solution(new int[][]{{0, 1, 1, 0, 1, 1},{0, 1, 1, 1, 1, 1},{1, 0, 0, 0, 1, 1}, {1, 1, 0, 1, 1, 1}, {1, 1, 0, 1, 1, 0}, {1, 0, 0, 1, 1, 1}, {1, 1, 1, 1, 1, 0}}));	
		System.out.println(T.solution(new int[][]{{0, 1, 1, 0, 1, 1, 1}, {1, 1, 1, 0, 1, 1, 1}, {1, 0, 0, 0, 0, 1, 1}, {1, 1, 1, 0, 1, 1, 1}, {1, 1, 1, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 0}}));
		System.out.println(T.solution(new int[][]{{0, 0, 1, 0, 1, 1, 1},{1, 1, 0, 0, 1, 1, 1},{1, 1, 0, 1, 0, 1, 1}, {0, 0, 1, 0, 1, 1, 1}, {1, 0, 1, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 1, 1}, {1, 0, 0, 1, 1, 1, 1}, {1, 1, 0, 0, 1, 1, 1}, {1, 1, 0, 1, 1, 1, 0}}));	
	}
}