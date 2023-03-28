import java.util.*;

class Solution {
	public char[] solution(int n, int[][] ladder){
		char[] answer = new char[n];
        
        // 아스키 코드로 A~Z까지 개수만큼 배열에 넣어준다.
        for (int i = 0; i < n ; i++) {
            answer[i] = (char) (i + 65);
        }

        // 사다리를 탄다는 의미는 사다리를 타는 사람의 위치를 바꿔주는 것이다.
        for(int[] line : ladder) {
            for(int x : line) {
                char tmp = answer[x];
                answer[x] = answer[x-1];
                answer[x-1] = tmp;
            }
        }

		return answer;
	}

	public static void main(String[] args){
		Solution T = new Solution();
		System.out.println(Arrays.toString(T.solution(5, new int[][]{{1, 3}, {2, 4}, {1, 4}})));
		System.out.println(Arrays.toString(T.solution(7, new int[][]{{1, 3, 5}, {1, 3, 6}, {2, 4}})));
		System.out.println(Arrays.toString(T.solution(8, new int[][]{{1, 5}, {2, 4, 7}, {1, 5, 7}, {2, 5, 7}})));
		System.out.println(Arrays.toString(T.solution(12, new int[][]{{1, 5, 8, 10}, {2, 4, 7}, {1, 5, 7, 9, 11}, {2, 5, 7, 10}, {3, 6, 8, 11}})));
	}
}