import java.util.*;

class Solution {
    // expression�� ��� ���� ���
    ArrayList<Long> nums = new ArrayList<>();
    // expression�� ��� ������ ���
    ArrayList<Character> cals = new ArrayList<>();
    
    // ������ �̷� �� �ִ� ������ ����
    char[] calCandidate = {'+', '*', '-'};
    boolean[] calVisit = new boolean[3];
    char[] realCals = new char[3];
    
    // ����
    long answer = 0;
    
    public long solution(String expression) {

        // 1. expression�� ��� ���ڿ� �����ڸ� list�� ���
        String tempNum = "";
        for(int i = 0; i < expression.length(); i++) {
            // 1-1. ���ڶ�� tempNum�� ����ؼ� ���� �־��ش� (2�ڸ�, 3�ڸ��� ���ڷ� �����ϱ⿡ -> �ٷ� nums�� ���� �ʴ´�.)
            if (expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
                tempNum += expression.charAt(i);
            }
            // 1-2. �����ڸ� ������ ���ݱ��� tempNum�� �����ߴ� ���ڿ��� nums�� �־��ش�.
            // �����ڸ� cals�� �־��ش�.
            else {
                nums.add(Long.parseLong(tempNum));
                // ���� ���ڸ� �ޱ� ���� �ʱ�ȭ
                tempNum = "";
                cals.add(expression.charAt(i));
            }
        }
        // 1-3. for���� ������ �� ������ ���ڰ� tempNum�� ��� �ִ�. nums list�� �־��ش�.
        nums.add(Long.parseLong(tempNum));
        
        // 2. ������ ������ ���� ���� ����� (���������� ������ ����� �� ���� �켱������ �´� �ִ밪 answer �Ҵ�)
        perm(0,3);
    
        return answer;
    }
    
    // 2-1. �����ڵ��� �ĺ��� ���ؼ� ���� �迭�� �����, �켱 ������ �´� ������ ���� ������� �����Ѵ�.
    public void perm(int count, int target) {
        // ������� ������ ���� �迭�� ���� (count) �� ������� �ϴ� ������ ���� (target)�� ���ٸ�
        if(count == target) {
            solve();
            return;
        }
        else {
            // �� 3���� �����ڸ� ���ؼ� ������ ������� �Ѵ�.
            for(int i = 0; i < 3; i++) {
                // �湮���� ���� �����ڿ� ���ؼ� ������� �Ѵ�.
                if (calVisit[i] == false) {
                    calVisit[i] = true;
                    // ������ �ĺ����� ���� �켱���� �����ڷ� ����� �迭�� �� �Ҵ�
                    realCals[count] = calCandidate[i];
                    perm(count + 1, target);
                    calVisit[i] = false;
                }
            }
        }
    }
    
    
    // 3. realCals�� �ִ� �켱���� �����ڿ� ���� �ִ밪�� ����Ͽ� �����Ѵ�.
    public void solve() {
        // 3-1. nums �� cals ����Ʈ�� �����Ͽ� ����Ѵ�. (���� ����Ʈ�� �ٸ� �켱���� ��꿡���� ����ؾ� ������ ��� X)
        ArrayList<Long> cNums = new ArrayList<>(nums);
        ArrayList<Character> cCals = new ArrayList<>(cals);
        
        // 3-2. �켱 ������ �°� ������ ��ȸ
        for(int i = 0; i < realCals.length; i++) {
            // expression�� ���� ������ ��������
            for(int k = 0; k < cCals.size(); k++) {
                // �켱 ������ ��ġ�ϴ� �����ڰ� ���Դٸ�
                if (realCals[i] == cCals.get(k)) {
                    // k index�� ��ġ�ϴ� ���� �����ϸ� �ڿ� �ִ� ���ڰ� �ٽ� k�� ������ ��ܿ´�.
                    // ����, k index�� remove�� 2�� ȣ���Ѵ�.
                    Long result = cal(cNums.remove(k), cNums.remove(k), cCals.get(k));
                    // k index ��ġ�� �����Ͽ� ���� ���� �ٽ� �־��ش�.
                    cNums.add(k, result);
                    // k index�� ��ġ�� �����ڴ� ����߱⿡ cCals ����Ʈ���� �����Ѵ�.
                    cCals.remove(k);
                    // (�߿�) k ���� �ϳ� ����� �Ѵ�. �ֳ��ϸ� cNums�� k index�� ���ο� ���� ���԰�
                    // k index�� ���� k ��ġ �ڿ� �ִ� �����ڰ� ������ ��ܿԱ� �����̴�. 
                    // �ڿ��� ������ ����� �����ڵ� ����ϱ� ���ؼ� �ݵ�� �ʿ��ϴ�.
                    // ���� k-- �� ���� �ʴ´ٸ�, �ڿ��� ������ ��ܿ� �ش� �����ڸ� for������ �ǳʶٰ� �����Ѵ�.
                    k--;
                }
            }
        }
        // �ִ� ������� �����մϴ�. cNums���� ���������� �ϳ��� ���ڸ� �����ִ�.
        answer = Math.max(answer, Math.abs(cNums.get(0)));
        
    }
    
    
    // 4. cal �Լ� ����
    public Long cal(Long num1, Long num2, char op) {
        long num = 0;
        switch(op) {
            case '+':
                num = num1 + num2;
                break;
            case '-':
                num = num1 - num2;
                break;
            case '*':
                num = num1 * num2;
                break;
        }
        return num;
    }
    
}