import java.util.*;

class ShortTransferPlan {

	public int solution(int[][] routes, int s, int e){
		int answer = 0;

        // key : 역, value = {해당 역을 지나는 노선 번호}
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();

        int countOfLine = routes.length;

        for(int line = 0; line < countOfLine; line++){
			for(int station : routes[line]){
				graph.putIfAbsent(station, new HashSet<Integer>());
				graph.get(station).add(line);
			}
		}

        int[] ch = new int[countOfLine];


        Queue<Integer> Q = new LinkedList<>();
        Q.offer(s);
        int rideOfLine = 0;

        while(!Q.isEmpty()) {
            int Qsize = Q.size();

            for(int i = 0; i < Qsize; i++) {
                int station = Q.poll();

                HashSet<Integer> lines = graph.get(station);

                for(int line : lines) {
                    if(ch[line] == 0) {
                        ch[line] = 1;

                        for(int nextStation : routes[line]) {
                            if(nextStation == e) {
                                return rideOfLine;
                            }
                            Q.offer(nextStation);
                        }

                    }
                }
            }
            rideOfLine++;
        }
		
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