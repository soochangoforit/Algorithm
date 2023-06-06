import java.util.*;

class BreakWallReview {
    public int solution(int[][] board) {
        int answer = 0;

        int[] dy = {-1,0,1,0};
        int[] dx = {0,1,0,-1};
        
        int ySize = board.length;
        int xSize = board[0].length;

        // (�߿�) �ִ� ������ �ʱ�ȭ 
        int[][] cost = new int[ySize][xSize];
        for(int i = 0; i < ySize; i++) {
            Arrays.fill(cost[i], Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2] - b[2]);
        pq.offer(new int[]{0,0,0});
        cost[0][0] = 0;

        while(!pq.isEmpty()) {
            int pqSize = pq.size();

            for(int i = 0; i < pqSize; i++) {
                int[] cur = pq.poll();

                int cur_y = cur[0];
                int cur_x = cur[1];
                int cur_moveCount = cur[2];

                // (�߿�) �ַ�, ����ġ�� �켱������ ���� ������ �켱 ���� ���� ���鿡 ���� ���� �湮�� �Ǿ �� continue�� ���� ������.
                if(cur_moveCount > cost[cur_y][cur_x]) continue;

                for(int k = 0; k < 4; k++) {
                    int ny = cur_y + dy[k];
                    int nx = cur_x + dx[k];

                    // (�߿�) ������ ����� ��� continue �Ѵ�.
                    if(ny < 0 || ny >= ySize || nx < 0 || nx >= xSize) continue;

                    // ���� ������ ���
                    if (board[ny][nx] == 1){
                        // (�߿�) �̵��ϰ��� �ϴ� ��ǥ�� ����, �ּ� �Ÿ� ���� ����Ǵ� ��츸 ����
                        // (�߿�) �̸� ����, �ڽ��� ������� �ߺ����� �ǵ��ư��� ���� �� �ִ�. (�湮 �迭�� ������ �ʾƵ� �Ǵ� ����)
                        if (cur_moveCount + 1 < cost[ny][nx]) {
                            cost[ny][nx] = cur_moveCount + 1;
                            pq.offer(new int[]{ny, nx, cur_moveCount + 1});
                        }
                    }
                    // ���� ������ �ʴ� ���
                    else {
                        // (�߿�) �̵��ϰ��� �ϴ� ��ǥ�� ����, �ּ� �Ÿ� ���� ����Ǵ� ��츸 ����
                        // (�߿�) �̸� ����, �ڽ��� ������� �ߺ����� �ǵ��ư��� ���� �� �ִ�. (�湮 �迭�� ������ �ʾƵ� �Ǵ� ����)
                        if (cur_moveCount < cost[ny][nx]) {
                            cost[ny][nx] = cur_moveCount;
                            pq.offer(new int[]{ny, nx, cur_moveCount});
                        }
                    }
                }
            }
        }



        return cost[ySize - 1][xSize - 1]; 
    }

	public static void main(String[] args){
		BreakWallReview T = new BreakWallReview();
		System.out.println(T.solution(new int[][]{{0, 1, 1, 0}, {1, 0, 0, 1}, {0, 1, 0, 0}}));	
		System.out.println(T.solution(new int[][]{{0, 1, 1, 0},{1, 1, 0, 1},{0, 0, 1, 0}, {0, 1, 1, 1}, {0, 1, 1, 0}}));	
		System.out.println(T.solution(new int[][]{{0, 1, 1, 0, 1, 1},{0, 1, 1, 1, 1, 1},{1, 0, 0, 0, 1, 1}, {1, 1, 0, 1, 1, 1}, {1, 1, 0, 1, 1, 0}, {1, 0, 0, 1, 1, 1}, {1, 1, 1, 1, 1, 0}}));	
		System.out.println(T.solution(new int[][]{{0, 1, 1, 0, 1, 1, 1}, {1, 1, 1, 0, 1, 1, 1}, {1, 0, 0, 0, 0, 1, 1}, {1, 1, 1, 0, 1, 1, 1}, {1, 1, 1, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 0}}));
		System.out.println(T.solution(new int[][]{{0, 0, 1, 0, 1, 1, 1},{1, 1, 0, 0, 1, 1, 1},{1, 1, 0, 1, 0, 1, 1}, {0, 0, 1, 0, 1, 1, 1}, {1, 0, 1, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 1, 1}, {1, 0, 0, 1, 1, 1, 1}, {1, 1, 0, 0, 1, 1, 1}, {1, 1, 0, 1, 1, 1, 0}}));	
	}
}