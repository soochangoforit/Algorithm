import java.util.*;

class MeetingRoomReview {
	public int solution(int n, int[][] meetings){
        int answer = 0;
        // 회의실 사용 빈도수를 저장할 배열
        int[] result = new int[n];
        

        // meeting 은 회의가 가장 먼저 시작하는 것으로 정렬을 수행한다.
        // meetings [회의시작 시간, 회의 끝나는 시간]
        Arrays.sort(meetings, (o1, o2) -> { return o1[0] - o2[0]; });

        // Priority Queue를 통해서 얻고자 하는 정보
        // 1. 회의가 끝나는 시간, 2. 사용한 회의실 번호
        // [회의가 끝나는 시간, 사용한 회의실 번호] 배열 형태로 관리하고자 한다.
        // 회의가 끝나는 시간이 이를순서가 우선순위가 높고, 끝나는 시간이 같으면 회의실 번호가 낮은게 우선 순위 높다.
        PriorityQueue<int[]> usingRoom = new PriorityQueue<>( (a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        TreeSet<Integer> availableRoom = new TreeSet<>();
        for(int i = 0; i < n; i++) availableRoom.add(i);


        // 본격적인 로직 시작
        // 우선 meetings를 먼저 순회해야 한다.
        for(int[] meeting : meetings){

            // Priority Queue에 있는 사용중인 회의들은 회의 시간이 끝나면, 따로 poll 해서 이용 가능한 상태로 만들어줘야 한다.
            // 회의 끝나는 시간이, 곧 시작할 회의보다 먼저 끝났다면 AvailableRoom에 넣어서, 곧 진행할 회의의 시작 시간부터 시작하고 끝내야 한다 (if으로 들어가야지)
            // 올바른 결과를 도출한다.
            while(!usingRoom.isEmpty() && usingRoom.peek()[0] < meeting[0]){
                int[] usedRoom = usingRoom.poll();
                availableRoom.add(usedRoom[1]);
            }


            // 이용 가능한 회의실이 있다면
            if (!availableRoom.isEmpty()){
                // 이용 가능한 회의실 번호를 뽑는다.
                int availableRoomNumber = availableRoom.pollFirst();

                // 회의실을 사용하고자 함으로 result 배열에 사용 빈도수를 증가시킨다.
                result[availableRoomNumber]++;

                // usingRoom에 회의실에사 회의하고 있다는 의미로 넣어준다.
                // [회의 끝나는 시간, 회의실 번호]
                usingRoom.add(new int[]{ meeting[1], availableRoomNumber });
            }

            // 이용 가능한 회의실이 없는 경우 (이용 가능한 회의실이 없다는 의미는 UsingRoom에 값이 있다는 의미)
            else {
                // Priority Queue 우선 순위에 맞게, 뽑아낸다. (회의가 끝나는 시간을 갱신하기 위해)
                int[] usedMeeting = usingRoom.poll();
                
                // 해당 강의실을 사용할 예정임으로, result 배열에 사용 횟수를 증가해준다.
                result[usedMeeting[1]]++;

                // 해당 강의실을 이용하고자 하고, 끝나는 시간을 갱신시켜서 usingRoom에 다시 넣어줘야 한다.
                usingRoom.add(new int[] { usedMeeting[0] + (meeting[1] - meeting[0]), usedMeeting[1] });
            }
        }
        
        int maxUsedCount = 0;
        for (int i = 0; i < n ; i++){
            if(result[i] > maxUsedCount){
                maxUsedCount = result[i];
                answer = i;
            }
        }




        return answer;
        
	}

	public static void main(String[] args){
		MeetingRoomReview T = new MeetingRoomReview();
		System.out.println(T.solution(2, new int[][]{{0, 5}, {2, 7}, {4, 5}, {7, 10}, {9, 12}}));
		System.out.println(T.solution(3, new int[][]{{3, 9}, {1, 10}, {5, 8}, {10, 15}, {9, 14}, {12, 14}, {15, 20}}));
		System.out.println(T.solution(3, new int[][]{{1, 30}, {2, 15}, {3, 10}, {4, 12}, {6, 10}}));
		System.out.println(T.solution(4, new int[][]{{3, 20}, {1, 25}, {5, 8}, {10, 15}, {9, 14}, {12, 14}, {15, 20}}));
	}
}