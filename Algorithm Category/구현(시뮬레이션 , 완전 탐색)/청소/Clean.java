import java.util.*;

class Clean {
    
	public int[] solution(int[][] board, int k){
		int[] answer = new int[2]; 

        // board �� ũ��
        int size = board.length;
       
        // ������ ��Ÿ���� ��ǥ
        // ��, ��, ��, ��
        // [y][x] ����
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        int xindex = 0;
        int yindex = 0;

        // ���� ��ġ [y][x]
        int curX = 0;
        int curY = 0;

        // k �ð���ŭ �������� �Ѵ�.
        for (int time = k; time > 0; time--) {
            
            // �켱 ���Ƿ� ��ġ �̵��ϰ��� �Ѵ�.
            int nextX = curX + dx[xindex];
            int nextY = curY + dy[yindex];

            // ��ֹ��� �ְų� ���鿡 �ε�����
            if ((nextX < 0 || nextY < 0 || nextX >= size || nextY >= size)  || board[nextY][nextX] == 1) {

                // ���� ��ȯ index �� �� �Ҵ�
                if (xindex == 3 && yindex == 3) {
                    xindex = 0;
                    yindex = 0;
                } else {
                    xindex += 1;
                    yindex += 1;
                }
                
                // �ش� �ʴ� �ܼ��� ���� ��ȯ�� �ϰ� �������� �ʴ´�.
                continue;
            }

            // ��ֹ��� ���ٸ�
            else {
                curX = curX + dx[xindex];
                curY = curY + dy[yindex];
            }

        }

        
        answer[0] = curY;
        answer[1] = curX;

		// ���������� ��, �� ������ ���
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
