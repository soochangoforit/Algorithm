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

        // minAndLaserIndex[i][0] : i번째 손님이 들어온 시간 (분 단위 => 1시간을 60분 단위로 계산)
        // minAndLaserIndex[i][1] : i번째 손님이 원하는 레이저 시술 번호
        int[][] minAndLaserIndex = new int[size][2];
        for(int i = 0; i < size; i ++){
            minAndLaserIndex[i][0] = getMinTime(enter[i].split(" ")[0]);
            minAndLaserIndex[i][1] = Integer.parseInt(enter[i].split(" ")[1]);
        }


        // waiting Room 에는 대기하는 사람이 맞고자 하는 레이저 시술 번호를 넣는다.
        Queue<Integer> waitingRoom = new LinkedList<>();

        // 첫번째 검사(처음으로 방문한 사람의 시술이 끝나는 시간)가 종료되는 시간으로 초기화를 한다.
        int finalTime = minAndLaserIndex[0][0] + laser[minAndLaserIndex[0][1]];

        // 첫번째 사람이 진료실에 들어오는 시간 (첫번째 사람이 도착한 시간이랑 같다.)
        int firstCustomerTime = minAndLaserIndex[0][0];

        // enter 배열에서 1번째 index 부터 시작한다.
        int i = 1;
        
        // 첫번째 고객이 입장한 시간(분)부터 ~ 20시 (1200분) 까지 검사를 진행한다.
        for(int time = firstCustomerTime; time <= 1200 ; time++) {

            // i번째 사람이 size 숫자만큼 벗어나면 break를 통해서 for문을 탈출한다.
            if(i >= size) break;

            // 시간이 되어서, 약속한 시간에 손님이 온 경우
            if (time == minAndLaserIndex[i][0]) {

                // 왔는데 앞전 사람의 시술이 끝나지 않는 경우, 대기실에 들어가야 한다.
                if(finalTime >  minAndLaserIndex[i][0]) {
                    waitingRoom.offer(minAndLaserIndex[i][1]);

                    // 현재 대기하고 있는 인원수 중 가장 많은 인원수를 answer에 갱신한다.
                    answer = Math.max(answer, waitingRoom.size());
                }

                // 왔는데 앞전 사람의 시술이 끝난 경우, 대기열에 들어가지 않고 바로 검사를 받을 수 있다.
                // (finalTime <= time)
                else {

                    // 시간은 현재 들어온 사람의 도착 시간 + 시술 시간을 더함으로써 시술 종료 시간(finalTime)을 갱신한다.
                    finalTime = time + laser[minAndLaserIndex[i][1]];
                }

                i++;
            }

            // 시간이 지나고 있고, 아직까지는 다음 사람의 약속된 시간이 되지 않은 경우
            else {

                // 대기열에 사람이 있고, 앞전 사람의 시술이 끝나는 시간(finalTime)이 현재 시간(time)보다 작거나 같은 경우 => 시술을 받을 수 있는 시점
                if(!waitingRoom.isEmpty() && finalTime <= time) {

                    // 대기열에 있는 사람의 시술을 받는다.
                    int laserIndex = waitingRoom.poll();

                    // 시간은 현재 들어온 사람의 도착 시간(time == finalTime) + 시술 시간을 더함으로써 시술 종료 시간(finalTime)을 갱신한다.
                    finalTime = time + laser[laserIndex];
                }

                // 시술을 받을 수 있는 시점이지만, 대기열에 사람이 없는 경우 시간은 흘러간다. (time ++)
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