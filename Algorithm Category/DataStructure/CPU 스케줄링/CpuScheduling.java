import java.util.*;

public class CpuScheduling {

    public int[] solution(int[][] tasks){
		int[] answer = {};
        answer = new int[tasks.length];
        int answerIdx = 0;

        // 프로그램이 끝나는 시간
        int finishTime = 0;

        // 프로그램이 순서대로 시작하는 시간순으로 먼저 정렬한 후, 시간순서대로 PriorityQueue 대기열에 넣어서 관리한다.
        // 프로그램이 시작하는 순서대로 정렬 (단, answer 배열을 위해 원래 작업 번호도 같이 가지고 있어야 한다.)
        // LinkedList를 사용하는 이유는 programs를 정렬 후, 시간 순서대로 프로그램을 순차적으로 가져오기 위해 (pollFirst() 와 peek() 을 활용하기 위해)
        LinkedList<int[]> programs = new LinkedList<>();
        for(int i = 0; i < tasks.length; i++){
            // 프로그램이 시작하는 시간, 프로그램의 실행 시간, 작업 번호
            programs.add(new int[]{tasks[i][0], tasks[i][1], i});
        }
        
        // programs를 시작 시간이 빠른 순서대로 정렬한다.
        programs.sort((a,b) -> a[0] - b[0]);
        

        
        // PriorityQueue 에는 programs의 값을 넣는다.
        // programs 에 있는 호출 시간이 이른 순서대로 priorityQueue에 넣고
        // poll 할 때는 프로그램 실행 시간이 짧은 순서대로 poll 한다.
        // 만약, 실행 시간이 같은 경우는, 작업 번호가 작은 순서대로 poll 한다.
        // priorityQueue에 넣을 값은 programs에서 나온 값을 그대로 넣고자 한다.
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a,b) -> {
            // 프로그램 실행 시간이 같은 경우,
            if(a[1] == b[1]){
                // 작업 순서가 작은 것부터 실행
                return a[2] - b[2];
            } 
            // 프로그램 실행 시간이 다른 경우,
            else {
                // 실행 시간이 작은 것부터 실행
                return a[1] - b[1];
            }
        });



        while (!priorityQueue.isEmpty() || !programs.isEmpty()) {

            // programs 에는 아직 남은 프로그램이 남아 있지만
            // 대기하고 있는 priorityQueue에는 프로그램이 없는 경우
            // finishTime을 곧 호출할 programs 의 시작 시간으로 갱신한다.
            // 추후 finishTime을 한번 더 초기화 해준다.
            // pq가 있는 경우는 원래 흐름대로 프로그램이 끝나는 시간을 계산해서 ft를 할당해준다.
            // 만약, pq가 없고, programs의 peek 부분이 ft보다 큰 경우는 PQ에 아무것도 들어오지 않아 67번 line에서 null point 발생
            // 따라서, pq가 비어있는 경우에는 ft를 programs의 peek 부분으로 갱신해줘서 pq를 채워준다.
            if (priorityQueue.isEmpty()){
                finishTime = Math.max(finishTime, programs.peek()[0]);
            }
            
            // programs에서 pollFirst()를 해야하기에 , null check를 해준다.
            // 또한, 프로그램 시작 시간이 finishTime 보다 작거나 같은 경우에만 priorityQueue에 넣어준다.
            while(!programs.isEmpty() && programs.peek()[0] <= finishTime){
                // programs 에서 맨앞의 값을 조건에 맞으면 하나씩 꺼내서 priorityQueue에 넣는다.
                priorityQueue.add(programs.pollFirst());
            }
    
            // priorityQueue에 넣은 프로그램들 중에서 우선 순위 규칙에 맞게 꺼내고, finishTime을 갱신한다.
            int[] task = priorityQueue.poll();
    
            // 끝나는 시간을 갱신
            finishTime += task[1];
            // 정답 배열에 작업 번호를 넣는다.
            answer[answerIdx++] = task[2];
        }

			
		return answer;
	}



    public static void main(String[] args){
		CpuScheduling T = new CpuScheduling();
		System.out.println(Arrays.toString(T.solution(new int[][]{{2, 3}, {1, 2}, {8, 2}, {3, 1}, {10, 2}})));
		System.out.println(Arrays.toString(T.solution(new int[][]{{5, 2}, {7, 3}, {1, 3}, {1, 5}, {2, 2}, {1, 1}})));
		System.out.println(Arrays.toString(T.solution(new int[][]{{1, 2}, {2, 3}, {1, 3}, {3, 3}, {8, 2}, {1, 5}, {2, 2}, {1, 1}})));
		System.out.println(Arrays.toString(T.solution(new int[][]{{999, 1000}, {996, 1000}, {998, 1000}, {999, 7}})));
	}
}
