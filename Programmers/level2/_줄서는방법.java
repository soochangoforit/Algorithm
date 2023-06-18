import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(int n, long k) {
        // 순열에 사용할 수 있는 숫자 목록
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }
        
        // 결과 순열을 저장할 배열
        int[] answer = new int[n];

        // 문제에서 인덱싱이 1 기반인데, 우리는 0 기반 인덱싱을 사용하므로 k를 1만큼 감소시킨다.
        k = k - 1;
        
        // 각 위치를 순차적으로 고정
        for (int i = 0; i < n; i++) {
            // n-i-1의 팩토리얼 계산
            long factorial = 1;
            for (int j = 1; j < n-i; j++) {
                factorial *= j;
            }
            // 현재 위치 i에 배치할 숫자의 인덱스 계산
            int index = (int)(k / factorial);
            answer[i] = numbers.get(index);
            // 사용된 숫자는 목록에서 제거
            numbers.remove(index);
            // 다음 반복을 위해 k를 팩토리얼로 나눈 나머지로 업데이트
            k = k % factorial;
        }
        
        return answer;
    }
}