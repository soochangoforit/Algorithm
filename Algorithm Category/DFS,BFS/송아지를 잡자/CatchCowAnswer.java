import java.util.*;

class CatchCow {
    public int solution(int s, int e){
        
        int[][] ch = new int[2][200001];

        int personIndex = s;
        int cowIndex = e;
        int cowMove = 1;

        int level = 0;

        // ������ ¦�� Level �� 0 Level ���� ���ݱ��� ¦�� Level���� �湮 �����ߴ� ��ǥ�� ���� ¦�� Level���� ��� �ٽ� �湮 �����ϴ�.
        // ������ Ȧ�� Level �� 1 Level ���� ���ݱ��� Ȧ�� Level���� �湮 �����ߴ� ��ǥ�� ���� Ȧ�� Level���� ��� �ٽ� �湮 �����ϴ�. 
        Queue<Integer> Q = new LinkedList<>();
        ch[level%2][0] = 1; 
        Q.offer(personIndex);

        while(!Q.isEmpty()) {
            int size = Q.size();
            
            // �ð� ����
            level++;

            // ���� ��ġ �̵�
            cowIndex = cowIndex + cowMove;
            cowMove++;

            if (cowIndex > 200000) return -1;

            for(int i = 0; i < size; i++) {
                int cur = Q.poll();

                for(int nx : new int[]{cur + 1, cur - 1, cur * 2}) {
                    // ���� ¦�� Level, Ȧ�� Level�� ����, �湮 ������ ��ǥ���� (�̹� �湮�� ��ǥ����) Ȯ���ϴ�.
                    if(nx >= 0 && nx <= 200000 && ch[level%2][nx] == 0){
                        ch[level%2][nx] = 1;
                        Q.offer(nx);
                    }
                }
            }

            // �� Level���� �����ϴ� Q�� �� ������ ��ȸ�� �ؼ� �̵� ������ ��ǥ�� Level�� �°� �湮 ǥ�ø� �ߴٸ�
            // ������ cowIndex�� ���� ��ǥ�迡 �񱳸� �ؼ� �湮�ߴٴ� �ǹ��� 1�� ����Ǹ�, �ش� Level���� ������ �Ҹ� ���� �� �־���.
            if(ch[level%2][cowIndex] == 1) return level;

        }
        
        return -1;
    }

	public static void main(String[] args){
		CatchCow T = new CatchCow();
		System.out.println(T.solution(1, 11));	
		System.out.println(T.solution(10, 3));	
		System.out.println(T.solution(1, 34567));
		System.out.println(T.solution(5, 6));	
		System.out.println(T.solution(2, 54321));	
	}
}