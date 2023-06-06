import java.util.*;

class ChangeDir {
    public int solution(int[][] board) {
        
        // �̵��� �ǹ��ϴ� ���ڿ� ���� index�� ���߱� ���� (���� ���� �۾� �߿�)
        // 1. ����, 2. ����, 3. ��, 4. ��
        int[] dy = {0, 0, 1, -1};
        int[] dx = {1, -1, 0, 0};

        int ySize = board.length;
        int xSize = board[0].length;

        // �ִ� �Ÿ� ���̺��� �ʱ⿡ ���� ū ������ �ʱ�ȭ
        int[][] cost = new int[ySize][xSize];
        for(int y = 0; y < ySize; y++) {
            Arrays.fill(cost[y], Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.offer(new int[]{0,0,0});
        cost[0][0] = 0;

        while(!pq.isEmpty()) {
            int pqSize = pq.size();

            for(int i = 0; i < pqSize; i++) {
                int[] cur = pq.poll();

                int cur_y = cur[0];
                int cur_x = cur[1];
                int cur_moveCount = cur[2];
                int cur_dir = board[cur_y][cur_x] - 1;

                // ������ cur ��忡 ���� �̹� �湮�� ���� �ִٸ� continue
                if(cur_moveCount > cost[cur_y][cur_x]) continue;

                // 4���� ���⿡ ���� Ž��
                for(int k = 0; k < 4; k++) {
                    int ny = cur_y + dy[k];
                    int nx = cur_x + dx[k];

                    // ���������� ����� ��� continue (�߿�)
                    if(ny < 0 || ny >= ySize || nx < 0 || nx >= xSize) continue;

                    // ������ �ϴ� k ������, ���� cur ǥ�õ� ����� ��ġ�ϸ� �̵��ϰ��� �ϴ� ���⿡�� �ƹ��� ����ġ �ο� X
                    // ������, ������ �ϴ� ������ ��ġ�Ͽ� ����ġ�� ���� �ʴ��� �̹� �ٸ� ���⼺�� ����,,,,
                    if (cur_dir == k) {
                        // if �� (�߿�)
                        if (cur_moveCount < cost[ny][nx]) {
                            cost[ny][nx] = cur_moveCount;
                            pq.offer(new int[]{ny,nx, cur_moveCount});
                        }
                    }
                    else {
                        // if �� (�߿�)
                        if (cur_moveCount + 1 < cost[ny][nx]) {
                            cost[ny][nx] = cur_moveCount + 1;
                            pq.offer(new int[]{ny, nx, cur[2] + 1});
                        }
                    }
                }
            }
        }

        return cost[ySize - 1][xSize - 1];
    }

	public static void main(String[] args){
		ChangeDir T = new ChangeDir();
		System.out.println(T.solution(new int[][]{{3, 1, 3}, {1, 4, 2}, {4, 2, 3}}));
		System.out.println(T.solution(new int[][]{{3, 2, 1, 3}, {1, 1, 4, 2}, {3, 4, 2, 1}, {1, 2, 2, 4}}));
		System.out.println(T.solution(new int[][]{{3, 2, 1, 3, 1, 2}, {2, 1, 1, 1, 4, 2}, {2, 2, 2, 1, 2, 2}, {1, 3, 3, 4, 4, 4}, {1, 2, 2, 3, 3, 4}}));
		System.out.println(T.solution(new int[][]{{3, 2, 1, 3, 1, 2, 2, 2}, {2, 1, 1, 1, 4, 2, 1, 1}, {2, 2, 2, 1, 2, 2, 3, 4}, {1, 3, 3, 4, 4, 4, 3, 1}, {1, 2, 2, 3, 3, 4, 3, 4}, {1, 2, 2, 3, 3, 1, 1, 1}}));
		System.out.println(T.solution(new int[][]{{1, 2, 3, 2, 1, 3, 1, 2, 2, 2}, {1, 2, 2, 1, 1, 1, 4, 2, 1, 1}, {3, 2, 2, 2, 2, 1, 2, 2, 3, 4}, {3, 3, 1, 3, 3, 4, 4, 4, 3, 1}, {1, 1, 1, 2, 2, 3, 3, 4, 3, 4}, {1, 1, 1, 2, 2, 3, 3, 1, 1, 1}}));
	}
}