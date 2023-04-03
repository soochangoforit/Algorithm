import java.util.*;

public class Password {

	public int solution(int[] keypad, String password){
		int answer = 0;

        // 1. keypad�� 2���� �迭�� �����
        int[][] keypad2D = new int[3][3];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                keypad2D[i][j] = keypad[index++];
            }
        }

        // 2. 8�������� �̵��� �� �ִ� ���� �迭 dx,dy ���ϱ�
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
        int d = 0;

    


        // 3. password �迭�� ù ���ڰ� ���° index�� ��ġ�ϴ��� Ȯ��
        int startX = 0;
        int startY = 0;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j <3; j++){
                if (keypad2D[i][j] == password.charAt(0) - '0'){
                    startX = i;
                    startY = j;
                }
            }
        }


        // 4. �ش� index�� �������� 8�� ��ȸ, nx = index + dx[] �� �õ��Ѵ�. ������ ����� continue
        // ã������ �ش� ��ġ�� �̵��ϸ� ���� count +1;
        int totalTime = 0;
        for (int i = 1; i < password.length(); i++){
            // ������ ���ڷ� �����ϴµ� �ɸ� �ð�
            int count = 0;

            // ���� ��ġ
            int nx = 0;
            int ny = 0;

            // startX �� startY �� ��ġ�ϴ� ���� password�� i��° ���� ������ count�� �������� �ʰ� continue
            if (keypad2D[startX][startY] == password.charAt(i) - '0'){
                continue;
            }


            // ���� ��ġ�� ������ ����� continue
            for(d = 0; d < 8; d++){
                nx = startX + dx[d];
                ny = startY + dy[d];

                // ���� ��ġ�� ������ ����� continue
                if(nx < 0 || nx >= 3 || ny < 0 || ny >= 3){
                    continue;
                }

                int as = password.charAt(i) - '0';

                // ���� ��ġ�� ã�� ���ڶ��
                if (keypad2D[nx][ny] == as){
                    // ���� ��ġ�� ���� ��ġ�� �ٲ��ְ�
                    startX = nx;
                    startY = ny;

                    // ���� ��ġ�� ã�����Ƿ� �����ϱ� ���� �ɸ� �ð� count +1
                    count++;

                    // ���� ��ġ�� ã�����Ƿ� break �ϰ�, ���� ���ڷ� �Ѿ��.
                    break;
                }
            }// end of inner for

            // for���� �� ��ȸ�� �ص� �� ã���� �ᱹ �ش� ���ұ��� �ִ� 2�ʰ� �ɸ���.
            if (d == 8){
                count = 2;
                // �׸��� �ش� ��ġ���� start ��ġ�� �ٲ��ش�.
                for (int a = 0; a < 3; a++){
                    for (int b = 0; b <3; b++){
                        if (keypad2D[a][b] == password.charAt(i) - '0'){
                            startX = a;
                            startY = b;
                        }
                    }
                }
            }

            // ������ ���ڷ� �����ϴµ� �ɸ� �ð��� �����ش�.
            totalTime += count;


        }

        answer = totalTime;
        
		return answer;
	}

	public static void main(String[] args){
		Password T = new Password();
        System.out.println(T.solution(new int[]{2, 5, 3, 7, 1, 6, 4, 9, 8}, "7596218"));	
		System.out.println(T.solution(new int[]{1, 5, 7, 3, 2, 8, 9, 4, 6}, "63855526592"));
		System.out.println(T.solution(new int[]{2, 9, 3, 7, 8, 6, 4, 5, 1}, "323254677"));
		System.out.println(T.solution(new int[]{1, 6, 7, 3, 8, 9, 4, 5, 2}, "3337772122"));
	}
}

    

