import java.util.*;

class SpringCoolerReview {
	public int solution(int n, int[] nums){
		int answer = 0;

		// ������ ������ �𷯰� ���� �Ѹ��� ������ 2�� �迭�� ����
		int[][] coolerRange = new int[nums.length][2];
		for(int i = 0; i < nums.length; i++) {
			coolerRange[i][0] = Math.max(0, i - nums[i]);
			coolerRange[i][1] = Math.min(n, i + nums[i]);
		}

		// ������ ������ �𷯰� ���� �Ѹ��� �������� �������� ���� ���� ����
		Arrays.sort(coolerRange, (a,b) -> a[0] - b[0]);

		int start = 0, end = 0, i = 0;

		while(i < coolerRange.length) {
			// ���� �Ѹ��� �������� �ٸ� ������ �𷯰� ���ö� ���� while �ݺ�
			while (i < coolerRange.length && coolerRange[i][0] <= start) {
				end = Math.max(end, coolerRange[i][1]);
				i++;
			}
			
			// while ���� �������Դٴ� �ǹ̴� ���� �ֱ� �����ϴ� start ��ġ�� ���Ӱ� update ����� ���� �ǹ��Ѵ�.
			answer++;

			// ���� while ���� �ѹ��� ���� ���� ���¶�� start == end�� ���� ����
			// ���� �ǹ̷� ���� start ������ ��ġ�ϴ� ������ �𷯿� ���� �ܵ� �翡 ���� ���� �� �� �κ��� �߻� (-1 ��ȯ �ʿ�)
			if (start == end) return -1;
			
			// ����, end�� �ܵ� ���� ������ ���� �ߴٸ� answer�� �̸� return �Ѵ�.
			if (end == n) return answer;

			// ���� 2���� if������ ��ư��� �ʾҴٸ�, ���� start ��ġ�� ������ �𷯸� ������ ��
			// start ��ġ�� end ��ġ�� ���� �÷���� �Ѵ�. (�ܵ�翡 ���� �Ѹ� �� �ִ� Ư�� ��ġ���� �ִ�� �ָ� �Ѹ� �� �ִ� �𷯸� ����ߴٸ�)
			// ���� end �������� start�� �ϴ� ���� ������ �𷯰� �ִٰ� �����ϸ�, start�� end���� �÷����� ������ 
			// ���� ���� while�� ���� �ʰ� �׷��� �Ǹ� if(start == end) ���ǿ� ���� ��ġ �ʴ� -1�� return �ϰ� �ȴ�.
			start = end;
		}

	

		return answer;
	}

	public static void main(String[] args){
		SpringCoolerReview T = new SpringCoolerReview();
		System.out.println(T.solution(8, new int[]{1, 1, 1, 2, 1, 1, 2, 1, 1}));
		System.out.println(T.solution(4, new int[]{1, 2, 2, 0, 0}));		
		System.out.println(T.solution(5, new int[]{2, 0, 0, 0, 0, 2}));
		System.out.println(T.solution(11, new int[]{1, 2, 3, 1, 2, 1, 1, 2, 1, 1, 1, 1}));
	}
}