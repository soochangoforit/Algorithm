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

            // 1초 증가
            time++;
            
            // 1초 시간 지남에 따라 소 이동
            cowIndex += cowMove;
            cowMove++;

            // 소가 범위를 벗어나는 경우, 잡지 못 한다.
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

            // 다음 초로 넘어가기 전에, 현재 이동한 소의 좌표가 while 문 안의 for에 의해 사람이 방문한 좌표인지 확인
            // 방문한 좌표면 잡았다는 의미이므로, 현재 시간을 리턴
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