import java.util.*;

class CatchCow {
    public int solution(int s, int e){
        
        int[][] ch = new int[2][200001];

        int personIndex = s;
        int cowIndex = e;
        int cowMove = 1;

        int level = 0;

        // 현재의 짝수 Level 은 0 Level 부터 지금까지 짝수 Level에서 방문 가능했던 좌표가 현재 짝수 Level에서 모두 다시 방문 가능하다.
        // 현재의 홀수 Level 은 1 Level 부터 지금까지 홀수 Level에서 방문 가능했던 좌표가 현재 홀수 Level에서 모두 다시 방문 가능하다. 
        Queue<Integer> Q = new LinkedList<>();
        ch[level%2][0] = 1; 
        Q.offer(personIndex);

        while(!Q.isEmpty()) {
            int size = Q.size();
            
            // 시간 증가
            level++;

            // 소의 위치 이동
            cowIndex = cowIndex + cowMove;
            cowMove++;

            if (cowIndex > 200000) return -1;

            for(int i = 0; i < size; i++) {
                int cur = Q.poll();

                for(int nx : new int[]{cur + 1, cur - 1, cur * 2}) {
                    // 현재 짝수 Level, 홀수 Level에 따라서, 방문 가능한 좌표인지 (이미 방문한 좌표인지) 확인하다.
                    if(nx >= 0 && nx <= 200000 && ch[level%2][nx] == 0){
                        ch[level%2][nx] = 1;
                        Q.offer(nx);
                    }
                }
            }

            // 현 Level에서 존재하는 Q를 다 꺼내고 순회를 해서 이동 가능한 좌표를 Level에 맞게 방문 표시를 했다면
            // 증가된 cowIndex가 현수 좌표계에 비교를 해서 방문했다는 의미의 1이 응답되면, 해당 Level에서 현수는 소를 잡을 수 있었다.
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