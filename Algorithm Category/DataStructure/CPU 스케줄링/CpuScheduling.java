import java.util.*;

public class CpuScheduling {

    public int[] solution(int[][] tasks){
		int[] answer = {};
        answer = new int[tasks.length];
        int answerIdx = 0;

        // ���α׷��� ������ �ð�
        int finishTime = 0;

        // ���α׷��� ������� �����ϴ� �ð������� ���� ������ ��, �ð�������� PriorityQueue ��⿭�� �־ �����Ѵ�.
        // ���α׷��� �����ϴ� ������� ���� (��, answer �迭�� ���� ���� �۾� ��ȣ�� ���� ������ �־�� �Ѵ�.)
        // LinkedList�� ����ϴ� ������ programs�� ���� ��, �ð� ������� ���α׷��� ���������� �������� ���� (pollFirst() �� peek() �� Ȱ���ϱ� ����)
        LinkedList<int[]> programs = new LinkedList<>();
        for(int i = 0; i < tasks.length; i++){
            // ���α׷��� �����ϴ� �ð�, ���α׷��� ���� �ð�, �۾� ��ȣ
            programs.add(new int[]{tasks[i][0], tasks[i][1], i});
        }
        
        // programs�� ���� �ð��� ���� ������� �����Ѵ�.
        programs.sort((a,b) -> a[0] - b[0]);
        

        
        // PriorityQueue ���� programs�� ���� �ִ´�.
        // programs �� �ִ� ȣ�� �ð��� �̸� ������� priorityQueue�� �ְ�
        // poll �� ���� ���α׷� ���� �ð��� ª�� ������� poll �Ѵ�.
        // ����, ���� �ð��� ���� ����, �۾� ��ȣ�� ���� ������� poll �Ѵ�.
        // priorityQueue�� ���� ���� programs���� ���� ���� �״�� �ְ��� �Ѵ�.
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

            // programs ���� ���� ���� ���α׷��� ���� ������
            // ����ϰ� �ִ� priorityQueue���� ���α׷��� ���� ���
            // finishTime�� �� ȣ���� programs �� ���� �ð����� �����Ѵ�.
            // ���� finishTime�� �ѹ� �� �ʱ�ȭ ���ش�.
            // pq�� �ִ� ���� ���� �帧��� ���α׷��� ������ �ð��� ����ؼ� ft�� �Ҵ����ش�.
            // ����, pq�� ����, programs�� peek �κ��� ft���� ū ���� PQ�� �ƹ��͵� ������ �ʾ� 67�� line���� null point �߻�
            // ����, pq�� ����ִ� ��쿡�� ft�� programs�� peek �κ����� �������༭ pq�� ä���ش�.
            if (priorityQueue.isEmpty()){
                finishTime = Math.max(finishTime, programs.peek()[0]);
            }
            
            // programs���� pollFirst()�� �ؾ��ϱ⿡ , null check�� ���ش�.
            // ����, ���α׷� ���� �ð��� finishTime ���� �۰ų� ���� ��쿡�� priorityQueue�� �־��ش�.
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
		CpuScheduling T = new CpuScheduling();
		System.out.println(Arrays.toString(T.solution(new int[][]{{2, 3}, {1, 2}, {8, 2}, {3, 1}, {10, 2}})));
		System.out.println(Arrays.toString(T.solution(new int[][]{{5, 2}, {7, 3}, {1, 3}, {1, 5}, {2, 2}, {1, 1}})));
		System.out.println(Arrays.toString(T.solution(new int[][]{{1, 2}, {2, 3}, {1, 3}, {3, 3}, {8, 2}, {1, 5}, {2, 2}, {1, 1}})));
		System.out.println(Arrays.toString(T.solution(new int[][]{{999, 1000}, {996, 1000}, {998, 1000}, {999, 7}})));
	}
}
