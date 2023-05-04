import java.util.*;

class Flower {
	public int solution(int[] plantTime, int[] growTime){
		int answer = 0;

        int n = plantTime.length;
        
        // [�ɴµ� �ɸ��� �ð�][�����ϴµ� �ɸ��� �ð�]
        int[][] flowers = new int[n][2];
        for (int i = 0; i < n ; i++) {
            flowers[i][0] = plantTime[i];
            flowers[i][1] = growTime[i];
        }

        // �����ϴµ� ���� �ð��� �ɸ��� ������ ��������
        // �����ϴµ� ���� ���� �ɸ��� �ɺ��� �ɴ°� ����.
        Arrays.sort(flowers, (a,b) -> b[1] - a[1]);

        // start�� ���� ��¥, end�� ������ ���� ��¥
        int start = 0, end = 0;

        for(int[] flower : flowers) {
            end = start + flower[0] + flower[1];
            // �������� ���ϴ� �ּ� �ð��� ã�� ���� �츮�� �̹� ������������ ������ �ߴ�.
            // �ɰ� �Ǳ���� �ּ������� �ð��� �ɸ��� ���� �ɰ��� �ϴ� �� ���� �ȿ��� �ɸ��� �ִ밪�� ã�ƾ� �Ѵ�.
            // �ּҰ��� ã�� �Ǹ�, �ٸ� �Ĺ��� ���� ���� �������� ���� �ɰ� �ִ� �Ĺ��� ���� �� �����ߴٰ� �� ������ �ƴϴ�.
            // �ɰ� �Ǵ� �������� �ð��� �ּ�ȭ �ϱ� ���ؼ� ������ �°� ���� �ɾ� ���� �Ĺ��� �� �ڶ��� ���� ��ٷ��� �Ѵ�.
            answer = Math.max(end, answer);
            start += flower[0];
        }

		
		return answer;
	}

	public static void main(String[] args){
		Flower T = new Flower();
		System.out.println(T.solution(new int[]{1, 3, 2}, new int[]{2, 3, 2}));
		System.out.println(T.solution(new int[]{2, 1, 4, 3}, new int[]{2, 5, 3, 1}));
		System.out.println(T.solution(new int[]{1, 1, 1}, new int[]{7, 3, 2}));
		System.out.println(T.solution(new int[]{5, 7, 10, 15, 7, 3, 5}, new int[]{6, 7, 2, 10, 15, 6, 7}));
		System.out.println(T.solution(new int[]{1, 2, 3, 4, 5, 6, 7}, new int[]{7, 5, 4, 3, 2, 1, 6}));
	}
}