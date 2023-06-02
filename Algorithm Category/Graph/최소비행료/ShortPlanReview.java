import java.util.*;

class ShortPlanReview {
	public int solution(int n, int[][] flights, int s, int e, int k){


        // 각각의 도시가 출발지가 되어 이동할 수 있는 비향 정보를 저장하고 있어야 한다. (3중 배열 활용)
        // 해당 배열을 통해서 자신의 출발지로 이동할 수 있는 비행 정보를 Q에 넣을 수 있다.
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        for(int i = 0; i < n ; i++) {
			graph.add(new ArrayList<int[]>());
		}

        
        for(int i = 0; i < flights.length ; i++) {
            int startPort = flights[i][0];
            int arrivePort = flights[i][1];
            int fee = flights[i][2];

            graph.get(startPort).add(new int[]{arrivePort, fee});
        }

        // 최종적으로 해당 목적지에 도착하기까지 소요된 최소한의 비용을 넣을 것이다.
        // 초기값에는 최대 값으로 할당한다.
        int[] costs = new int[n];
        Arrays.fill(costs, Integer.MAX_VALUE);


        // Q를 통해 출발지 s 부터 이동가능한 비행 정보를 graph 배열에서 참고해서 Q에 넣는다.
        Queue<int[]> Q = new LinkedList<>();
        Q.offer(new int[]{s,0});
        costs[s] = 0;

        int flightRideCount = 0;

        while(!Q.isEmpty()) {
            int Qsize = Q.size();

            for(int i = 0; i < Qsize; i++) {
                int[] cur = Q.poll();

                int startPort = cur[0];
                int feeUntilStart = cur[1];

                for(int[] flightInfo : graph.get(startPort)) {
                    int targetPort = flightInfo[0];
                    int feeToTarget = flightInfo[1];

                    int totalFeeToTarget = feeUntilStart + feeToTarget;

                    // 해당 targetPort까지 도달하기 위한 최소한의 거리로 이동한 경우에 한해서만 Q에 값을 넣어준다.
                    // Q에 값을 넣어줌과 동시에 costs 배열을 update 해준다.
                    // 만약, 해당 targetPort까지 최소한의 경우말고 다른 경우에 대해서도 Q에 값을 넣으면 targetPort까지 도달할 수 있는 모든 경우의수를 고려하게 된다.
                    // 문제에서 원하는 건, 최소한의 경우에서만 알고 싶어한다.
                    // 최소한으로 만족하지 않는다 하면 Q에 넣지 않는다.
                    if (totalFeeToTarget < costs[targetPort]) {
                        costs[targetPort] = totalFeeToTarget;
                        Q.offer(new int[]{targetPort, totalFeeToTarget});
                    }
                }
            }
            flightRideCount++;
            // 만약 if 문이 for 문 안에 있다면 for문은 break를 통해 빠져나오겠지만, Q에는 아직 값이 있기에 Q에 모든 노드가 빠져 나올때까지 while을 순회한다.
            // 하지만, 로직 상 자식 노드가 Q에 들어가면서 우리가 얻고자 하는 costs 정보를 업데이트 하기에
            // 해당 시점에서 if문을 통해 break 하여 while 문을 종료하고자 한다.
            if(flightRideCount > k) break;
        }


        if (costs[e] == Integer.MAX_VALUE) return -1;
        else return costs[e];

	}
		
	public static void main(String[] args){
		ShortPlanReview T = new ShortPlanReview();
		System.out.println(T.solution(5, new int[][]{{0, 1, 10}, {1, 2, 20}, {0, 2, 70}, {0, 3, 100}, {1, 3, 80}, {2, 3, 10}, {2, 4, 30}, {3, 4, 10}}, 0, 3, 1));
		System.out.println(T.solution(4, new int[][]{{0, 1, 10}, {0, 2, 10}, {1, 3, 5}, {2, 3, 3}}, 0, 3, 0));
		System.out.println(T.solution(8, new int[][]{{0, 3, 10}, {1, 5, 10}, {1, 7, 100}, {0, 1, 10}, {0, 2, 10}, {5, 7, 30}, {3, 7, 10}, {1, 3, 5}, {2, 3, 3}}, 1, 7, 2));
		System.out.println(T.solution(10, new int[][]{{1, 8, 50}, {0, 8, 30}, {1, 0, 10}, {2, 8, 10}, {0, 3, 10}, {1, 5, 10}, {1, 7, 100}, {0, 1, 10}, {0, 2, 10}, {5, 7, 30}, {3, 7, 10}, {1, 3, 5}, {2, 3, 3}}, 1, 8, 2));
	}
}