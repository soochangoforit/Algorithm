import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(int n, long k) {
        // ������ ����� �� �ִ� ���� ���
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }
        
        // ��� ������ ������ �迭
        int[] answer = new int[n];

        // �������� �ε����� 1 ����ε�, �츮�� 0 ��� �ε����� ����ϹǷ� k�� 1��ŭ ���ҽ�Ų��.
        k = k - 1;
        
        // �� ��ġ�� ���������� ����
        for (int i = 0; i < n; i++) {
            // n-i-1�� ���丮�� ���
            long factorial = 1;
            for (int j = 1; j < n-i; j++) {
                factorial *= j;
            }
            // ���� ��ġ i�� ��ġ�� ������ �ε��� ���
            int index = (int)(k / factorial);
            answer[i] = numbers.get(index);
            // ���� ���ڴ� ��Ͽ��� ����
            numbers.remove(index);
            // ���� �ݺ��� ���� k�� ���丮��� ���� �������� ������Ʈ
            k = k % factorial;
        }
        
        return answer;
    }
}