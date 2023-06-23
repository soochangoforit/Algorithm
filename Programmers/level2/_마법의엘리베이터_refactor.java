
class Solution {
    public int solution(int storey) {
        int answer = 0;

        while (storey != 0) {
            // ù ������ 1�� �ڸ����� ����, upperNumer�� 10�� �ڸ� ���� �ǹ�.
            int upperNumber = (storey %100)/10;
            // ù ���� ���� number�� 1�� �ڸ� ����.
            int number = storey % 10;

            // ���� �ڸ��� 5���� ū ��� or ���� �ڸ��� 5�̰�, ���ڸ��� 5���� ū ���
            if (number > 5 || number == 5 && upperNumber>=5) {
                // ���� �ڸ� �� �ø�
                storey += 10;
                // 10���� ����� ���� ���� Ƚ���� �����ش�.
                answer += (10 - number);
            } 
            // ���� �ڸ��� 5���� ���� ��� or ���� �ڸ��� 5�̰�, ���ڸ��� 5���� ���� ���
            else {
                // ���� ��ġ�ϰ� �ִ� ���ڸ�ŭ �����ش�. (�ø����� �ʴ´�.)
                answer += number;
            }

            // ������ 10�� �����ν� number ��ġ�ϴ� ���� ����
            storey = storey / 10;
        }  
        return answer;
    }
}