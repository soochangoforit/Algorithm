import java.util.*;

class Bitonic {
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

            // �����ϴ� ���
            for (int j = peak-1; j >= 0; j--) {
                if(nums[j] < nums[j+1]) {
                    cnt++;
                }else{
                    break;
                }
            }

            // �����ϴ� ���
            for (int j = peak; j < nums.length-1; j++) {
                if(nums[j] > nums[j+1]) {
                    cnt++;
                }else{
                    break;
                }
            }

            answer = Math.max(answer, cnt);
        }


        
		return answer;	
	}

	public static void main(String[] args){
		Bitonic T = new Bitonic();
		System.out.println(T.solution(new int[]{1, 2, 1, 2, 3, 2, 1}));
		System.out.println(T.solution(new int[]{1, 1, 2, 3, 5, 7, 4, 3, 1, 2}));
		System.out.println(T.solution(new int[]{3, 2, 1, 3, 2, 4, 6, 7, 3, 1}));
		System.out.println(T.solution(new int[]{1, 3, 1, 2, 1, 5, 3, 2, 1, 1}));
	}
}


            // // �����ϴ� ���
            // for (int j = i-1; j >= 0; j--) {
            //     if(nums[j] < nums[j+1]) {
            //         cnt++;
            //     }else{
            //         break;
            //     }
            // }

            // // �����ϴ� ���
            // for (int j = i; j > 0; j--) {
            //     if(nums[j] < nums[j-1]) {
            //         cnt++;
            //     }else{
            //         break;
            //     }
            // }

            // answer = Math.max(answer, cnt);
