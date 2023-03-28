import java.util.*;

class MySolution {
	public char[] solution(int n, int[][] ladder){
		char[] answer = new char[n];
	
		// 여기서부터 코드 작성
        for (int i = 0; i < n ; i++) {
            // temp는 움직이고자 하는 사람의 index 주소 값
            int temp = i;

            for (int j = 0; j < ladder.length; j++) {
                
                for (int k = 0; k < ladder[j].length; k++) {

                    // (temp 위치값 +1) 이 ladder[j][k]와 같으면 해당 위치에 우측으로 사다리가 존재해서 우측으로 빠지는 경우
                    if (ladder[j][k] == temp + 1) {
                        temp += 1;
                    } 
                    // teamp 위치값이 ladder[j][k]와 같으면 해당 위치에 좌측으로 사다리가 존재해서 좌측으로 빠지는 경우
                    else if (ladder[j][k] == temp) {
                        temp -= 1;
                    }
                }

                // 아무것도 아닌 경우에 대해서는 temp 위치 값을 그대로 유지한 상태에서 다음 사다리로 넘어간다.

            }

            // temp 위치값에 맞는 알파벳을 answer 배열에 넣어준다. 아스키코드를 이용한다.
            answer[temp] = (char) (i + 65);
        }

		return answer;
	}

	public static void main(String[] args){
		MySolution T = new MySolution();
		System.out.println(Arrays.toString(T.solution(5, new int[][]{{1, 3}, {2, 4}, {1, 4}})));
		System.out.println(Arrays.toString(T.solution(7, new int[][]{{1, 3, 5}, {1, 3, 6}, {2, 4}})));
		System.out.println(Arrays.toString(T.solution(8, new int[][]{{1, 5}, {2, 4, 7}, {1, 5, 7}, {2, 5, 7}})));
		System.out.println(Arrays.toString(T.solution(12, new int[][]{{1, 5, 8, 10}, {2, 4, 7}, {1, 5, 7, 9, 11}, {2, 5, 7, 10}, {3, 6, 8, 11}})));
	}
}