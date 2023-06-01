import java.util.*;

public class ShortPlan {

    public int solution(int n, int[][] flights, int s, int e, int k){


		// graph 양방향으로 만들기 (각각의 도시에 도달할 수 있는 비행 정보를 넣기 위해)
		// [ [해당 index에 갈 수 있는 도시 번호 뿐만 아니라 이동할 때 소요되는 비용도 관리해야 한다. ], [ ], [ ], ... ]
		// [ [[1도시, 10원], [2도시, 20원]] ..., [[3도시, 10원]] ]
		ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
		// 각각의 도시에 빈 배열이 들어갈 공간 마련
		for(int i = 0; i < n ; i++) {
			graph.add(new ArrayList<int[]>());
		}

		// 각각의 index 도시별로 도착 할 수 있는 초기 거리는 최대값으로 설정
		int[] costs = new int[n];
		Arrays.fill(costs, Integer.MAX_VALUE);

		// graph 에는 각 index 도시가 출발지가 되어 도착할 수 있는 도시까지 걸리는 요금을 관리한다.
		for(int[] flight : flights) {
			graph.get(flight[0]).add(new int[]{flight[1], flight[2]}); 
		}


		// 노드끼리 방향이 정해져 있어, 최소 거리 비용을 구하는 문제라서 다익스트라 알고리즘이라고 판단할 수 있다.
		// 환승의 제한이 없으면 다익스트라 알고리즘을 통해서 해결할 수 있지만, 현재 환승의 횟수가 제한되어 있기에 Level 을 통해서 접근해야 한다.
		Queue<int[]> Q = new LinkedList<>();
		// Q에 들어가는 값은 {현재 도시, 현재 도시까지 도달하는데 걸린 요금들의 합}
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

					// 이동거리까지 비용이 최소가 된다면, costs 배열에 값을 제일 작은 경로 값으로 업데이트 해준다.
					// 만약, if 문 밖에서 Q.offer(new int[]{targetCity, totalCostToCity}); 이게 있으면 해당 City까지 도달하기 위한 모든 (최단 거리 상관없이)
					// [도착 city, 총 비용] 이 Queue에 들어가게 된다.
					// 만약 if 문 안에서 다뤘다면, Q에는 문제에서 원하는 최소비용으로만 도달했을 경우만 Q에 배열이 들어가게 된다.
					// 우리는 결국 문제에서 가고자 하는 City까지 최소한의 비용으로 도달했을 경우 그 요금을 계산하기는 것이기에
					// Q에 target City까지 도달하기 위한 모든 경우의 수를 안넣어도 된다. 조금이라도 짧은 거리로 갈 수 있는 경우에만 Q에 넣으면 된다. 
					if(totalCostToCity < costs[targetCity]) {
						costs[targetCity] = totalCostToCity;
						Q.offer(new int[]{targetCity, totalCostToCity});
					}
				}
			}
			// Root 부모에서 자식 노드들이 곧 while문을 한번 더 돌아서 Q에서 poll 되는 경우 비행기를 탔다는 의미를 전달하기 위해 비행기 탄 횟수를 증가시킨다.
			flightRideCount++;

		
			// 곧 타게 될 비행기의 횟수가 환승 k 보다 크면 break 해서 while 종료
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
