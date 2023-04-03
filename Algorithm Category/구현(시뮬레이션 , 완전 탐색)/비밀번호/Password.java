import java.util.*;

public class Password {

	public int solution(int[] keypad, String password){
		int answer = 0;

        // 1. keypad를 2차원 배열로 만들기
        int[][] keypad2D = new int[3][3];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                keypad2D[i][j] = keypad[index++];
            }
        }

        // 2. 8방향으로 이동할 수 있는 방향 배열 dx,dy 구하기
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
        int d = 0;

    


        // 3. password 배열의 첫 글자가 몇번째 index에 위치하는지 확인
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


        // 4. 해당 index를 기준으로 8번 순회, nx = index + dx[] 를 시도한다. 범위를 벗어나면 continue
        // 찾았으면 해당 위치로 이동하며 최종 count +1;
        int totalTime = 0;
        for (int i = 1; i < password.length(); i++){
            // 각각의 숫자로 도달하는데 걸린 시간
            int count = 0;

            // 다음 위치
            int nx = 0;
            int ny = 0;

            // startX 와 startY 에 위치하는 값이 password의 i번째 값과 같으면 count는 증가하지 않고 continue
            if (keypad2D[startX][startY] == password.charAt(i) - '0'){
                continue;
            }


            // 다음 위치가 범위를 벗어나면 continue
            for(d = 0; d < 8; d++){
                nx = startX + dx[d];
                ny = startY + dy[d];

                // 다음 위치가 범위를 벗어나면 continue
                if(nx < 0 || nx >= 3 || ny < 0 || ny >= 3){
                    continue;
                }

                int as = password.charAt(i) - '0';

                // 다음 위치가 찾는 숫자라면
                if (keypad2D[nx][ny] == as){
                    // 현재 위치를 다음 위치로 바꿔주고
                    startX = nx;
                    startY = ny;

                    // 다음 위치를 찾았으므로 도달하기 까지 걸린 시간 count +1
                    count++;

                    // 다음 위치를 찾았으므로 break 하고, 다음 숫자로 넘어간다.
                    break;
                }
            }// end of inner for

            // for문을 다 순회를 해도 못 찾으면 결국 해당 원소까지 최대 2초가 걸린다.
            if (d == 8){
                count = 2;
                // 그리고 해당 위치까지 start 위치를 바꿔준다.
                for (int a = 0; a < 3; a++){
                    for (int b = 0; b <3; b++){
                        if (keypad2D[a][b] == password.charAt(i) - '0'){
                            startX = a;
                            startY = b;
                        }
                    }
                }
            }

            // 각각의 숫자로 도달하는데 걸린 시간을 더해준다.
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

    

