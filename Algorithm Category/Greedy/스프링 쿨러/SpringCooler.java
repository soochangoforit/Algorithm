import java.util.*;

class SpringCooler {
	public int solution(int n, int[] nums){
		int answer = 0;

		// ������ ������ �𷯰� ���� �Ѹ��� ����~������ ������ �����ϴ� �迭
		int[][] coolerRange = new int[nums.length][2];
		for (int i = 0; i < nums.length; i++){
			// 0���� ���� ���� ���̸� �ִ� 0���� �Ҵ� 
			coolerRange[i][0] = Math.max(0, i - nums[i]);
			// �ܵ� �� n ������� ū ���� ���̸� �ִ� n���� �Ҵ�
			coolerRange[i][1] = Math.min(n, i + nums[i]);
		}

		// ���� �Ѹ��� ������ ���� ���� �������� �������� ����
		// ���� ���� �ϴ� ������ ���� �Ѹ��� �� �ϴ� �ܵ� �翡 ���� ���� �Ǵ��� �� �ִ�.
		Arrays.sort(coolerRange, (a,b) -> a[0] - b[0]);

		// start ���� �ѷ����� ������ ���� ��, end ���� �ѷ����� ������ ������ ��
		int start = 0, end = 0, i = 0;

		// ������ �𷯱��� �ϴ� ��ȸ�� �õ��Ѵ�.
		while(i < coolerRange.length){
			// i <= coolerRange.length �� ���̸�, ���� ������ ��ȸ���� �� �ߴٴ� �ǹ��̴�.
			// ó�� : start(0) �������� ���� ��� ���������� ���� �Ѹ� �� �ִ� ������ ã�´�.
			// ��� start �������� ���� ������ ���� �Ѹ��� ������ �𷯰� ���� �� ������,
			// start �������� ���� ��� ���������� ���� �Ѹ� �� �ִ� ������ ã�°� �ּ� ������ ����ϴ� ����̴�. (�̹� �ѷȴٰ� �ؼ� ������ �κ��� ����.)
			while (i < coolerRange.length && coolerRange[i][0] <= start){
				end = Math.max(end, coolerRange[i][1]);
				i++;
			}
			// �ʿ��� ������ �� 1�� ������Ű��
			answer++;

			// ������ ���� end�� n�̸� ���� �� �ѷȴٴ� �ǹ�
			if (end == n) return answer;

			// ���� while�� ���ؼ� start�� �����ؼ� ���� �Ѹ��� ������ line[i][0] <= start �� ����� ���� �ڿ��� �Ѹ��ٸ�
			// while �� �ȿ��� end�� ������Ʈ �Ǿ�� �ϴµ�, �׷��� �� �Ѵ�. �׷��� �Ǹ� ��������� end �� start�� ��������.
			// �� ���� �ｼ, ���� �Ѹ��� ���ϴ� ������ �ִٴ� �ǹ��̴�. "-1" �� return �ؾ��Ѵ�.
			if (start == end) return -1;


			// �ּ� ������ ����ϱ� ���ؼ�, end �������� ���� �Ѵ°� ����.
			start = end;
			
		}



		return answer;
	}

	public static void main(String[] args){
		SpringCooler T = new SpringCooler();
		System.out.println(T.solution(8, new int[]{1, 1, 1, 2, 1, 1, 2, 1, 1}));
		System.out.println(T.solution(4, new int[]{1, 2, 2, 0, 0}));		
		System.out.println(T.solution(5, new int[]{2, 0, 0, 0, 0, 2}));
		System.out.println(T.solution(11, new int[]{1, 2, 3, 1, 2, 1, 1, 2, 1, 1, 1, 1}));
	}
}