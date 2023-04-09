import java.util.*;

class MaxLengthSequence {

	public int solution(int[] nums){
		int answer = 1;

        // ����
        Arrays.sort(nums);

        List<Integer> answers = new ArrayList<>();

        for (int i = 0; i < nums.length - 1; i++) {
            int firstValue = nums[i];

            // ���� ���ڰ� +1�� �Ǵ� ���� ���� �����Ѵ�.
            if(firstValue + 1 == nums[i+1]){
                answer++;
            } 
            
            // ���� ���ڰ� ���� ���ڷ� ���´ٸ�, continue
            else if (firstValue == nums[i+1]){
                continue;
            } 
            
            // ���� ���ڰ� +1�� �Ǵ� ���� ������ �������� �ʴ´ٸ�, answer�� answers�� �ְ�, answer�� 1�� �ʱ�ȭ
            else{
                answers.add(answer);
                answer = 1;
            }

        }

        // ��� �ݺ����� ������, ������ answer�� answers�� �ִ´�.
        answers.add(answer);

        // answers���� ���� ū ���� ã�´�.
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