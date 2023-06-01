import java.util.*;

public class ShortPlan {

    public int solution(int n, int[][] flights, int s, int e, int k){


		// graph ��������� ����� (������ ���ÿ� ������ �� �ִ� ���� ������ �ֱ� ����)
		// [ [�ش� index�� �� �� �ִ� ���� ��ȣ �Ӹ� �ƴ϶� �̵��� �� �ҿ�Ǵ� ��뵵 �����ؾ� �Ѵ�. ], [ ], [ ], ... ]
		// [ [[1����, 10��], [2����, 20��]] ..., [[3����, 10��]] ]
		ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
		// ������ ���ÿ� �� �迭�� �� ���� ����
		for(int i = 0; i < n ; i++) {
			graph.add(new ArrayList<int[]>());
		}

		// ������ index ���ú��� ���� �� �� �ִ� �ʱ� �Ÿ��� �ִ밪���� ����
		int[] costs = new int[n];
		Arrays.fill(costs, Integer.MAX_VALUE);

		// graph ���� �� index ���ð� ������� �Ǿ� ������ �� �ִ� ���ñ��� �ɸ��� ����� �����Ѵ�.
		for(int[] flight : flights) {
			graph.get(flight[0]).add(new int[]{flight[1], flight[2]}); 
		}


		// ��峢�� ������ ������ �־�, �ּ� �Ÿ� ����� ���ϴ� ������ ���ͽ�Ʈ�� �˰����̶�� �Ǵ��� �� �ִ�.
		// ȯ���� ������ ������ ���ͽ�Ʈ�� �˰����� ���ؼ� �ذ��� �� ������, ���� ȯ���� Ƚ���� ���ѵǾ� �ֱ⿡ Level �� ���ؼ� �����ؾ� �Ѵ�.
		Queue<int[]> Q = new LinkedList<>();
		// Q�� ���� ���� {���� ����, ���� ���ñ��� �����ϴµ� �ɸ� ��ݵ��� ��}
		Q.offer(new int[]{s,0});
		costs[s] = 0;
		int flightRideCount = 0;


		while(!Q.isEmpty()) {
			int Qsize = Q.size();

			for(int i = 0; i < Qsize; i++) {
				int[] cur = Q.poll();

				int curCity = cur[0];
				int curFeeUntilNow = cur[1];

				for(int[] canGotoCityWithFee : graph.get(curCity)) {
					
					int targetCity = canGotoCityWithFee[0];
					int targetCityFee = canGotoCityWithFee[1];

					int totalCostToCity = curFeeUntilNow + targetCityFee;

					// �̵��Ÿ����� ����� �ּҰ� �ȴٸ�, costs �迭�� ���� ���� ���� ��� ������ ������Ʈ ���ش�.
					// ����, if �� �ۿ��� Q.offer(new int[]{targetCity, totalCostToCity}); �̰� ������ �ش� City���� �����ϱ� ���� ��� (�ִ� �Ÿ� �������)
					// [���� city, �� ���] �� Queue�� ���� �ȴ�.
					// ���� if �� �ȿ��� �ٷ�ٸ�, Q���� �������� ���ϴ� �ּҺ�����θ� �������� ��츸 Q�� �迭�� ���� �ȴ�.
					// �츮�� �ᱹ �������� ������ �ϴ� City���� �ּ����� ������� �������� ��� �� ����� ����ϱ�� ���̱⿡
					// Q�� target City���� �����ϱ� ���� ��� ����� ���� �ȳ־ �ȴ�. �����̶� ª�� �Ÿ��� �� �� �ִ� ��쿡�� Q�� ������ �ȴ�. 
					if(totalCostToCity < costs[targetCity]) {
						costs[targetCity] = totalCostToCity;
						Q.offer(new int[]{targetCity, totalCostToCity});
					}
				}
			}
			// Root �θ𿡼� �ڽ� ������ �� while���� �ѹ� �� ���Ƽ� Q���� poll �Ǵ� ��� ����⸦ ���ٴ� �ǹ̸� �����ϱ� ���� ����� ź Ƚ���� ������Ų��.
			flightRideCount++;

		
			// �� Ÿ�� �� ������� Ƚ���� ȯ�� k ���� ũ�� break �ؼ� while ����
			//if(flightRideCount == k + 1) break;
			if(flightRideCount > k) break;
			

		}


		if(costs[e] == Integer.MAX_VALUE) return -1;
		else return costs[e];

	}
		
	
		
	public static void main(String[] args){
		ShortPlan T = new ShortPlan();
		System.out.println(T.solution(5, new int[][]{{0, 1, 10}, {1, 2, 20}, {0, 2, 70}, {0, 3, 100}, {1, 3, 80}, {2, 3, 10}, {2, 4, 30}, {3, 4, 10}}, 0, 3, 1));
		System.out.println(T.solution(4, new int[][]{{0, 1, 10}, {0, 2, 10}, {1, 3, 5}, {2, 3, 3}}, 0, 3, 0));
		System.out.println(T.solution(8, new int[][]{{0, 3, 10}, {1, 5, 10}, {1, 7, 100}, {0, 1, 10}, {0, 2, 10}, {5, 7, 30}, {3, 7, 10}, {1, 3, 5}, {2, 3, 3}}, 1, 7, 2));
		System.out.println(T.solution(10, new int[][]{{1, 8, 50}, {0, 8, 30}, {1, 0, 10}, {2, 8, 10}, {0, 3, 10}, {1, 5, 10}, {1, 7, 100}, {0, 1, 10}, {0, 2, 10}, {5, 7, 30}, {3, 7, 10}, {1, 3, 5}, {2, 3, 3}}, 1, 8, 2));
	}
}
