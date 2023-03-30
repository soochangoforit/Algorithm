import java.util.*;

class Clean {
    
	public int[] solution(int[][] board, int k){
		int[] answer = new int[2]; 

        // board 의 크기
        int size = board.length;
       
        // 방향을 나타내는 좌표
        // 우, 하, 좌, 상
        // [y][x] 순서
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        int xindex = 0;
        int yindex = 0;

        // 현재 위치 [y][x]
        int curX = 0;
        int curY = 0;

        // k 시간만큼 움직여야 한다.
        for (int time = k; time > 0; time--) {
            
            // 우선 임의로 위치 이동하고자 한다.
            int nextX = curX + dx[xindex];
            int nextY = curY + dy[yindex];

            // 장애물이 있거나 벽면에 부딪히면
            if ((nextX < 0 || nextY < 0 || nextX >= size || nextY >= size)  || board[nextY][nextX] == 1) {

                // 방향 전환 index 에 값 할당
                if (xindex == 3 && yindex == 3) {
                    xindex = 0;
                    yindex = 0;
                } else {
                    xindex += 1;
                    yindex += 1;
                }
                
                // 해당 초는 단순히 방향 전환만 하고 움직이지 않는다.
                continue;
            }

            // 장애물이 없다면
            else {
                curX = curX + dx[xindex];
                curY = curY + dy[yindex];
            }

        }

        
        answer[0] = curY;
        answer[1] = curX;

		// 최종적으로 행, 열 순서로 출력
		return answer;
	}

	public static void main(String[] args){
		Clean T = new Clean();

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
