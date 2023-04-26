import java.util.*;

class MeetingRoom {
	public int solution(int n, int[][] meetings){
        int answer = 0;
        // 회의실 사용 빈도수를 저장할 배열
        int[] result = new int[n];

        // meeting 은 회의가 가장 먼저 시작하는 것으로 정렬을 수행한다.
        Arrays.sort(meetings, (o1, o2) -> { return o1[0] - o2[0]; });

        // 현재 이용중인 회의실을 Priorty Queue로 관리한다.
        // [종료시간, 회의실 번호] 이렇게 들어간다.
        // 1. 회의 종료 시간이 빠른걸 우선순위를 높게 잡아서, 이용 가능하도록 한다.
        // 2. 회의 종료 시간이 동일한 경우, 회의실 번호가 작은것 부터 이용 가능하다.
        PriorityQueue<int[]> usingRoom = new PriorityQueue<>((a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        // 이용 가능한 회의실
        // TreeSet 자료구조는 자료를 추가하거나 자료를 제거할 때 O(log n)으로 오름차순 자동 정렬을 해준다.
        // n개의 회의실이 사용 가능하다고 하면, 번호가 작은 회의실을 먼저 사용하도록 해야 한다. 
        TreeSet<Integer> availableRoom = new TreeSet<>();
        for(int i = 0 ; i < n ; i++) availableRoom.add(i);


        // 본격적인 코드 시작
        for(int[] meeting : meetings){

            // 새롭게 회의가 시작하기 전, 이미 끝난 회의들은 availableRoom에 넣어준다.
            // usingRoom에 어떠한 회의가 있고 사용하고 있는 회의실 끝나는 시간이 < 곧 회의할 시간보다 먼저 끝났다면
            while(!usingRoom.isEmpty() && usingRoom.peek()[0] < meeting[0]) {
                // 이미 회의가 끝난 회의실 번호를 availableRoom에 넣는다.
                int usedRoomNumber = usingRoom.poll()[1];
                availableRoom.add(usedRoomNumber);
            }



            // available 방이 비어있지 않고, 이용가능한 방 하나를 꺼내온다.
            // 회의실이 이용 가능하기에, 끝나는 시간은 해당 회의가 끝나는 시점으로 맞춘다.
            if(!availableRoom.isEmpty()){
                int availableRoomNumber = availableRoom.pollFirst();

                // 해당 회의실을 사용했음으로, count 해준다.
                result[availableRoomNumber]++;

                usingRoom.add( new int[]{ meeting[1], availableRoomNumber } );
            }

            // 현재 이용 가능한 회의실이 없다면, 시작하고자 하는 회의보다 빨리 끝나는 회의가 아직 없다는 의미
            // 우선 순위 높은 현재 회의하고 있는 값을 꺼내서 다음 회의를 이용할 수 있도록 한다.
            else {
                // 현재 time이라는 변수를 통해서, 시간이 흐르기까지 기다리는 방향으로 구현하지 않기에
                // 현재 이용중인 회의 중에서, 우선 순위에 맞게 꺼내고, 새롭게 갱신해준다.
                int[] usedMeeting = usingRoom.poll();

                // 해당 회의실을 사용했음으로, count 해준다.
                result[usedMeeting[1]]++;

                // 진행하고자 하는 회의를 UsingRoom에 새롭게 넣어준다.
                usingRoom.add( new int[] { usedMeeting[0] + (meeting[1] - meeting[0]) , usedMeeting[1] } );
            }

        }


        // result 배열중에서 max 값을 찾아, 회의실 번호를 반환한다.
        int maxUseCount = 0;
        for (int i = 0; i < n ; i++){
            if(result[i] > maxUseCount){
                maxUseCount = result[i];
                answer = i;
            }
        }






        return answer;
        
	}

	public static void main(String[] args){
		MeetingRoom T = new MeetingRoom();
		System.out.println(T.solution(2, new int[][]{{0, 5}, {2, 7}, {4, 5}, {7, 10}, {9, 12}}));
		System.out.println(T.solution(3, new int[][]{{3, 9}, {1, 10}, {5, 8}, {10, 15}, {9, 14}, {12, 14}, {15, 20}}));
		System.out.println(T.solution(3, new int[][]{{1, 30}, {2, 15}, {3, 10}, {4, 12}, {6, 10}}));
		System.out.println(T.solution(4, new int[][]{{3, 20}, {1, 25}, {5, 8}, {10, 15}, {9, 14}, {12, 14}, {15, 20}}));
	}
}