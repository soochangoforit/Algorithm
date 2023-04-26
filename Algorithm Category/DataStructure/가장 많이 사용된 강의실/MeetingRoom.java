import java.util.*;

class MeetingRoom {
	public int solution(int n, int[][] meetings){
        int answer = 0;
        // ȸ�ǽ� ��� �󵵼��� ������ �迭
        int[] result = new int[n];

        // meeting �� ȸ�ǰ� ���� ���� �����ϴ� ������ ������ �����Ѵ�.
        Arrays.sort(meetings, (o1, o2) -> { return o1[0] - o2[0]; });

        // ���� �̿����� ȸ�ǽ��� Priorty Queue�� �����Ѵ�.
        // [����ð�, ȸ�ǽ� ��ȣ] �̷��� ����.
        // 1. ȸ�� ���� �ð��� ������ �켱������ ���� ��Ƽ�, �̿� �����ϵ��� �Ѵ�.
        // 2. ȸ�� ���� �ð��� ������ ���, ȸ�ǽ� ��ȣ�� ������ ���� �̿� �����ϴ�.
        PriorityQueue<int[]> usingRoom = new PriorityQueue<>((a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        // �̿� ������ ȸ�ǽ�
        // TreeSet �ڷᱸ���� �ڷḦ �߰��ϰų� �ڷḦ ������ �� O(log n)���� �������� �ڵ� ������ ���ش�.
        // n���� ȸ�ǽ��� ��� �����ϴٰ� �ϸ�, ��ȣ�� ���� ȸ�ǽ��� ���� ����ϵ��� �ؾ� �Ѵ�. 
        TreeSet<Integer> availableRoom = new TreeSet<>();
        for(int i = 0 ; i < n ; i++) availableRoom.add(i);


        // �������� �ڵ� ����
        for(int[] meeting : meetings){

            // ���Ӱ� ȸ�ǰ� �����ϱ� ��, �̹� ���� ȸ�ǵ��� availableRoom�� �־��ش�.
            // usingRoom�� ��� ȸ�ǰ� �ְ� ����ϰ� �ִ� ȸ�ǽ� ������ �ð��� < �� ȸ���� �ð����� ���� �����ٸ�
            while(!usingRoom.isEmpty() && usingRoom.peek()[0] < meeting[0]) {
                // �̹� ȸ�ǰ� ���� ȸ�ǽ� ��ȣ�� availableRoom�� �ִ´�.
                int usedRoomNumber = usingRoom.poll()[1];
                availableRoom.add(usedRoomNumber);
            }



            // available ���� ������� �ʰ�, �̿밡���� �� �ϳ��� �����´�.
            // ȸ�ǽ��� �̿� �����ϱ⿡, ������ �ð��� �ش� ȸ�ǰ� ������ �������� �����.
            if(!availableRoom.isEmpty()){
                int availableRoomNumber = availableRoom.pollFirst();

                // �ش� ȸ�ǽ��� �����������, count ���ش�.
                result[availableRoomNumber]++;

                usingRoom.add( new int[]{ meeting[1], availableRoomNumber } );
            }

            // ���� �̿� ������ ȸ�ǽ��� ���ٸ�, �����ϰ��� �ϴ� ȸ�Ǻ��� ���� ������ ȸ�ǰ� ���� ���ٴ� �ǹ�
            // �켱 ���� ���� ���� ȸ���ϰ� �ִ� ���� ������ ���� ȸ�Ǹ� �̿��� �� �ֵ��� �Ѵ�.
            else {
                // ���� time�̶�� ������ ���ؼ�, �ð��� �帣����� ��ٸ��� �������� �������� �ʱ⿡
                // ���� �̿����� ȸ�� �߿���, �켱 ������ �°� ������, ���Ӱ� �������ش�.
                int[] usedMeeting = usingRoom.poll();

                // �ش� ȸ�ǽ��� �����������, count ���ش�.
                result[usedMeeting[1]]++;

                // �����ϰ��� �ϴ� ȸ�Ǹ� UsingRoom�� ���Ӱ� �־��ش�.
                usingRoom.add( new int[] { usedMeeting[0] + (meeting[1] - meeting[0]) , usedMeeting[1] } );
            }

        }


        // result �迭�߿��� max ���� ã��, ȸ�ǽ� ��ȣ�� ��ȯ�Ѵ�.
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