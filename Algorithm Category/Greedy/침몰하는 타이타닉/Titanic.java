import java.util.*;

class Titanic {
	public int solution(int[] nums, int m){
		int answer = 0;

        Arrays.sort(nums);

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            if (nums[left] + nums[right] <= 150) {
                answer++;
                left++;
                right--;
            }
            else {
                // 오름 차순 정렬이 되어 있기에
                // 제일 몸무게가 작은 사람이랑도 보트를 타지 못한다면
                // 가장 우측에 있는 가장 무거운 사람은 혼자 보트를 타야하기에 asnwer++
                answer++;
                right--;

            }
        }


		
		return answer;
	}

	public static void main(String[] args){
		Titanic T = new Titanic();
		System.out.println(T.solution(new int[]{90, 50, 70, 100, 60}, 140));
		System.out.println(T.solution(new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90}, 100));
		System.out.println(T.solution(new int[]{68, 72, 30, 105, 55, 115, 36, 67, 119, 111, 95, 24, 25, 80, 55, 85, 75, 83, 21, 81}, 120));
	}
}