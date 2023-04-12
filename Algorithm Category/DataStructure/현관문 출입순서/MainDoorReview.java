import java.util.*;

class MainDoorReview {
	public int[] solution(int[] arrival, int[] state){
		

        
        // 앞전에 현관문을 이용한 변수
        int prev = 1;
        // 현관문 이용하려는 사람 수
        int n = arrival.length;
        int[] answer = new int[n];

        // 나가는 대기열
        Queue<Integer> exit = new LinkedList<>();
        // 들어오는 대기열
        Queue<Integer> enter = new LinkedList<>();

        // cntOfEscape 는 for문을 종료하기 위해 필요한 변수 (t는 시간, human은 사람 번호, cntOfEscape 는 사람이 탈출한 횟수를 의미한다. n 사이즈(사람명수)만큼 탈출했으면 for문 종료한다.)
        // t 시간이 지나면서, arrival 배열을 순회할 index 가 필요하다 => human (t 초에 동시에 현관문에 도착한 사람들을 찾기 위한 index)
        // t와 human 은 계속 해서 증가시킬 예정이기에, cntOfEscape 변수를 통해 for문을 탈출하고자 한다.
        for(int t = 0, human = 0 , cntOfEscape = 0 ;  ; t++ ){

            
            // 현재 t 시간이 앞으로 도착할 arrival[human] 도착 시간보다 아직 이른경우
            // 시간을 다음 사람이 현관문에 도착한 시간으로 순간 이동한다.
            // 그리고 t 시간을 이동하고 나서는 그 바로 1초 전엔 현관문을 이용한 사람이 없기에 prev 1로 수정
            if(enter.isEmpty() && exit.isEmpty() && t < arrival[human]){
                // t가 다음 현관문을 이용할 사람한테 후욱 뛰어넘을 수 있는 전제 조건은, "대기열에 아무도 없어야지 가능하다"
                // 만약 대기열에 누군가가 있는 상태로 후욱 건너뛰면 미리 대기열에 있는 사람이 현관문을 이용한 시간 t를 answer에 저장할 때 잘못된 결과 값이 들어가게 된다.
                // 따라서, enter.isEmpty() , exit.isEmpty()가 반드시 필요하다.
                t = arrival[human];
                prev = 1;
            }

            
            // t 시간에 따라 enter 혹은 exit에 값을 넣어야 한다.
            // human 값을 계속 증가시키면, indexOutOfBound 에러가 발생할 수 있기에 human < n 조건을 넣어준다.
            while(human < n && arrival[human] <= t) {
                // 다음 초(t)로 넘어가기 전에 사람 index를 증가시켜 t 시간에 도착한 다른 사람을 찾는다.
                if(state[human] == 1){
                    exit.offer(human);
                    human++;
                } 

                else{
                    enter.offer(human);
                    human++;
                }
            }

            // 전에 나가는 사람이 이용함 (1초당 한명씩만 이용가능)
            if(prev == 1){

                // 전에 먼저 나가는 사람이 있었기에, 다음 차례도 나가는 사람이 먼저 우선권 
                // 나가고자 하는 사람이 있으면
                if(!exit.isEmpty()){
                    int goingToExitHuman = exit.poll();
                    // t 초에 나갔다는 의미로 값을 넣어준다.
                    answer[goingToExitHuman] = t;

                    // 전에 이용한 사람이 나간 사람이라 prev 변수를 바꿔준다.
                    prev = 1;
                }
                // 전에 나가는 사람이 이용했지만, 이젠 나가려고 하는 사람이 없기에 들어오는 사람 우선권 (1초당 한명씩만 이용가능)
                else{
                    int goingToEnterHuman = enter.poll();
                    // t 초에 들어왔다는 의미로 값을 넣어준다.
                    answer[goingToEnterHuman] = t;

                    // 전에 이용한 사람이 들어온 사람이라 prev 변수 바꿔준다.
                    prev = 0;
                }
            }
            // 전에 들어오는 사람이 이용함 (1초당 한명씩만 이용가능)
            else if(prev == 0){

                // 전에 들어오는 사람이 있었기에, 다음 차례도 들어오는 사람이 먼저 우선권 (1초당 한명씩만 이용가능)
                if(!enter.isEmpty()) {
                    int goingToEnterHuman = enter.poll();
                    answer[goingToEnterHuman] = t;
                    // 전에 이용한 사람이 들어온 사람이라 prev 변수를 바꿔준다.
                    prev = 0;
                }
                // 전에 들어오는 사람이 이용했지만, 이젠 들어오려는 사람이 없기에 나가는 사람 우선권 (1초당 한명씩만 이용가능)
                else {
                    int goingToExitHuman = exit.poll();
                    answer[goingToExitHuman] = t;
                    prev = 1;
                }
            }

            // for문 탈출을 위한 변수 (cntOfExcape => 탈출한 횟수를 저장하고 있다. 1초에 한사람씩 탈출한다.)
            cntOfEscape++;
            if(cntOfEscape == n) break;
            
        }

		
		return answer;
	}
		
	public static void main(String[] args){
		MainDoorReview T = new MainDoorReview();
		System.out.println(Arrays.toString(T.solution(new int[]{0, 1, 1, 1, 2, 3, 8, 8}, new int[]{1, 0, 0, 1, 0, 0, 1, 0})));
		System.out.println(Arrays.toString(T.solution(new int[]{3, 3, 4, 5, 5, 5}, new int[]{1, 0, 1, 0, 1, 0})));
		System.out.println(Arrays.toString(T.solution(new int[]{2, 2, 2, 3, 4, 8, 8, 9, 10, 10}, new int[]{1, 0, 0, 0, 1, 1, 0, 1, 1, 0})));
	}
}