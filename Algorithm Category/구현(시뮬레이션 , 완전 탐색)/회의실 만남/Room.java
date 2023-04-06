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

        // 강의실에 들어오고 나가는 부분을 시간 순서대로 관리하고자 한다.
        // 들어온 시간을 저장할 배열
        // enterTime[사람번호]=들어온 시간
        int[] enterTime = new int[n];

        // 나간 시간을 저장할 배열
        // exitTime[사람번호]=나간 시간
        int[] exitTime = new int[n];

        // 시간을 관리할 변수
        int cnt = 0;
        
        // i는 exit 배열을 순회
        // j 는 enter 배열을 순회
        // exit 처럼 나가기 위해서는 우선적으로 들어와야 한다.
        for(int i = 0, j = 0; i < n; i++){
            // exitHuman 은 나가고자 하는 사람 번호
            int exitHuman = exit[i];

            // j 는 나가고자 하는 사람이 몇번째로 들어왔는지 담는 변수 
            // enterTime 배열에 값을 할당
            while(j < n && j <= enterIdx[exitHuman]){
                int enterHuman = enter[j];

                // 0초 부터 시작하고자 한다.
                enterTime[enterHuman] = cnt++;

                // exitHuman 이 들어오고 바로 나가기 전에, 미리 들어온 사람에 대해 enterTime 에 cnt 값을 증가시킨다.
                j++;
            }
            
            // enterTime에 cnt 값을 다 반영하고 나서, exitHuman 을 나가게 한다.
            exitTime[exitHuman] = cnt++;

        }

        int[] answer = new int[n];

        // 0번 사람부터 n-1번 사람까지 비교하고자 한다.
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                // 완전히 만나지 않는 경우를 제외한 경우는 => 만났다고 판단
                if(!(exitTime[i] < enterTime[j] || exitTime[j] < enterTime[i])) {
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