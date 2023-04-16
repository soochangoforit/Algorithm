import java.util.*;


class Skin {

    public int getMinTime(String time){
        int hour = Integer.parseInt(time.split(":")[0]);
        int min = Integer.parseInt(time.split(":")[1]);

        return hour * 60 + min;
    }



	public int solution(int[] laser, String[] enter){
		int answer = 0;


        int size = enter.length;

        // minAndLaserIndex[i][0] : i��° �մ��� ���� �ð� (�� ���� => 1�ð��� 60�� ������ ���)
        // minAndLaserIndex[i][1] : i��° �մ��� ���ϴ� ������ �ü� ��ȣ
        int[][] minAndLaserIndex = new int[size][2];
        for(int i = 0; i < size; i ++){
            minAndLaserIndex[i][0] = getMinTime(enter[i].split(" ")[0]);
            minAndLaserIndex[i][1] = Integer.parseInt(enter[i].split(" ")[1]);
        }


        // waiting Room ���� ����ϴ� ����� �°��� �ϴ� ������ �ü� ��ȣ�� �ִ´�.
        Queue<Integer> waitingRoom = new LinkedList<>();

        // ù��° �˻�(ó������ �湮�� ����� �ü��� ������ �ð�)�� ����Ǵ� �ð����� �ʱ�ȭ�� �Ѵ�.
        int finalTime = minAndLaserIndex[0][0] + laser[minAndLaserIndex[0][1]];

        // ù��° ����� ����ǿ� ������ �ð� (ù��° ����� ������ �ð��̶� ����.)
        int firstCustomerTime = minAndLaserIndex[0][0];

        // enter �迭���� 1��° index ���� �����Ѵ�.
        int i = 1;
        
        // ù��° ���� ������ �ð�(��)���� ~ 20�� (1200��) ���� �˻縦 �����Ѵ�.
        for(int time = firstCustomerTime; time <= 1200 ; time++) {

            // i��° ����� size ���ڸ�ŭ ����� break�� ���ؼ� for���� Ż���Ѵ�.
            if(i >= size) break;

            // �ð��� �Ǿ, ����� �ð��� �մ��� �� ���
            if (time == minAndLaserIndex[i][0]) {

                // �Դµ� ���� ����� �ü��� ������ �ʴ� ���, ���ǿ� ���� �Ѵ�.
                if(finalTime >  minAndLaserIndex[i][0]) {
                    waitingRoom.offer(minAndLaserIndex[i][1]);

                    // ���� ����ϰ� �ִ� �ο��� �� ���� ���� �ο����� answer�� �����Ѵ�.
                    answer = Math.max(answer, waitingRoom.size());
                }

                // �Դµ� ���� ����� �ü��� ���� ���, ��⿭�� ���� �ʰ� �ٷ� �˻縦 ���� �� �ִ�.
                // (finalTime <= time)
                else {

                    // �ð��� ���� ���� ����� ���� �ð� + �ü� �ð��� �������ν� �ü� ���� �ð�(finalTime)�� �����Ѵ�.
                    finalTime = time + laser[minAndLaserIndex[i][1]];
                }

                i++;
            }

            // �ð��� ������ �ְ�, ���������� ���� ����� ��ӵ� �ð��� ���� ���� ���
            else {

                // ��⿭�� ����� �ְ�, ���� ����� �ü��� ������ �ð�(finalTime)�� ���� �ð�(time)���� �۰ų� ���� ��� => �ü��� ���� �� �ִ� ����
                if(!waitingRoom.isEmpty() && finalTime <= time) {

                    // ��⿭�� �ִ� ����� �ü��� �޴´�.
                    int laserIndex = waitingRoom.poll();

                    // �ð��� ���� ���� ����� ���� �ð�(time == finalTime) + �ü� �ð��� �������ν� �ü� ���� �ð�(finalTime)�� �����Ѵ�.
                    finalTime = time + laser[laserIndex];
                }

                // �ü��� ���� �� �ִ� ����������, ��⿭�� ����� ���� ��� �ð��� �귯����. (time ++)
            }

        }


		return answer;
	}

	public static void main(String[] args){
		Skin T = new Skin();
		System.out.println(T.solution(new int[]{30, 20, 25, 15}, new String[]{"10:23 0", "10:40 3", "10:42 2", "10:52 3", "11:10 2"}));
		System.out.println(T.solution(new int[]{30, 20, 25, 15}, new String[]{"10:23 0", "10:40 3", "10:42 2", "10:52 3", "15:10 0", "15:20 3", "15:22 1", "15:23 0", "15:25 0"}));
		System.out.println(T.solution(new int[]{30, 20, 25, 15}, new String[]{"10:20 1", "10:40 1", "11:00 1", "11:20 1", "11:40 1"}));
	}
}