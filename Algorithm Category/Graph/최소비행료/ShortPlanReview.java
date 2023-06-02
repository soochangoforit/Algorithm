import java.util.*;

class ShortPlanReview {
	public int solution(int n, int[][] flights, int s, int e, int k){


        // ������ ���ð� ������� �Ǿ� �̵��� �� �ִ� ���� ������ �����ϰ� �־�� �Ѵ�. (3�� �迭 Ȱ��)
        // �ش� �迭�� ���ؼ� �ڽ��� ������� �̵��� �� �ִ� ���� ������ Q�� ���� �� �ִ�.
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

        // ���������� �ش� �������� �����ϱ���� �ҿ�� �ּ����� ����� ���� ���̴�.
        // �ʱⰪ���� �ִ� ������ �Ҵ��Ѵ�.
        int[] costs = new int[n];
        Arrays.fill(costs, Integer.MAX_VALUE);


        // Q�� ���� ����� s ���� �̵������� ���� ������ graph �迭���� �����ؼ� Q�� �ִ´�.
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

                    // �ش� targetPort���� �����ϱ� ���� �ּ����� �Ÿ��� �̵��� ��쿡 ���ؼ��� Q�� ���� �־��ش�.
                    // Q�� ���� �־��ܰ� ���ÿ� costs �迭�� update ���ش�.
                    // ����, �ش� targetPort���� �ּ����� ��츻�� �ٸ� ��쿡 ���ؼ��� Q�� ���� ������ targetPort���� ������ �� �ִ� ��� ����Ǽ��� ����ϰ� �ȴ�.
                    // �������� ���ϴ� ��, �ּ����� ��쿡���� �˰� �;��Ѵ�.
                    // �ּ������� �������� �ʴ´� �ϸ� Q�� ���� �ʴ´�.
                    if (totalFeeToTarget < costs[targetPort]) {
                        costs[targetPort] = totalFeeToTarget;
                        Q.offer(new int[]{targetPort, totalFeeToTarget});
                    }
                }
            }
            flightRideCount++;
            // ���� if ���� for �� �ȿ� �ִٸ� for���� break�� ���� ��������������, Q���� ���� ���� �ֱ⿡ Q�� ��� ��尡 ���� ���ö����� while�� ��ȸ�Ѵ�.
            // ������, ���� �� �ڽ� ��尡 Q�� ���鼭 �츮�� ����� �ϴ� costs ������ ������Ʈ �ϱ⿡
            // �ش� �������� if���� ���� break �Ͽ� while ���� �����ϰ��� �Ѵ�.
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