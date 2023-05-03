import java.util.*;

class SpringCoolerReview {
	public int solution(int n, int[] nums){
		int answer = 0;

		// 각각의 스프링 쿨러가 물을 뿌리는 범위를 2중 배열로 관리
		int[][] coolerRange = new int[nums.length][2];
		for(int i = 0; i < nums.length; i++) {
			coolerRange[i][0] = Math.max(0, i - nums[i]);
			coolerRange[i][1] = Math.min(n, i + nums[i]);
		}

		// 각각의 스프링 쿨러가 물을 뿌리는 시작점을 기점으로 오름 차순 정렬
		Arrays.sort(coolerRange, (a,b) -> a[0] - b[0]);

		int start = 0, end = 0, i = 0;

		while(i < coolerRange.length) {
			// 물을 뿌리는 시작점이 다른 스프링 쿨러가 나올때 까지 while 반복
			while (i < coolerRange.length && coolerRange[i][0] <= start) {
				end = Math.max(end, coolerRange[i][1]);
				i++;
			}
			
			// while 문을 빠져나왔다는 의미는 물을 주기 시작하는 start 위치를 새롭게 update 해줘야 함을 의미한다.
			answer++;

			// 만약 while 문을 한번도 돌지 않은 상태라면 start == end는 같은 상태
			// 같은 의미로 다음 start 시점에 위치하는 스프링 쿨러에 의해 잔디 밭에 물을 주지 못 한 부분이 발생 (-1 반환 필요)
			if (start == end) return -1;
			
			// 만약, end가 잔디 밭의 끝까지 도달 했다면 answer를 이른 return 한다.
			if (end == n) return answer;

			// 위의 2가지 if문으로 들아가지 않았다면, 다음 start 위치의 스프링 쿨러를 시작할 때
			// start 위치를 end 위치로 끌어 올려줘야 한다. (잔디밭에 물을 뿌릴 수 있는 특정 위치에서 최대로 멀리 뿌릴 수 있는 쿨러를 사용했다면)
			// 만약 end 지점부터 start로 하는 다음 스프링 쿨러가 있다고 가정하면, start를 end까지 올려주지 않으면 
			// 위의 로직 while이 돌지 않고 그렇게 되면 if(start == end) 조건에 의해 원치 않는 -1을 return 하게 된다.
			start = end;
		}

	

		return answer;
	}

	public static void main(String[] args){
		SpringCoolerReview T = new SpringCoolerReview();
		System.out.println(T.solution(8, new int[]{1, 1, 1, 2, 1, 1, 2, 1, 1}));
		System.out.println(T.solution(4, new int[]{1, 2, 2, 0, 0}));		
		System.out.println(T.solution(5, new int[]{2, 0, 0, 0, 0, 2}));
		System.out.println(T.solution(11, new int[]{1, 2, 3, 1, 2, 1, 1, 2, 1, 1, 1, 1}));
	}
}