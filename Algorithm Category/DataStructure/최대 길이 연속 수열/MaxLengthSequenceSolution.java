import java.util.*;

class MaxLengthSequenceSolution {

	public int solution(int[] nums){
		int answer = 0;

        // 중복 제거 => 모든 연산들이 상수 시간을 갖게 된다.
        HashSet<Integer> set = new HashSet<>();
        for(int num : nums){
            set.add(num);
        }

        for (int x : set) {
            // x 자신 값보다 -1 작은 값이 set에 존재한다면, continue
            if (set.contains(x-1)) continue;

            // x 보다 작은 숫자가 없다, 즉, x가 연속된 수열의 시작이 될 수 있다.
            // while 문에 값이 없다면, while문을 빠져나와서 다음 set 배열의 원소 값을 기준으로 한다.
            int cnt = 0;
            while (set.contains(x)){
                // 자기 자신을 포함한 연속된 수열의 길이를 더해준다.
                cnt++;
                // 수열이란 +1인 값이 존재를 해애하기에 x를 +1 해주고 다시 while문을 돌린다.
                x++;
            }

            answer = Math.max(answer, cnt);

        }

		return answer;
	}

	public static void main(String[] args){
		MaxLengthSequenceSolution T = new MaxLengthSequenceSolution();
		System.out.println(T.solution(new int[]{8, 1, 9, 3, 10, 2, 4, 0, 2, 3}));
		System.out.println(T.solution(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 0, 0, 0, 0}));
		System.out.println(T.solution(new int[]{3, 3, 3, 3, 3, 3, 3, 3}));
		System.out.println(T.solution(new int[]{-3, -1, -2, 0, 3, 3, 5, 6, 2, 2, 1, 1}));
		System.out.println(T.solution(new int[]{-5, -3, -1, -4, 3, 3, 5, 6, 2, 2, 1, 1, 7}));
	}
}