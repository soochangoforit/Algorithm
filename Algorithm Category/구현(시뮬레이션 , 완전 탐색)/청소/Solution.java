import java.util.*;
class Solution {
	public int[] solution(int[][] board, int k){
		int[] answer = new int[2]; 

		int n = board.length;

        // �����¿�
        // [x][y] ����
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};

        // d�� 1 index ���� �����̱� ���� (���� ����)
        // ���� ��ġ x, y
        // �� count
		int x = 0, y = 0, d = 1, count = 0;

        // k �ʰ� �Ǵ� ���� ����
		while(count < k){
            // �ʸ� �켱������ ����
			count++;

            // ������ �ʿ� ���� �ൿ ����
			int nx = x + dx[d];
			int ny = y + dy[d];

            // ��ֹ� �� ������ ����� ��
			if((nx < 0 || nx >= n || ny < 0 || ny >= n) || board[nx][ny] == 1){

                // ���� ��ȯ
                // dx, dy �迭���� ��ȯ�� �� �� �ִ�.
				d = (d + 1) % 4;

                // ��ֹ� �� ������ ��� �ֱ� ������ �ش� �ʸ�ŭ�� �������� �ʴ´�.
				continue;
			}

			x = nx;
			y = ny;
		}
        
        // k�� ����Ǵ� ���������� ��, �� ������ ���
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