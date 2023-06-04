import java.util.*;

class ShortTransferPlanReview {
	public int solution(int[][] routes, int s, int e){
		int answer = 0;
    
        // key : 역, value : {해당 역에 속한 노선}
        HashMap<Integer, HashSet<Integer>> liens = new HashMap<>();

        // 초기 자료 구조 구성
        for(int line = 0; line < routes.length; line++){
            for(int station : routes[line]) {
                liens.putIfAbsent(station, new HashSet<>());
                liens.get(station).add(line);
            }
        }

        Queue<Integer> Q = new LinkedList<>();
        Q.offer(s);

        int[] ch = new int[routes.length];

        int rideOfSubway = 0;

        while(!Q.isEmpty()) {
            int Qsize = Q.size();

            // Q 사이즈만큼 반복
            for(int i = 0; i < Qsize; i++) {
                int curStation = Q.poll();

                // 현재 curStation 역이 속한 모든 노선 정보를 가져온다.
                HashSet<Integer> curLine = liens.get(curStation);

                // curStation이 현재 속한 모든 노선 정보를 순회한다.
                for(int line : curLine) {

                    // 해당 노선을 아직 Q에 넣지 않은 경우에서만 접근한다.
                    if (ch[line] == 0) {
                        // 곧 Q에 넣고, 해당 노선은 Q에 들어감으로써 이젠 더 이상 방문하지 않기 위해 check 해준다
                        ch[line] = 1;

                        // curStation이 속한 모든 노선에 있는 역들을 Q에 넣어준다.
                        for(int station : routes[line]) {

                            // 만약, Q에 넣는데 end와 같은 역이라면 답을 return 한다.
                            if(station == e) return rideOfSubway;

                            Q.offer(station);
                        }

                    }
                }

            }
            rideOfSubway++;
        }

		
		return -1;
	}
		
	public static void main(String[] args){
		ShortTransferPlanReview T = new ShortTransferPlanReview();
		System.out.println(T.solution(new int[][]{{1, 2, 3, 4, 5, 6, 19}, {2, 7, 8, 13}, {5, 9, 10}, {9, 11, 12, 18}, {13, 14, 15}, {14, 12, 16, 17}}, 1, 12));
		System.out.println(T.solution(new int[][]{{1, 3, 5, 7}, {9, 3, 12}, {6, 5, 8}, {2, 8, 14, 15}, {2, 14, 16}}, 1, 14));
		System.out.println(T.solution(new int[][]{{7, 12},{5, 19},{7, 19},{9, 12, 13},{9, 5, 15}}, 9, 19));
		System.out.println(T.solution(new int[][]{{1, 2, 3, 4, 5},{9, 7, 10},{7, 6, 3, 8}, {5, 11, 8, 12}}, 1, 10));
	}
}