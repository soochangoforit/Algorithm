import java.util.*;

class TileJumpReview {

	public int solution(int[] nums){
		int answer = 0;

        int n = nums.length;

        // �̹� �湮�� �ߴ��� Ȯ���ϱ� ���� �迭
        int[] ch = new int[n];

        Queue<Integer> Q = new LinkedList<>();
        Q.offer(0);
        ch[0] = 1;
        int L = 0;

        while(!Q.isEmpty()){
            // ���� ��ġ���� �̵� ������ Ÿ��(�ڽ� ���) �� ��ŭ ���� �� �ִ�.
            int size = Q.size();
            for(int i = 0; i < size; i++) {
                // ���� Ÿ�Ͽ��� �̵� ������ Ÿ��(�ڽ� ���)�� �ϳ��� ������.
                int x = Q.poll();
                
                // �ڽ� ��尡 �̵��� �� �ִ� ���� �� ����
                for(int j = 1; j <= nums[x]; j++) {
                    // for�� �ȿ��� x�� �ڽ� ����̸鼭, ���� ���� �����Ѵ�.
                    // ���� �ڽ� ��忡�� �̵��� �� �ִ� ���� �� j
                    // �̵��Ѵ� ���� ���� ���� ��ġ : nx
                    int nx = x + j;

                    // ���� ����� ��ġ nx(index ����)�� ������ �ε���(��ü ������ - 1)�� �� �ѹ� �� while�� ���� �ʱ� ���� �̸� return (������ ������ while ���� �� ���� L++ ������)
                    // ������ �̸� return�� �߱⿡ L + 1 �� �Ѵ�.
                    if(nx == n - 1) return L + 1;

                    // �������� �̵��� �� �ִ� nx (index ����) �� ��ü �ε��� ������ ����� �ʰ�, �湮���� ���� ��쿡���� �湮
                    if(nx < n && ch[nx] == 0) {
                        // �湮 ó�� ��, �ڽ� ���� �Լ�
                        ch[nx] = 1;
                        Q.offer(nx);
                    }
                }
            }

            // �ڽ� ��忡 ���� ��� ���İ��⿡
            L++;
        }


		// ���� while�� �� ���� ������ while�� �ȿ��� L + 1�� return ���� �� �ϸ�, �湮���� �� �ϱ⿡ -1�� return �Ѵ�.
		return -1;
	}
		
	public static void main(String[] args){
		TileJumpReview T = new TileJumpReview();
		System.out.println(T.solution(new int[]{2, 2, 1, 2, 1, 1}));
		System.out.println(T.solution(new int[]{1, 0, 1, 1, 3, 1, 2, 1}));
		System.out.println(T.solution(new int[]{2, 3, 1, 0, 1, 1, 2, 3, 1, 5, 1, 3, 1}));
		System.out.println(T.solution(new int[]{1, 2, 1, 2, 1, 2, 1, 1, 3, 1, 2, 1}));
		System.out.println(T.solution(new int[]{1, 3, 2, 1, 1, 2, 3, 1, 3, 1, 2, 3, 5, 1, 5, 1, 2, 1, 1}));
	}
}