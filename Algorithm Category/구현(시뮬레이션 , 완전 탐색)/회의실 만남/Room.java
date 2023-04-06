import java.util.*;

class Room {
	public int[] solution(int[] enter, int[] exit){
 

        // 1번째 사람이 들어오는 걸 index 기준으로 맞추기
        int n = enter.length;

        for(int i = 0; i < n; i++){
            enter[i]--;
            exit[i]--;
        }

        // 0부터 ~ n 까지의 사람들이 몇번째로 들어왔는지 저장할 배열
        // enterIdx[사람번호] = 몇번째로 들어왔는지
        int[] enterIdx = new int[n];
        for(int i =0; i<n; i++){
            enterIdx[enter[i]] = i;
        }

        // 들어온 시간을 저장할 배열
        int[] enterTime = new int[n];
        // 나간 시간을 저장할 배열
        int[] exitTime = new int[n];

        int cnt = 0;
        for(int i = 0, j = 0; i < n; i++){
            while(j < n && j <= enterIdx[exit[i]]){
                // 0부터 j까지의 사람들이 들어온 시간을 저장
                enterTime[enter[j]] = cnt++;
                j++;
            }
            exitTime[exit[i]] = cnt++;
        }

        int[] answer = new int[n];
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                if(!(exitTime[i] < enterTime[j] || exitTime[j] < enterTime[i])){
                    answer[i]++;
                    answer[j]++;
                }
            }
        }
		
		return answer;
	}
		
	public static void main(String[] args){
		Room T = new Room();
		System.out.println(Arrays.toString(T.solution(new int[]{1, 2, 3, 4}, new int[]{2, 4, 1, 3})));
		System.out.println(Arrays.toString(T.solution(new int[]{1, 2, 5, 3, 4}, new int[]{2, 3, 1, 4, 5})));
		System.out.println(Arrays.toString(T.solution(new int[]{1, 3, 2, 4, 5, 7, 6, 8}, new int[]{2, 3, 5, 6, 1, 4, 8, 7})));
		System.out.println(Arrays.toString(T.solution(new int[]{1, 4, 7, 2, 3, 5, 6}, new int[]{5, 2, 6, 1, 7, 3, 4})));
		System.out.println(Arrays.toString(T.solution(new int[]{1, 4, 2, 3}, new int[]{2, 1, 4, 3})));
	}
}