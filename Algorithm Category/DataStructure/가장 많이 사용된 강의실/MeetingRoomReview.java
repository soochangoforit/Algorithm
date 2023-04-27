import java.util.*;

class MeetingRoomReview {
	public int solution(int n, int[][] meetings){
        int answer = 0;
        // ȸ�ǽ� ��� �󵵼��� ������ �迭
        int[] result = new int[n];
        

        // meeting �� ȸ�ǰ� ���� ���� �����ϴ� ������ ������ �����Ѵ�.
        // meetings [ȸ�ǽ��� �ð�, ȸ�� ������ �ð�]
        Arrays.sort(meetings, (o1, o2) -> { return o1[0] - o2[0]; });

        // Priority Queue�� ���ؼ� ����� �ϴ� ����
        // 1. ȸ�ǰ� ������ �ð�, 2. ����� ȸ�ǽ� ��ȣ
        // [ȸ�ǰ� ������ �ð�, ����� ȸ�ǽ� ��ȣ] �迭 ���·� �����ϰ��� �Ѵ�.
        // ȸ�ǰ� ������ �ð��� �̸������� �켱������ ����, ������ �ð��� ������ ȸ�ǽ� ��ȣ�� ������ �켱 ���� ����.
        PriorityQueue<int[]> usingRoom = new PriorityQueue<>( (a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        TreeSet<Integer> availableRoom = new TreeSet<>();
        for(int i = 0; i < n; i++) availableRoom.add(i);


        // �������� ���� ����
        // �켱 meetings�� ���� ��ȸ�ؾ� �Ѵ�.
        for(int[] meeting : meetings){

            // Priority Queue�� �ִ� ������� ȸ�ǵ��� ȸ�� �ð��� ������, ���� poll �ؼ� �̿� ������ ���·� �������� �Ѵ�.
            // ȸ�� ������ �ð���, �� ������ ȸ�Ǻ��� ���� �����ٸ� AvailableRoom�� �־, �� ������ ȸ���� ���� �ð����� �����ϰ� ������ �Ѵ� (if���� ������)
            // �ùٸ� ����� �����Ѵ�.
            while(!usingRoom.isEmpty() && usingRoom.peek()[0] < meeting[0]){
                int[] usedRoom = usingRoom.poll();
                availableRoom.add(usedRoom[1]);
            }


            // �̿� ������ ȸ�ǽ��� �ִٸ�
            if (!availableRoom.isEmpty()){
                // �̿� ������ ȸ�ǽ� ��ȣ�� �̴´�.
                int availableRoomNumber = availableRoom.pollFirst();

                // ȸ�ǽ��� ����ϰ��� ������ result �迭�� ��� �󵵼��� ������Ų��.
                result[availableRoomNumber]++;

                // usingRoom�� ȸ�ǽǿ��� ȸ���ϰ� �ִٴ� �ǹ̷� �־��ش�.
                // [ȸ�� ������ �ð�, ȸ�ǽ� ��ȣ]
                usingRoom.add(new int[]{ meeting[1], availableRoomNumber });
            }

            // �̿� ������ ȸ�ǽ��� ���� ��� (�̿� ������ ȸ�ǽ��� ���ٴ� �ǹ̴� UsingRoom�� ���� �ִٴ� �ǹ�)
            else {
                // Priority Queue �켱 ������ �°�, �̾Ƴ���. (ȸ�ǰ� ������ �ð��� �����ϱ� ����)
                int[] usedMeeting = usingRoom.poll();
                
                // �ش� ���ǽ��� ����� ����������, result �迭�� ��� Ƚ���� �������ش�.
                result[usedMeeting[1]]++;

                // �ش� ���ǽ��� �̿��ϰ��� �ϰ�, ������ �ð��� ���Ž��Ѽ� usingRoom�� �ٽ� �־���� �Ѵ�.
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