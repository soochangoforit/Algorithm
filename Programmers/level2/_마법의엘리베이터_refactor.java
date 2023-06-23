
class Solution {
    public int solution(int storey) {
        int answer = 0;

        while (storey != 0) {
            // 첫 시작은 1의 자리부터 시작, upperNumer은 10의 자리 숫자 의미.
            int upperNumber = (storey %100)/10;
            // 첫 시작 기준 number은 1의 자리 숫자.
            int number = storey % 10;

            // 현재 자리가 5보다 큰 경우 or 현재 자리가 5이고, 앞자리가 5보다 큰 경우
            if (number > 5 || number == 5 && upperNumber>=5) {
                // 다음 자리 수 올림
                storey += 10;
                // 10으로 만들기 위한 연산 횟수를 더해준다.
                answer += (10 - number);
            } 
            // 현재 자리가 5보다 작은 경우 or 현재 자리가 5이고, 앞자리가 5보다 작은 경우
            else {
                // 현재 위치하고 있는 숫자만큼 내려준다. (올림하지 않는다.)
                answer += number;
            }

            // 나누기 10을 함으로써 number 위치하던 숫자 제거
            storey = storey / 10;
        }  
        return answer;
    }
}