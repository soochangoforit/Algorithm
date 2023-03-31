import java.util.*;

public class SeatSolution {

    public int[] solution(int c, int r, int k){
		int[] answer = new int[2];

        // k가 c*r보다 터무늬 없이 크면 0,0을 리턴 (이른 반환)
        if ( k > c* r) {
            return new int[] {0,0};
        }

        // 배열 선언 시, 기본 값으로 0이 할당되기 때문에 따로 초기화 할 필요 없음
		int[][] seat = new int[c][r];

        // 이동할 수 있는 방향에 대한 좌표 설정 (상, 우, 하, 좌)
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};

        // 시작 위치 index
		int x = 0, y = 0;
        
        // 1번 좌석부터 시작
        int count = 1;

        // 방향 이동할 시작 방향 index
        int d = 1;
		
        // k 번째 좌석이 되는 순간, while문을 빠져나온다.
        while(count < k){

            // 다음 좌표
			int nx = x + dx[d];
            int ny = y + dy[d];

            // 다음 좌표가 범위를 벗어나거나 이미 채워져 있으면
			if(nx < 0 || nx >= c || ny < 0 || ny >= r || seat[nx][ny] != 0){
                // 방향 전환
                d = (d+1) % 4;
                // 다음 좌표에 대한 방향 전환만 하고 이동은 하지 않는다.
                continue;
			}

            // 앉을 수 있는 자리면, 현재 좌석 번호를 할당하고, 다음 좌표로 이동
            seat[x][y] = count;
		
            // 다음 손님이 앉을 좌석 번호 증가
            count++;

            // 다음 좌표로 이동
            x = nx;
            y = ny;
		
		}

        // k 번째 좌석에 대한 좌표를 answer 배열에 할당, 좌표는 1부터 시작하므로 인덱스 위치에서 +1
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
