import java.util.*;

public class SeatSolution {

    public int[] solution(int c, int r, int k){
		int[] answer = new int[2];

        // k�� c*r���� �͹��� ���� ũ�� 0,0�� ���� (�̸� ��ȯ)
        if ( k > c* r) {
            return new int[] {0,0};
        }

        // �迭 ���� ��, �⺻ ������ 0�� �Ҵ�Ǳ� ������ ���� �ʱ�ȭ �� �ʿ� ����
		int[][] seat = new int[c][r];

        // �̵��� �� �ִ� ���⿡ ���� ��ǥ ���� (��, ��, ��, ��)
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};

        // ���� ��ġ index
		int x = 0, y = 0;
        
        // 1�� �¼����� ����
        int count = 1;

        // ���� �̵��� ���� ���� index
        int d = 1;
		
        // k ��° �¼��� �Ǵ� ����, while���� �������´�.
        while(count < k){

            // ���� ��ǥ
			int nx = x + dx[d];
            int ny = y + dy[d];

            // ���� ��ǥ�� ������ ����ų� �̹� ä���� ������
			if(nx < 0 || nx >= c || ny < 0 || ny >= r || seat[nx][ny] != 0){
                // ���� ��ȯ
                d = (d+1) % 4;
                // ���� ��ǥ�� ���� ���� ��ȯ�� �ϰ� �̵��� ���� �ʴ´�.
                continue;
			}

            // ���� �� �ִ� �ڸ���, ���� �¼� ��ȣ�� �Ҵ��ϰ�, ���� ��ǥ�� �̵�
            seat[x][y] = count;
		
            // ���� �մ��� ���� �¼� ��ȣ ����
            count++;

            // ���� ��ǥ�� �̵�
            x = nx;
            y = ny;
		
		}

        // k ��° �¼��� ���� ��ǥ�� answer �迭�� �Ҵ�, ��ǥ�� 1���� �����ϹǷ� �ε��� ��ġ���� +1
		answer[0] = x + 1;
		answer[1] = y + 1;

		return answer;
	}

    public static void main(String[] args){
		SeatSolution T = new SeatSolution();
		System.out.println(Arrays.toString(T.solution(6, 5, 12)));	
		System.out.println(Arrays.toString(T.solution(6, 5, 20)));
		System.out.println(Arrays.toString(T.solution(6, 5, 30)));
		System.out.println(Arrays.toString(T.solution(6, 5, 31)));
	}



}
