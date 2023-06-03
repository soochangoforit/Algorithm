import java.util.*;

class ShortTransferPlan {

	public int solution(int[][] routes, int s, int e){
		int answer = 0;

        // key : 역, value = {해당 역을 지나는 노선 번호}
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();

        // 전체 노선의 개수
        int countOfLine = routes.length;

        // graph 에는 각각의 역이 어느 노선에 속하는지 그 정보를 저장한다.
        // 문제에서 원하는 값을 얻기 위해서는 특정 역에 도달했을때, 해당 역이 속한 노선의 모든 역 정보를 Q에 넣어야 한다.
        // 그럴려면 해당 역이 어떤 노선에 속하는지 알아야 한다.
        for(int line = 0; line < countOfLine; line++){
			for(int station : routes[line]){
				graph.putIfAbsent(station, new HashSet<Integer>());
				graph.get(station).add(line);
			}
		}

        // 각각의 노선을 타고 갈때, 해당 노선을 탔는지 체크하는 배열
        int[] ch = new int[countOfLine];

        // Q에는 각 역을 지나는 노선의 모든 역 정보를 넣는다.
        Queue<Integer> Q = new LinkedList<>();
        Q.offer(s);
        int rideOfLine = 0;

        while(!Q.isEmpty()) {
            int Qsize = Q.size();

            for(int i = 0; i < Qsize; i++) {
                int station = Q.poll();

                // start 역이 속한 모든 노선들을 가져온다.
                HashSet<Integer> lines = graph.get(station);

                // 해당 노선을 방문하지 않은 경우에서만 접근한다.
                for(int line : lines) {
                    if(ch[line] == 0) {
                        ch[line] = 1;
                        // 해당 노선에 속한 모든 역 정보를 가져온다.
                        for(int nextStation : routes[line]) {
                            // 도착 역에 도달한 경우
                            if(nextStation == e) {
                                return rideOfLine;
                            }
                            // 아직 도착역에 도달하지 못한 경우는 Q에 넣는다.
                            Q.offer(nextStation);
                        }

                    }
                }
            }
            // 자식 역 정보를 다 순회했기에, 다음 노선을 타야한다.
            rideOfLine++;
        }
		
        // 해당 역에 방문을 하지 못하는 경우는 -1을 return 한다.
		return -1;
	}
		
	public static void main(String[] args){
		ShortTransferPlan T = new ShortTransferPlan();
		System.out.println(T.solution(new int[][]{{1, 2, 3, 4, 5, 6, 19}, {2, 7, 8, 13}, {5, 9, 10}, {9, 11, 12, 18}, {13, 14, 15}, {14, 12, 16, 17}}, 1, 12));
		System.out.println(T.solution(new int[][]{{1, 3, 5, 7}, {9, 3, 12}, {6, 5, 8}, {2, 8, 14, 15}, {2, 14, 16}}, 1, 14));
		System.out.println(T.solution(new int[][]{{7, 12},{5, 19},{7, 19},{9, 12, 13},{9, 5, 15}}, 9, 19));
		System.out.println(T.solution(new int[][]{{1, 2, 3, 4, 5},{9, 7, 10},{7, 6, 3, 8}, {5, 11, 8, 12}}, 1, 10));
	}
}