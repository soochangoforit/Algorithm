import java.util.*;

class WarGame {
	public int[] solution(String[] students){
		int n = students.length;
		int[] answer = new int[n];

		String[][] studentWarPoint = new String[n][3];

		for (int i = 0; i < n; i++) {
			studentWarPoint[i][0] = String.valueOf(i);
			studentWarPoint[i][1] = students[i].split(" ")[0];
			studentWarPoint[i][2] = students[i].split(" ")[1];
		}

		// 1. 전투력이 높은 순으로 정렬 (내림 차순 정렬, 같다면 알파벳 순으로 오름차순 정렬)
		Arrays.sort(studentWarPoint, (a, b) -> Integer.parseInt(b[2]) - Integer.parseInt(a[2]));

		int i = 0;
		while(i < n) {
			String team = studentWarPoint[i][1];
			int teamWarPoint = Integer.parseInt(studentWarPoint[i][2]);
			int totalPoint = 0;

			int j = i + 1;
			while(j < n) {
				if (!team.equals(studentWarPoint[j][1]) && teamWarPoint > Integer.parseInt(studentWarPoint[j][2])) {
					totalPoint += Integer.parseInt(studentWarPoint[j][2]);
					j++;
				}
				else {
					j++;
				}
				
			}

			answer[Integer.parseInt(studentWarPoint[i][0])] = totalPoint;
			i++;
		}




		
		return answer;
	}

	public static void main(String[] args){
		WarGame T = new WarGame();
		System.out.println(Arrays.toString(T.solution(new String[]{"a 20", "b 12", "a 10", "c 11", "e 12"})));
		System.out.println(Arrays.toString(T.solution(new String[]{"a 17", "b 12", "a 10", "c 11", "b 24", "a 25", "b 12"})));
		System.out.println(Arrays.toString(T.solution(new String[]{"b 20", "c 15", "a 200", "b 11", "b 24", "a 25", "b 12"})));
		System.out.println(Arrays.toString(T.solution(new String[]{"a 30", "a 25", "a 25", "b 20", "b 25", "a 25", "b 30"})));
	}
}