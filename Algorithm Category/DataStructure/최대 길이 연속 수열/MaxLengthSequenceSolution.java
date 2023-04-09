import java.util.*;

class MaxLengthSequenceSolution {

	public int solution(int[] nums){
		int answer = 0;

        // �ߺ� ���� => ��� ������� ��� �ð��� ���� �ȴ�.
        HashSet<Integer> set = new HashSet<>();
        for(int num : nums){
            set.add(num);
        }

        for (int x : set) {
            // x �ڽ� ������ -1 ���� ���� set�� �����Ѵٸ�, continue
            if (set.contains(x-1)) continue;

            // x ���� ���� ���ڰ� ����, ��, x�� ���ӵ� ������ ������ �� �� �ִ�.
            // while ���� ���� ���ٸ�, while���� �������ͼ� ���� set �迭�� ���� ���� �������� �Ѵ�.
            int cnt = 0;
            while (set.contains(x)){
                // �ڱ� �ڽ��� ������ ���ӵ� ������ ���̸� �����ش�.
                cnt++;
                // �����̶� +1�� ���� ���縦 �ؾ��ϱ⿡ x�� +1 ���ְ� �ٽ� while���� ������.
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