import java.util.*;

class TugOfWarRefactor {
	int[] ch;
	int[][] graph;
	int count = 0;
    Stack<Integer> stack = new Stack<Integer>();
	

	public int solution(int[][] fight) {
		// 0번째 index는 사용하지 않기 위해
        ch = new int[8];
        graph = new int[8][8];

		// 각각의 학생 index에 친하지 않는 친구들을 1로 표시
        for (int i = 0; i < fight.length; i++) {
            graph[fight[i][0]][fight[i][1]] = 1;
            graph[fight[i][1]][fight[i][0]] = 1;
        }

		// 0 Level, 0번 학생 시작
		DFS(0);

		// DFS를 통해 나온 결과 값을 answer에 할당 후, count를 0으로 초기화
        int answer = count;
		count = 0;

        return answer;
    }

    public void DFS(int level) {

		// 중복되지 않은 숫자와 자신과 친하지 않은 친구들을 피해서 7자리가 만들어지면 count를 증가시킨다.
		// level 0부터 시작했기에, level 6까지만 돌면 7자리가 만들어진다.
        if (level == 7) {
            count++;
            return;
        } 
		
		else {
			// 1번 학생부터 7번 학생까지 순회
            for (int i = 1; i <= 7; i++) {
                if(!stack.isEmpty() && graph[stack.peek()][i] == 1) continue;
				// level 0에선 어떤 학생이던지 접근 가능하기에 if문을 타고 들어가서 1로 방문 처리
                if (ch[i] == 0) {
                    ch[i] = 1;
                    stack.push(i);
                    DFS(level + 1);
                    ch[i] = 0;
                    stack.pop();
                }
            }
        }
	}
		
	public static void main(String[] args){
		TugOfWarRefactor T = new TugOfWarRefactor();
		System.out.println(T.solution(new int[][]{{1, 3}, {5, 7}, {4, 2}}));
		System.out.println(T.solution(new int[][]{{3, 2}, {3, 5}, {5, 2}, {7, 3}}));
		System.out.println(T.solution(new int[][]{{1, 2}, {1, 5}, {1, 7}, {1, 3}}));
		System.out.println(T.solution(new int[][]{{1, 7}}));
		System.out.println(T.solution(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}}));
	}
}