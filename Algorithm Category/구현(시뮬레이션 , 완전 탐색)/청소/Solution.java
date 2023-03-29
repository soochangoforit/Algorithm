import java.util.*;
class Solution {
	public int[] solution(int[][] board, int k){
		int[] answer = new int[2]; 

		int n = board.length;

        // 상하좌우
        // [x][y] 순서
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};

        // d는 1 index 부터 움직이기 시작 (우측 방향)
        // 현재 위치 x, y
        // 초 count
		int x = 0, y = 0, d = 1, count = 0;

        // k 초가 되는 순간 종료
		while(count < k){
            // 초를 우선적으로 증가
			count++;

            // 증가한 초에 대한 행동 정의
			int nx = x + dx[d];
			int ny = y + dy[d];

            // 장애물 및 범위를 벗어났을 때
			if((nx < 0 || nx >= n || ny < 0 || ny >= n) || board[nx][ny] == 1){

                // 방향 전환
                // dx, dy 배열에서 순환을 할 수 있다.
				d = (d + 1) % 4;

                // 장애물 및 범위를 벗어나 있기 때문에 해당 초만큼은 움직이지 않는다.
				continue;
			}

			x = nx;
			y = ny;
		}
        
        // k가 종료되는 시점에서는 행, 열 순서로 출력
		answer[0] = x;
		answer[1] = y;
		return answer;
	}

	public static void main(String[] args){
		Solution T = new Solution();
		int[][] arr1 = {{0, 0, 0, 0, 0}, 
			{0, 1, 1, 0, 0}, 
			{0, 0, 0, 0, 0}, 
			{1, 0, 1, 0, 1}, 
			{0, 0, 0, 0, 0}};
		System.out.println(Arrays.toString(T.solution(arr1, 10)));
		int[][] arr2 = {{0, 0, 0, 1, 0, 1}, 
			{0, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 0, 0, 1}, 
			{1, 1, 0, 0, 1, 0}, 
			{0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0}};
		System.out.println(Arrays.toString(T.solution(arr2, 20)));
		int[][] arr3 = {{0, 0, 1, 0, 0}, 
			{0, 1, 0, 0, 0}, 
			{0, 0, 0, 0, 0}, 
			{1, 0, 0, 0, 1}, 
			{0, 0, 0, 0, 0}};
		System.out.println(Arrays.toString(T.solution(arr3, 25)));
		
	}
}