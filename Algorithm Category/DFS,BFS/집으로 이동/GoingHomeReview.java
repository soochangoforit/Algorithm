import java.util.*;

class GoingHomeReview {

	public int solution(int[] pool, int a, int b, int home){

		// 0 index는 앞으로 이동하는 경우, 1 index는 뒤로 이동하는 경우
		int[][] ch = new int[2][100001];

		// pool은 어차피 이동을 못 하기에 이미 접근한 것 처럼 방문 배열 1로 초기화
		for(int p : pool) {
			ch[0][p] = 1;
			ch[1][p] = 1;
 		}

		// {현재 좌표, 전에 좌표에서 앞으로 왔으면 1 (뒤로 왔으면 0)}
		Queue<int[]> Q = new LinkedList<>();
		Q.offer(new int[]{0,0});
		ch[0][0] = 1;
		ch[1][0] = 1;

		int moveCount = 0;


		while(!Q.isEmpty()){
			int Qsize= Q.size();

			for(int i = 0; i < Qsize; i++) {
				int[] cur = Q.poll();

				if (cur[0] == home) return moveCount;

				int forward = cur[0] + a;
				if(forward <= 10000 && ch[0][forward] == 0) {
					ch[0][forward] = 1;
					Q.offer(new int[]{forward, 0});
				}

				int back = cur[0] - b;
				if(back >= 0 && ch[1][back] == 0 && cur[1] == 0) {
					ch[1][back] = 1;
					Q.offer(new int[]{back,1});
				}
			}
			moveCount++;
		}


        
		return -1;
	}



	public static void main(String[] args){
		GoingHomeReview T = new GoingHomeReview();
		System.out.println(T.solution(new int[]{11, 7, 20}, 3, 2, 10));	
		System.out.println(T.solution(new int[]{1, 15, 11}, 3, 2, 5));	
		System.out.println(T.solution(new int[]{9, 15, 35, 30, 20}, 2, 1, 25));	
		System.out.println(T.solution(new int[]{5, 12, 7, 19, 23}, 3, 5, 18));	
		System.out.println(T.solution(new int[]{10, 15, 20}, 3, 2, 2));	
	}
}