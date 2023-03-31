
import java.util.*;

class Seat {

	public int[] solution(int c, int r, int k){
		int[] answer = new int[2];

        // answer �迭�� ��� 0���� �ʱ�ȭ
        Arrays.fill(answer, 0);

        // ���� r, ���� c 2�� �迭�� �����ϰ� 0���� ��� �ʱ�ȭ
        int[][] seat = new int[c][r];
        for (int i = 0; i < c; i++) {
            Arrays.fill(seat[i], 0);
        }
    
        // ���� ��ġ index
        int x = 0;
        int y = 0;

        // �̵� ������ ���� ��ǥ ���� (��, ��, ��, ��)
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        // �ʱ� ���� ����
        int d = 0;

        // ��ȸ ����
        for (int i = 0; i < r * c; i++) {
            // �¼� ��ȣ�� 1���� ���� �� �Ҵ�
            seat[x][y] = i + 1;

            // k ��° �¼��� ã������
            if (seat[x][y] == k) {
                // ��ǥ�� answer �迭�� ����, �츰 x,y ��ǥ�� ���ؼ� 0���� �����߱� ������ ���߿� +1�� �� �Ѵ�.
                answer[0] = x + 1;
                answer[1] = y + 1;
                break;
            }

            // ���� ��ǥ
            int nextX = x + dx[d];
            int nextY = y + dy[d];

            // ���� ��ǥ�� ������ ����ų� �̹� ä���� ������
            if (nextX < 0 || nextX >= c || nextY < 0 || nextY >= r || seat[nextX][nextY] != 0) {
                // ������ �ٲ۴�.
                d = (d + 1) % 4;

                // ������ �ٲ� ���·� ���� �̵� ��ǥ�� ���Ѵ�.
                nextX = x + dx[d];
                nextY = y + dy[d];
            }

            // ���� ��ǥ�� �̵��� ���������� �̵��Ѵ�.
            x = nextX;
            y = nextY;

        }

		return answer;
	}

	public static void main(String[] args){
		Seat T = new Seat();
		System.out.println(Arrays.toString(T.solution(6, 5, 12)));	
		System.out.println(Arrays.toString(T.solution(6, 5, 20)));
		System.out.println(Arrays.toString(T.solution(6, 5, 30)));
		System.out.println(Arrays.toString(T.solution(6, 5, 31)));
	}

}
