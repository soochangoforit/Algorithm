import java.util.*;

class TugOfWarRefactor {
	int[] ch;
	int[][] graph;
	int count = 0;
    Stack<Integer> stack = new Stack<Integer>();
	

	public int solution(int[][] fight) {
		// 0��° index�� ������� �ʱ� ����
        ch = new int[8];
        graph = new int[8][8];

		// ������ �л� index�� ģ���� �ʴ� ģ������ 1�� ǥ��
        for (int i = 0; i < fight.length; i++) {
            graph[fight[i][0]][fight[i][1]] = 1;
            graph[fight[i][1]][fight[i][0]] = 1;
        }

		// 0 Level, 0�� �л� ����
		DFS(0);

		// DFS�� ���� ���� ��� ���� answer�� �Ҵ� ��, count�� 0���� �ʱ�ȭ
        int answer = count;
		count = 0;

        return answer;
    }

    public void DFS(int level) {

		// �ߺ����� ���� ���ڿ� �ڽŰ� ģ���� ���� ģ������ ���ؼ� 7�ڸ��� ��������� count�� ������Ų��.
		// level 0���� �����߱⿡, level 6������ ���� 7�ڸ��� ���������.
        if (level == 7) {
            count++;
            return;
        } 
		
		else {
			// 1�� �л����� 7�� �л����� ��ȸ
            for (int i = 1; i <= 7; i++) {
                if(!stack.isEmpty() && graph[stack.peek()][i] == 1) continue;
				// level 0���� � �л��̴��� ���� �����ϱ⿡ if���� Ÿ�� ���� 1�� �湮 ó��
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