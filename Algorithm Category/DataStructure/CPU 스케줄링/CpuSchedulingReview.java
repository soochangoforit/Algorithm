import java.util.*;

public class CpuSchedulingReview {

    public int[] solution(int[][] tasks){
		int[] answer = {};
        answer = new int[tasks.length];
        int answerIdx = 0;

        int finishTime = 0;

        
        LinkedList<int[]> programs = new LinkedList<>();
        for(int i = 0; i < tasks.length; i++){
            programs.add(new int[]{tasks[i][0], tasks[i][1], i});
        }
        
        
        programs.sort((a,b) -> a[0] - b[0]);
        
        // poll 할 때 조건을 명시한다.
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

            if (priorityQueue.isEmpty()){
                finishTime = Math.max(finishTime, programs.peek()[0]);
            }

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
		CpuSchedulingReview T = new CpuSchedulingReview();
		System.out.println(Arrays.toString(T.solution(new int[][]{{2, 3}, {1, 2}, {8, 2}, {3, 1}, {10, 2}})));
		System.out.println(Arrays.toString(T.solution(new int[][]{{5, 2}, {7, 3}, {1, 3}, {1, 5}, {2, 2}, {1, 1}})));
		System.out.println(Arrays.toString(T.solution(new int[][]{{1, 2}, {2, 3}, {1, 3}, {3, 3}, {8, 2}, {1, 5}, {2, 2}, {1, 1}})));
		System.out.println(Arrays.toString(T.solution(new int[][]{{999, 1000}, {996, 1000}, {998, 1000}, {999, 7}})));
	}
}
