import java.util.*;

class CatchCow {
public int solution(int s, int e){
        int[] ch = new int[200001];

        int cowIndex = e;
        int cowMove = 1;

        int personIndex = s;

        Queue<Integer> Q = new LinkedList<>();
        Q.offer(personIndex);
        ch[personIndex] = 1;
        int time = 0;

        while(!Q.isEmpty()) {
            int size = Q.size();

            // 1�� ����
            time++;
            
            // 1�� �ð� ������ ���� �� �̵�
            cowIndex += cowMove;
            cowMove++;

            // �Ұ� ������ ����� ���, ���� �� �Ѵ�.
            if (cowIndex > 200000) return -1;

            for(int i = 0; i < size; i++) {
                int cur = Q.poll();

                int forward = cur + 1;
                if(forward <= 200000 && ch[forward] == 0) {
                    ch[forward] = 1;
                    Q.offer(forward);
                }

                

                int back = cur - 1;
                if(back >= 0 && ch[back] == 0) {
                    ch[back] = 1;
                    Q.offer(back);
                }

            

                int doubleJump = cur * 2;
                if (doubleJump <= 200000 && ch[doubleJump] == 0) {
                    ch[doubleJump] = 1;
                    Q.offer(doubleJump);
                }

                
            }

            // ���� �ʷ� �Ѿ�� ����, ���� �̵��� ���� ��ǥ�� while �� ���� for�� ���� ����� �湮�� ��ǥ���� Ȯ��
            // �湮�� ��ǥ�� ��Ҵٴ� �ǹ��̹Ƿ�, ���� �ð��� ����
            if (ch[cowIndex] == 1) return time;
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