import java.util.*;

class Solution {

    public long solution(int k, int d) {
        long answer = 0;


        for(int x = 0; x*k <= d; x++) {
            // �ִ� d �Ÿ� �� �ȿ� ������ y�� �ִ밪
            long remaining = (long) d*d - (long) k*x*k*x;
            // �ִ� d �Ÿ� �� �ȿ� ������ y�� �ִ� ���� (0�� ���� X)
            int max_y = (int) (Math.sqrt(remaining) / k);

            // 0�� �����ϹǷ� +1
            answer += max_y + 1;
        }


        return answer;
    }
}