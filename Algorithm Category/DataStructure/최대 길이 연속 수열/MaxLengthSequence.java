import java.util.*;

class MaxLengthSequence {

	public int solution(int[] nums){
		int answer = 1;

        // 정렬
        Arrays.sort(nums);

        List<Integer> answers = new ArrayList<>();

        for (int i = 0; i < nums.length - 1; i++) {
            int firstValue = nums[i];

            // 다음 숫자가 +1로 되는 순열 조건 만족한다.
            if(firstValue + 1 == nums[i+1]){
                answer++;
            } 
            
            // 다음 숫자가 같은 숫자로 나온다면, continue
            else if (firstValue == nums[i+1]){
                continue;
            } 
            
            // 다음 숫자가 +1로 되는 순열 조건을 만족하지 않는다면, answer를 answers에 넣고, answer를 1로 초기화
            else{
                answers.add(answer);
                answer = 1;
            }

        }

        // 모든 반복문이 끝나면, 마지막 answer를 answers에 넣는다.
        answers.add(answer);

        // answers에서 가장 큰 값을 찾는다.
        int finalAnswer = answers.stream().mapToInt(Integer::intValue).max().getAsInt();
		
		return finalAnswer;
	}

	public static void main(String[] args){
		MaxLengthSequence T = new MaxLengthSequence();
		System.out.println(T.solution(new int[]{8, 1, 9, 3, 10, 2, 4, 0, 2, 3}));
		System.out.println(T.solution(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 0, 0, 0, 0}));
		System.out.println(T.solution(new int[]{3, 3, 3, 3, 3, 3, 3, 3}));
		System.out.println(T.solution(new int[]{-3, -1, -2, 0, 3, 3, 5, 6, 2, 2, 1, 1}));
		System.out.println(T.solution(new int[]{-5, -3, -1, -4, 3, 3, 5, 6, 2, 2, 1, 1, 7}));
	}
}