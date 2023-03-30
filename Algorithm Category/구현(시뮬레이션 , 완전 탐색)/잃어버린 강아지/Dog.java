import java.util.*;

class Dog {
	public int solution(int[][] board){
		int answer = 0;

        // �̵� ������ ���� ��ǥ ����
        // ��, ��, ��, ��
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        // �̵� �迭�� �̵��� index : d
        int humanD = 0;
        int dogD = 0;

        // �̵� �� �� �ҿ�Ǵ� �ð�(��)
        int time = 0;

        // �̵� ������ �ִ� �ð�(��)
        int maxTime = 10000;

        // board �� �ִ� ������
        int size = board.length;

        int humanX = 0;
        int humanY = 0;

        int dogX = 0;
        int dogY = 0;

        // ����, ������ ��ġ ã��
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

        // ���������� ã�� ����
        while (time < maxTime) {
            // �켱 1�� �ҿ�
            time++;

            // ������ �̵��� �� �ִ� ���� ��ǥ
            int nextHumanX = humanX + dx[humanD];
            int nextHumanY = humanY + dy[humanD];

            // �������� �̵��� �� �ִ� ���� ��ǥ
            int nextDogX = dogX + dx[dogD];
            int nextDogY = dogY + dy[dogD];

            // ������ �̵��� �� �ִ� ���� ��ǥ�� ������ ����ų� �����̸�
            if(nextHumanX < 0 || nextHumanX >= size || nextHumanY < 0 || nextHumanY >= size || board[nextHumanX][nextHumanY] == 1) {
                // ���θ��� �ֱ� ������ ȸ���� �ؾ� �Ѵ�.
                // ������ ������ ���� 4���� ������ �迭���� ��ȯ�Ѵ�.
                humanD = (humanD + 1) % 4;
            }else {
                // ������ �̵��� �� �ִ� ���� ��ǥ�� �̵�
                humanX = nextHumanX;
                humanY = nextHumanY;
            }

            // �������� �̵��� �� �ִ� ���� ��ǥ�� ������ ����ų� �����̸�
            if(nextDogX < 0 || nextDogX >= size || nextDogY < 0 || nextDogY >= size || board[nextDogX][nextDogY] == 1) {
                // ���θ��� �ֱ� ������ ȸ���� �ؾ� �Ѵ�.
                // ������ ������ ���� 4���� ������ �迭���� ��ȯ�Ѵ�.
                dogD = (dogD + 1) % 4;
            }else {
                // �������� �̵��� �� �ִ� ���� ��ǥ�� �̵�
                dogX = nextDogX;
                dogY = nextDogY;
            }

            // ������ �������� �����ٸ�
            if(humanX == dogX && humanY == dogY) {
                // ������ �������� ��Ҵ�.
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