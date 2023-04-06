import java.util.*;

class Room {
	public int[] solution(int[] enter, int[] exit){
 

        // 1��° ����� ������ �� index �������� ���߱�
        int n = enter.length;

        for(int i = 0; i < n; i++){
            enter[i]--;
            exit[i]--;
        }

        // 0���� ~ n ������ ������� ���°�� ���Դ��� ������ �迭
        // enterIdx[�����ȣ] = ���°�� ���Դ���
        int[] enterIdx = new int[n];
        for(int i =0; i<n; i++){
            enterIdx[enter[i]] = i;
        }

        // ���ǽǿ� ������ ������ �κ��� �ð� ������� �����ϰ��� �Ѵ�.
        // ���� �ð��� ������ �迭
        // enterTime[�����ȣ]=���� �ð�
        int[] enterTime = new int[n];

        // ���� �ð��� ������ �迭
        // exitTime[�����ȣ]=���� �ð�
        int[] exitTime = new int[n];

        // �ð��� ������ ����
        int cnt = 0;
        
        // i�� exit �迭�� ��ȸ
        // j �� enter �迭�� ��ȸ
        // exit ó�� ������ ���ؼ��� �켱������ ���;� �Ѵ�.
        for(int i = 0, j = 0; i < n; i++){
            // exitHuman �� �������� �ϴ� ��� ��ȣ
            int exitHuman = exit[i];

            // j �� �������� �ϴ� ����� ���°�� ���Դ��� ��� ���� 
            // enterTime �迭�� ���� �Ҵ�
            while(j < n && j <= enterIdx[exitHuman]){
                int enterHuman = enter[j];

                // 0�� ���� �����ϰ��� �Ѵ�.
                enterTime[enterHuman] = cnt++;

                // exitHuman �� ������ �ٷ� ������ ����, �̸� ���� ����� ���� enterTime �� cnt ���� ������Ų��.
                j++;
            }
            
            // enterTime�� cnt ���� �� �ݿ��ϰ� ����, exitHuman �� ������ �Ѵ�.
            exitTime[exitHuman] = cnt++;

        }

        int[] answer = new int[n];

        // 0�� ������� n-1�� ������� ���ϰ��� �Ѵ�.
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                // ������ ������ �ʴ� ��츦 ������ ���� => �����ٰ� �Ǵ�
                if(!(exitTime[i] < enterTime[j] || exitTime[j] < enterTime[i])) {
                    answer[i]++;
                    answer[j]++;
                } 
            }

        }


        
		
		return answer;
	}
		
	public static void main(String[] args){
		Room T = new Room();
		System.out.println(Arrays.toString(T.solution(new int[]{1, 2, 3, 4}, new int[]{2, 4, 1, 3})));
		System.out.println(Arrays.toString(T.solution(new int[]{1, 2, 5, 3, 4}, new int[]{2, 3, 1, 4, 5})));
		System.out.println(Arrays.toString(T.solution(new int[]{1, 3, 2, 4, 5, 7, 6, 8}, new int[]{2, 3, 5, 6, 1, 4, 8, 7})));
		System.out.println(Arrays.toString(T.solution(new int[]{1, 4, 7, 2, 3, 5, 6}, new int[]{5, 2, 6, 1, 7, 3, 4})));
		System.out.println(Arrays.toString(T.solution(new int[]{1, 4, 2, 3}, new int[]{2, 1, 4, 3})));
	}
}