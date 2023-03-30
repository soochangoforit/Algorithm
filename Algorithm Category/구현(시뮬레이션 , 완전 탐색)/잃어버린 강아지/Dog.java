import java.util.*;

class Dog {
	public int solution(int[][] board){
		int answer = 0;

        // 이동 가능한 방향 좌표 설정
        // 상, 우, 하, 좌
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        // 이동 배열을 이동할 index : d
        int humanD = 0;
        int dogD = 0;

        // 이동 할 때 소요되는 시간(분)
        int time = 0;

        // 이동 가능한 최대 시간(분)
        int maxTime = 10000;

        // board 의 최대 사이즈
        int size = board.length;

        int humanX = 0;
        int humanY = 0;

        int dogX = 0;
        int dogY = 0;

        // 현수, 강아지 위치 찾기
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 2) {
                    humanX = i;
                    humanY = j;
                } else if (board[i][j] == 3) {
                    dogX = i;
                    dogY = j;
                }
            }
        }

        // 본격적으로 찾기 시작
        while (time < maxTime) {
            // 우선 1분 소요
            time++;

            // 현수가 이동할 수 있는 다음 좌표
            int nextHumanX = humanX + dx[humanD];
            int nextHumanY = humanY + dy[humanD];

            // 강아지가 이동할 수 있는 다음 좌표
            int nextDogX = dogX + dx[dogD];
            int nextDogY = dogY + dy[dogD];

            // 현수가 이동할 수 있는 다음 좌표가 범위를 벗어나거나 나무이면
            if(nextHumanX < 0 || nextHumanX >= size || nextHumanY < 0 || nextHumanY >= size || board[nextHumanX][nextHumanY] == 1) {
                // 가로막혀 있기 때문에 회전을 해야 한다.
                // 나머지 연산을 통해 4가지 방향을 배열에서 순환한다.
                humanD = (humanD + 1) % 4;
            }else {
                // 현수가 이동할 수 있는 다음 좌표로 이동
                humanX = nextHumanX;
                humanY = nextHumanY;
            }

            // 강아지가 이동할 수 있는 다음 좌표가 범위를 벗어나거나 나무이면
            if(nextDogX < 0 || nextDogX >= size || nextDogY < 0 || nextDogY >= size || board[nextDogX][nextDogY] == 1) {
                // 가로막혀 있기 때문에 회전을 해야 한다.
                // 나머지 연산을 통해 4가지 방향을 배열에서 순환한다.
                dogD = (dogD + 1) % 4;
            }else {
                // 강아지가 이동할 수 있는 다음 좌표로 이동
                dogX = nextDogX;
                dogY = nextDogY;
            }

            // 현수와 강아지가 만났다면
            if(humanX == dogX && humanY == dogY) {
                // 현수가 강아지를 잡았다.
                answer = time;
                break;
            }

        } // end of while


		
		return answer;		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args){
        
		Dog T = new Dog();
		int[][] arr1 = 
           {{0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, 
			{0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 1, 0, 0, 0, 1, 0, 0}, 
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 1, 0, 0, 0, 2, 0, 0}, 
			{1, 0, 0, 0, 0, 0, 1, 0, 0, 0}, 
			{0, 0, 0, 1, 0, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 0, 0, 3, 0, 0, 0, 1}, 
			{0, 0, 0, 1, 0, 1, 0, 0, 0, 0}, 
			{0, 1, 0, 1, 0, 0, 0, 0, 0, 0}}; 
		System.out.println(T.solution(arr1));
		int[][] arr2 = 
           {{1, 0, 0, 0, 1, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, 
			{0, 0, 1, 1, 0, 0, 0, 1, 0, 0}, 
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 1, 0, 1, 0, 0, 0, 0}, 
			{1, 0, 0, 0, 0, 0, 1, 0, 1, 0}, 
			{0, 0, 0, 1, 0, 0, 0, 0, 0, 0}, 
			{0, 0, 1, 0, 0, 0, 0, 0, 2, 1}, 
			{0, 0, 0, 1, 0, 1, 0, 0, 0, 1}, 
			{0, 1, 0, 1, 0, 0, 0, 0, 0, 3}}; 
		System.out.println(T.solution(arr2));
	}
}