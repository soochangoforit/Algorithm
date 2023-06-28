import java.util.*;

class Solution {
    private static List<int[]> answersList;
    
    public int[][] solution(int n) {
        
    
        answersList = new ArrayList<>();
        
        dfs(n,1,3,2);
        
        int[][] answer = new int[answersList.size()][2];
        for(int i = 0; i < answersList.size(); i++) {
            answer[i] = answersList.get(i);
        }
        
        return answer;
    }
    
    private void dfs (int size, int startPoint, int endPoint, int rest_of_point) {
        if(size == 1) {
            answersList.add(new int[]{startPoint, endPoint});
            return;
        }
        else {
            // 1.  1�� ���� ~ size -1 �������� ��� ��ž�� ���� ������(3)�� �ƴ�, ���� ��(2)���� ���� �̵�
            dfs(size - 1, startPoint, rest_of_point, endPoint);
            
            // 2. size ��ŭ�� ���ڸ� ���� ���� ū ������ ���� ��(1)���� ���� ��(3)���� �̵�
            answersList.add(new int[] {startPoint, endPoint});
            
            // 3. 1�� ���� ~ size - 1 �������� ��� ��ž�� ���� ��(2)���� ���� ��(3)���� ��� �̵�
            dfs(size - 1, rest_of_point, endPoint, startPoint);
        }
    }
}