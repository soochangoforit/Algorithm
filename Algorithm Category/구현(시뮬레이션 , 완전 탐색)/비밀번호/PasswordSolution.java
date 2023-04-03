import java.util.*;

public class PasswordSolution {

	public int solution(int[] keypad, String password){
        int answer = 0;

        int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

        // 2차원 배열로 만들기
        int[][] pad = new int[3][3];

        // 입력 받은 keypad를 2차원 배열로 만들기
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                pad[i][j] = keypad[i*3 + j];
            }
        }


        // 소요될 시간을 저장할 배열 (초기에 2로 초기화)
        // 최대 소요되는 시간은 2초이다. (소요될 시간의 경우의 수 : 0, 1, 2)
        int[][] dist = new int[10][10];

        for(int i = 0; i < 10; i++){
            Arrays.fill(dist[i], 2);
        }

        // 자기 자신으로 가는 비용은 0으로 초기화
        for(int i = 0; i < 10; i++){
            dist[i][i] = 0;
        } 

        // 8방향으로 이동할 수 있는 경우 1로 초기화
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                // 시작 위치 from
                int from = pad[i][j];

                // 8방향으로 이동할 수 있는 경우 1로 초기화
                for(int k = 0; k < 8; k++){
                    // 범위 안에 속하는 경우만
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if(nx >= 0 && nx < 3 && ny >= 0 && ny < 3){
                        int to = pad[nx][ny];
                        // 8방향을 통해서 이동할 수 있음으로 1로 초기화
                        dist[from][to] = 1;
                    }
                }
            }
        }

        // 본격적으로 password를 순회하면서 dist 배열을 이용하여 소요 시간을 계산한다.
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

    

