
import java.util.*;

class Seat {

	public int[] solution(int c, int r, int k){
		int[] answer = new int[2];

        // answer 배열을 모두 0으로 초기화
        Arrays.fill(answer, 0);

        // 가로 r, 세로 c 2중 배열로 구성하고 0으로 모두 초기화
        int[][] seat = new int[c][r];
        for (int i = 0; i < c; i++) {
            Arrays.fill(seat[i], 0);
        }
    
        // 시작 위치 index
        int x = 0;
        int y = 0;

        // 이동 가능한 방향 좌표 설정 (우, 하, 좌, 상)
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        // 초기 시작 방향
        int d = 0;

        // 순회 시작
        for (int i = 0; i < r * c; i++) {
            // 좌석 번호는 1부터 시작 및 할당
            seat[x][y] = i + 1;

            // k 번째 좌석을 찾았으면
            if (seat[x][y] == k) {
                // 좌표를 answer 배열에 저장, 우린 x,y 좌표에 대해서 0부터 시작했기 때문에 나중에 +1를 더 한다.
                answer[0] = x + 1;
                answer[1] = y + 1;
                break;
            }

            // 다음 좌표
            int nextX = x + dx[d];
            int nextY = y + dy[d];

            // 다음 좌표가 범위를 벗어나거나 이미 채워져 있으면
            if (nextX < 0 || nextX >= c || nextY < 0 || nextY >= r || seat[nextX][nextY] != 0) {
                // 방향을 바꾼다.
                d = (d + 1) % 4;

                // 방향을 바꾼 상태로 다음 이동 좌표를 구한다.
                nextX = x + dx[d];
                nextY = y + dy[d];
            }

            // 다음 좌표로 이동로 실질적으로 이동한다.
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
