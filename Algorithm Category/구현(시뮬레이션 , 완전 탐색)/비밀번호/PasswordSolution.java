import java.util.*;

public class PasswordSolution {

	public int solution(int[] keypad, String password){
        int answer = 0;

        int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

        // 2���� �迭�� �����
        int[][] pad = new int[3][3];

        // �Է� ���� keypad�� 2���� �迭�� �����
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                pad[i][j] = keypad[i*3 + j];
            }
        }


        // �ҿ�� �ð��� ������ �迭 (�ʱ⿡ 2�� �ʱ�ȭ)
        // �ִ� �ҿ�Ǵ� �ð��� 2���̴�. (�ҿ�� �ð��� ����� �� : 0, 1, 2)
        int[][] dist = new int[10][10];

        for(int i = 0; i < 10; i++){
            Arrays.fill(dist[i], 2);
        }

        // �ڱ� �ڽ����� ���� ����� 0���� �ʱ�ȭ
        for(int i = 0; i < 10; i++){
            dist[i][i] = 0;
        } 

        // 8�������� �̵��� �� �ִ� ��� 1�� �ʱ�ȭ
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                // ���� ��ġ from
                int from = pad[i][j];

                // 8�������� �̵��� �� �ִ� ��� 1�� �ʱ�ȭ
                for(int k = 0; k < 8; k++){
                    // ���� �ȿ� ���ϴ� ��츸
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if(nx >= 0 && nx < 3 && ny >= 0 && ny < 3){
                        int to = pad[nx][ny];
                        // 8������ ���ؼ� �̵��� �� �������� 1�� �ʱ�ȭ
                        dist[from][to] = 1;
                    }
                }
            }
        }

        // ���������� password�� ��ȸ�ϸ鼭 dist �迭�� �̿��Ͽ� �ҿ� �ð��� ����Ѵ�.
        for(int i = 1; i < password.length(); i++){

            int from = (int) password.charAt(i-1) - '0';
            int to = (int) password.charAt(i) - '0';

            answer += dist[from][to];
        }
        return answer;
	}

	public static void main(String[] args){
		PasswordSolution T = new PasswordSolution();
        System.out.println(T.solution(new int[]{2, 5, 3, 7, 1, 6, 4, 9, 8}, "7596218"));	
		System.out.println(T.solution(new int[]{1, 5, 7, 3, 2, 8, 9, 4, 6}, "63855526592"));
		System.out.println(T.solution(new int[]{2, 9, 3, 7, 8, 6, 4, 5, 1}, "323254677"));
		System.out.println(T.solution(new int[]{1, 6, 7, 3, 8, 9, 4, 5, 2}, "3337772122"));
	}
}

    

