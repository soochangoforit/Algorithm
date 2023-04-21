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
        
        // poll �� �� ������ ����Ѵ�.
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a,b) -> {
            // ���α׷� ���� �ð��� ���� ���,
            if(a[1] == b[1]){
                // �۾� ������ ���� �ͺ��� ����
                return a[2] - b[2];
            } 
            // ���α׷� ���� �ð��� �ٸ� ���,
            else {
                // ���� �ð��� ���� �ͺ��� ����
                return a[1] - b[1];
            }
        });



        while (!priorityQueue.isEmpty() || !programs.isEmpty()) {

            if (priorityQueue.isEmpty()){
                finishTime = Math.max(finishTime, programs.peek()[0]);
            }

            while(!programs.isEmpty() && programs.peek()[0] <= finishTime){
                // programs ���� �Ǿ��� ���� ���ǿ� ������ �ϳ��� ������ priorityQueue�� �ִ´�.
                priorityQueue.add(programs.pollFirst());
            }
    
            // priorityQueue�� ���� ���α׷��� �߿��� �켱 ���� ��Ģ�� �°� ������, finishTime�� �����Ѵ�.
            int[] task = priorityQueue.poll();
    
            // ������ �ð��� ����
            finishTime += task[1];
            // ���� �迭�� �۾� ��ȣ�� �ִ´�.
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
