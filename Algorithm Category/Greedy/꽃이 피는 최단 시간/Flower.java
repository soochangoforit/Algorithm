import java.util.*;

class Flower {
	public int solution(int[] plantTime, int[] growTime){
		int answer = 0;

        int n = plantTime.length;
        
        // [심는데 걸리는 시간][성장하는데 걸리는 시간]
        int[][] flowers = new int[n][2];
        for (int i = 0; i < n ; i++) {
            flowers[i][0] = plantTime[i];
            flowers[i][1] = growTime[i];
        }

        // 성장하는데 오랜 시간이 걸리는 순으로 내림차순
        // 성장하는데 가장 오래 걸리는 꽃부터 심는게 좋다.
        Arrays.sort(flowers, (a,b) -> b[1] - a[1]);

        // start는 심은 날짜, end는 성장이 끝난 날짜
        int start = 0, end = 0;

        for(int[] flower : flowers) {
            end = start + flower[0] + flower[1];
            // 문제에서 원하는 최소 시간을 찾기 위해 우리는 이미 내림차순으로 정렬을 했다.
            // 심고 피기까지 최소한으로 시간이 걸리기 위해 심고자 하는 꽃 순서 안에서 걸리는 최대값을 찾아야 한다.
            // 최소값을 찾게 되면, 다른 식물이 아직 성장 중이지만 지금 심고 있는 식물이 먼저 다 성장했다고 다 끝난건 아니다.
            // 심고 피는 최종적인 시간을 최소화 하기 위해서 순서에 맞게 먼저 심어 놓은 식물이 다 자랄때 까지 기다려야 한다.
            answer = Math.max(end, answer);
            start += flower[0];
        }

		
		return answer;
	}

	public static void main(String[] args){
		Flower T = new Flower();
		System.out.println(T.solution(new int[]{1, 3, 2}, new int[]{2, 3, 2}));
		System.out.println(T.solution(new int[]{2, 1, 4, 3}, new int[]{2, 5, 3, 1}));
		System.out.println(T.solution(new int[]{1, 1, 1}, new int[]{7, 3, 2}));
		System.out.println(T.solution(new int[]{5, 7, 10, 15, 7, 3, 5}, new int[]{6, 7, 2, 10, 15, 6, 7}));
		System.out.println(T.solution(new int[]{1, 2, 3, 4, 5, 6, 7}, new int[]{7, 5, 4, 3, 2, 1, 6}));
	}
}