import java.util.*;

class Solution {
    // 연산자 우선순위를 정의합니다.
    static char[] calculators = {'+', '-', '*'};
    // 방문 여부를 확인하는 배열입니다.
    static boolean[] visit = new boolean[3];
    // 숫자를 저장하는 리스트입니다.
    static ArrayList<Long> nums = new ArrayList<>();
    // 연산자를 저장하는 리스트입니다.
    static ArrayList<Character> ops = new ArrayList<>();
    // 연산자 우선순위를 저장하는 배열입니다.
    static char[] realCalculators = new char[3];
    // 최대 결과값을 저장하는 변수입니다.
    static long answer;

    public long solution(String expression) {
        answer = 0;
        String num = "";
        // 문자열을 숫자와 연산자로 분리합니다.
        for(int i = 0; i < expression.length(); i++) {
            // 숫자는 여러자리 수가 올 수 있다. 연산자가 오기 전까지의 숫자들을 String num에 저장한다.
            if(expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
                num += expression.charAt(i);
            } else {
                // 연산자가 오면 숫자를 리스트에 저장하고, 연산자를 리스트에 저장한다.
                nums.add(Long.parseLong(num));
                num = "";
                ops.add(expression.charAt(i));
            }
        }
        // 마지막 숫자는 if 문만 탐으로써 String num에만 값이 있음으로 nums 리스트에 저장한다.
        nums.add(Long.parseLong(num));
        
        // 모든 연산자 우선순위의 순열을 생성합니다.
        perm(0, 3);
        return answer;
    }

    // 연산자 우선순위의 순열을 생성하는 함수입니다.
    public void perm(int count, int target) {
        if(count == target) {
            solve();
            return;
        }
        for(int i = 0; i < 3; i++) {
            if(!visit[i]) {
                visit[i] = true;
                realCalculators[count] = calculators[i];
                perm(count + 1, target);
                visit[i] = false;
            }
        }
    }

    // 현재 연산자 우선순위로 표현식을 계산하는 함수입니다.
    public void solve() {
        ArrayList<Long> cNums = new ArrayList<>(nums);
        ArrayList<Character> cOps = new ArrayList<>(ops);
        for(int i = 0; i < realCalculators.length; i++) {
            for(int j = 0; j < cOps.size(); j++) {
                if(cOps.get(j) == realCalculators[i]) {
                    Long res = calc(cNums.remove(j), cNums.remove(j), realCalculators[i]);
                    cNums.add(j, res);
                    cOps.remove(j);
                    // (중요) j 를 감소시켜야 한다. 왜냐하면 j 번째 연산자를 제거하고, j 번째 숫자를 제거했다.
                    // j 에 연산되어 나온 새로운 숫자가 들어갔고, 해당 j 숫자부터 다시 연산을 해야하기 때문이다.
                    j--;
                }
            }
        }
        // 최대 결과값을 갱신합니다. cNums에는 최종적으로 하나의 숫자만 남아있다.
        answer = Math.max(answer, Math.abs(cNums.get(0)));
    }

    // 두 숫자와 연산자를 받아 계산 결과를 반환하는 함수입니다.
    public Long calc(Long num1, Long num2, char op) {
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
