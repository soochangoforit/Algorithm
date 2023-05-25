import java.util.*;

class GoingHome {

	public int solution(int[] pool, int a, int b, int home){
        
		// [앞으로 이동 방문 배열][뒤로 이동 방문 배열] => 2중 배열로 구성
		int[][] ch = new int[2][100001];

		// 물 웅덩이는 미리 방문 처리
		for(int p : pool) {
			ch[0][p] = 1;
			ch[1][p] = 1;
		}


		Queue<int[]> Q = new LinkedList<>();

		// 제일 상위 Root 노드에 대해서는 방문 처리
		ch[0][0] = 1;
		ch[1][0] = 1;

		// 노드를 움직이면서, {현재 좌표, 저번 노드가 뒤로 이동했는지, 앞으로 이동했는지 판단 여부}
		// {현재 좌표, 0} => 저번 노드 앞으로 움직임
		// {현재 좌표, 1} => 저번 노드 뒤로 움직임
		Q.offer(new int[]{0,0});

		// 아직 한번 더 움직이지 않았기에 level 0
		int level = 0;

		while(!Q.isEmpty()){
			int size = Q.size();

			for(int i = 0; i < size; i++) {
				int[] cur = Q.poll();

				int foward = cur[0] + a;

				// Q에서 방금 꺼낸 노드 기준으로는 level을 return 하고
				// Q에서 방금 꺼낸 노드 x 라고 가정하면 움직임을 더한 nx 기준으로는 level + 1을 return 해야한다.
				if (cur[0] == home) return level;

				// index 범위를 벗어나지 않으면서, 앞으로 이동 방문하지 않아야 한다.
				if (foward <= 10000 && ch[0][foward] == 0) {
					ch[0][foward] = 1;
					// {현재 좌표, 앞으로 이동 했다는 의미는 0}
					Q.offer(new int[]{foward, 0});
				}

				int back = cur[0] - b;

				// index 범위를 벗어나지 않으면서, 뒤로 이동 방문 X, 추가로 현재 Q에서 방금 빠져나온 cur 노드가 뒤로 이동해서 나온 결과 값이 아니여야 한다.
				if (back >=0 && ch[1][back] == 0 && cur[1] == 0) {
					ch[1][back] = 1;
					// {현재 좌표, 뒤로 방문 했다는 의미는 1}
					Q.offer(new int[]{back, 1});
				}


			}

			// 자식 노드 방문을 다 끝나고, 자손노드들도 Q에 다 넣었다면 
			// 자손 노드를 탐색하기 위한 level + 1을 해준다.
			level++;
		}

		// 방문하지 못 하는 경우는 -1을 해준다.
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