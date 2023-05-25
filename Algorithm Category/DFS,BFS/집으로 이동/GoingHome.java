import java.util.*;

class GoingHome {

	public int solution(int[] pool, int a, int b, int home){
        
		// [������ �̵� �湮 �迭][�ڷ� �̵� �湮 �迭] => 2�� �迭�� ����
		int[][] ch = new int[2][100001];

		// �� �����̴� �̸� �湮 ó��
		for(int p : pool) {
			ch[0][p] = 1;
			ch[1][p] = 1;
		}


		Queue<int[]> Q = new LinkedList<>();

		// ���� ���� Root ��忡 ���ؼ��� �湮 ó��
		ch[0][0] = 1;
		ch[1][0] = 1;

		// ��带 �����̸鼭, {���� ��ǥ, ���� ��尡 �ڷ� �̵��ߴ���, ������ �̵��ߴ��� �Ǵ� ����}
		// {���� ��ǥ, 0} => ���� ��� ������ ������
		// {���� ��ǥ, 1} => ���� ��� �ڷ� ������
		Q.offer(new int[]{0,0});

		// ���� �ѹ� �� �������� �ʾұ⿡ level 0
		int level = 0;

		while(!Q.isEmpty()){
			int size = Q.size();

			for(int i = 0; i < size; i++) {
				int[] cur = Q.poll();

				int foward = cur[0] + a;

				// Q���� ��� ���� ��� �������δ� level�� return �ϰ�
				// Q���� ��� ���� ��� x ��� �����ϸ� �������� ���� nx �������δ� level + 1�� return �ؾ��Ѵ�.
				if (cur[0] == home) return level;

				// index ������ ����� �����鼭, ������ �̵� �湮���� �ʾƾ� �Ѵ�.
				if (foward <= 10000 && ch[0][foward] == 0) {
					ch[0][foward] = 1;
					// {���� ��ǥ, ������ �̵� �ߴٴ� �ǹ̴� 0}
					Q.offer(new int[]{foward, 0});
				}

				int back = cur[0] - b;

				// index ������ ����� �����鼭, �ڷ� �̵� �湮 X, �߰��� ���� Q���� ��� �������� cur ��尡 �ڷ� �̵��ؼ� ���� ��� ���� �ƴϿ��� �Ѵ�.
				if (back >=0 && ch[1][back] == 0 && cur[1] == 0) {
					ch[1][back] = 1;
					// {���� ��ǥ, �ڷ� �湮 �ߴٴ� �ǹ̴� 1}
					Q.offer(new int[]{back, 1});
				}


			}

			// �ڽ� ��� �湮�� �� ������, �ڼճ��鵵 Q�� �� �־��ٸ� 
			// �ڼ� ��带 Ž���ϱ� ���� level + 1�� ���ش�.
			level++;
		}

		// �湮���� �� �ϴ� ���� -1�� ���ش�.
		return -1;
	}



	public static void main(String[] args){
		GoingHome T = new GoingHome();
		System.out.println(T.solution(new int[]{11, 7, 20}, 3, 2, 10));	
		System.out.println(T.solution(new int[]{1, 15, 11}, 3, 2, 5));	
		System.out.println(T.solution(new int[]{9, 15, 35, 30, 20}, 2, 1, 25));	
		System.out.println(T.solution(new int[]{5, 12, 7, 19, 23}, 3, 5, 18));	
		System.out.println(T.solution(new int[]{10, 15, 20}, 3, 2, 2));	
	}
}