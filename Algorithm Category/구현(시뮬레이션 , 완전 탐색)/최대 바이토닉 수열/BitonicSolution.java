import java.util.*;

class BitonicSolution {
	public int solution(int[] nums){
		int answer = 0;

        // ���츮 �ڰ��� �ִ� index ��ġ ã��
        ArrayList<Integer> peaks = new ArrayList<>();

        for (int i = 1; i < nums.length -1 ; i++) {
            if (nums[i-1] < nums[i] && nums[i] > nums[i+1]) {
                peaks.add(i);
            }
        }

        for (int peak : peaks) {

            // ���� ���츮 ��ġ�� ���� ���� �켱������ ����
            int cnt = 1;

            // �������� ������ index ��ǥ
            int left = peak;
            
            // �������� ������ index ��ǥ
            int right = peak;

            // �������� �̵��ϴ� ���
            while(left > 0 && nums[left-1] < nums[left]) {
                left--;
                cnt++;
            }
          
            // �����ϴ� ���
            while(right < nums.length-1 && nums[right] > nums[right+1]){
                right++;
                cnt++;
            }
           
            // ������ ���츮 �� �ִ� ���� �� ã��
            answer = Math.max(answer, cnt);
        }


        
		return answer;	
	}

	public static void main(String[] args){
		BitonicSolution T = new BitonicSolution();
		System.out.println(T.solution(new int[]{1, 2, 1, 2, 3, 2, 1}));
		System.out.println(T.solution(new int[]{1, 1, 2, 3, 5, 7, 4, 3, 1, 2}));
		System.out.println(T.solution(new int[]{3, 2, 1, 3, 2, 4, 6, 7, 3, 1}));
		System.out.println(T.solution(new int[]{1, 3, 1, 2, 1, 5, 3, 2, 1, 1}));
	}
}

