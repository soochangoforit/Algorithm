import java.util.*;

class BitonicSolution {
	public int solution(int[] nums){
		int answer = 0;

        // 봉우리 자격이 있는 index 위치 찾기
        ArrayList<Integer> peaks = new ArrayList<>();

        for (int i = 1; i < nums.length -1 ; i++) {
            if (nums[i-1] < nums[i] && nums[i] > nums[i+1]) {
                peaks.add(i);
            }
        }

        for (int peak : peaks) {

            // 현재 봉우리 위치도 길이 값에 우선적으로 포함
            int cnt = 1;

            // 좌측으로 움직일 index 좌표
            int left = peak;
            
            // 우측으로 움직일 index 좌표
            int right = peak;

            // 좌측으로 이동하는 경우
            while(left > 0 && nums[left-1] < nums[left]) {
                left--;
                cnt++;
            }
          
            // 감소하는 경우
            while(right < nums.length-1 && nums[right] > nums[right+1]){
                right++;
                cnt++;
            }
           
            // 각각의 봉우리 중 최대 길이 값 찾기
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

