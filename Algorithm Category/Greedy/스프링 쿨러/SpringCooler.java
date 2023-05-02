import java.util.*;

class SpringCooler {
	public int solution(int n, int[] nums){
		int answer = 0;

		// 각각의 스프링 쿨러가 물을 뿌리는 왼쪽~오른쪽 범위를 저장하는 배열
		int[][] coolerRange = new int[nums.length][2];
		for (int i = 0; i < nums.length; i++){
			// 0보다 작은 범위 값이면 최대 0으로 할당 
			coolerRange[i][0] = Math.max(0, i - nums[i]);
			// 잔디 밭 n 사이즈보다 큰 범위 값이면 최대 n으로 할당
			coolerRange[i][1] = Math.min(n, i + nums[i]);
		}

		// 물을 뿌리는 범위의 왼쪽 끝을 기준으로 오름차순 정렬
		// 오름 차순 하는 이유는 물을 뿌리지 못 하는 잔디 밭에 대해 쉽게 판단할 수 있다.
		Arrays.sort(coolerRange, (a,b) -> a[0] - b[0]);

		// start 물이 뿌려지는 범위의 왼쪽 끝, end 물이 뿌려지는 범위의 오른쪽 끝
		int start = 0, end = 0, i = 0;

		// 마지막 쿨러까지 일단 순회를 시도한다.
		while(i < coolerRange.length){
			// i <= coolerRange.length 가 참이면, 아직 끝까지 순회하지 못 했다는 의미이다.
			// 처음 : start(0) 지점부터 가장 길게 오른쪽으로 물을 뿌릴 수 있는 범위를 찾는다.
			// 비록 start 지점보다 왼쪽 끝에서 물을 뿌리는 스프링 쿨러가 있을 수 있지만,
			// start 지점부터 가장 길게 오른쪽으로 물을 뿌릴 수 있는 범위를 찾는게 최소 개수를 사용하는 방법이다. (이미 뿌렸다고 해서 문제될 부분은 없다.)
			while (i < coolerRange.length && coolerRange[i][0] <= start){
				end = Math.max(end, coolerRange[i][1]);
				i++;
			}
			// 필요한 스프링 쿨러 1개 증가시키고
			answer++;

			// 오른쪽 끝인 end가 n이면 물을 다 뿌렸다는 의미
			if (end == n) return answer;

			// 위의 while에 의해서 start를 포함해서 물을 뿌리는 범위가 line[i][0] <= start 를 벗어나서 한참 뒤에서 뿌린다면
			// while 문 안에서 end가 업데이트 되어야 하는데, 그러지 못 한다. 그렇게 되면 결과적으로 end 와 start가 같아진다.
			// 그 말인 즉슨, 물을 뿌리지 못하는 범위가 있다는 의미이다. "-1" 을 return 해야한다.
			if (start == end) return -1;


			// 최소 개수로 사용하기 위해선, end 지점부터 물을 뿌는게 좋다.
			start = end;
			
		}



		return answer;
	}

	public static void main(String[] args){
		SpringCooler T = new SpringCooler();
		System.out.println(T.solution(8, new int[]{1, 1, 1, 2, 1, 1, 2, 1, 1}));
		System.out.println(T.solution(4, new int[]{1, 2, 2, 0, 0}));		
		System.out.println(T.solution(5, new int[]{2, 0, 0, 0, 0, 2}));
		System.out.println(T.solution(11, new int[]{1, 2, 3, 1, 2, 1, 1, 2, 1, 1, 1, 1}));
	}
}