import java.util.*;

class MySolution {
	public char[] solution(int n, int[][] ladder){
		char[] answer = new char[n];
	
		// ���⼭���� �ڵ� �ۼ�
        for (int i = 0; i < n ; i++) {
            // temp�� �����̰��� �ϴ� ����� index �ּ� ��
            int temp = i;

            for (int j = 0; j < ladder.length; j++) {
                
                for (int k = 0; k < ladder[j].length; k++) {

                    // (temp ��ġ�� +1) �� ladder[j][k]�� ������ �ش� ��ġ�� �������� ��ٸ��� �����ؼ� �������� ������ ���
                    if (ladder[j][k] == temp + 1) {
                        temp += 1;
                    } 
                    // teamp ��ġ���� ladder[j][k]�� ������ �ش� ��ġ�� �������� ��ٸ��� �����ؼ� �������� ������ ���
                    else if (ladder[j][k] == temp) {
                        temp -= 1;
                    }
                }

                // �ƹ��͵� �ƴ� ��쿡 ���ؼ��� temp ��ġ ���� �״�� ������ ���¿��� ���� ��ٸ��� �Ѿ��.

            }

            // temp ��ġ���� �´� ���ĺ��� answer �迭�� �־��ش�. �ƽ�Ű�ڵ带 �̿��Ѵ�.
            answer[temp] = (char) (i + 65);
        }

		return answer;
	}

	public static void main(String[] args){
		MySolution T = new MySolution();
		System.out.println(Arrays.toString(T.solution(5, new int[][]{{1, 3}, {2, 4}, {1, 4}})));
		System.out.println(Arrays.toString(T.solution(7, new int[][]{{1, 3, 5}, {1, 3, 6}, {2, 4}})));
		System.out.println(Arrays.toString(T.solution(8, new int[][]{{1, 5}, {2, 4, 7}, {1, 5, 7}, {2, 5, 7}})));
		System.out.println(Arrays.toString(T.solution(12, new int[][]{{1, 5, 8, 10}, {2, 4, 7}, {1, 5, 7, 9, 11}, {2, 5, 7, 10}, {3, 6, 8, 11}})));
	}
}