import java.util.*;

class Info implements Comparable<Info> {
    public int idx;
    public Character team;
    public int power;

    public Info(int idx, Character team, int power) {
        this.idx = idx;
        this.team = team;
        this.power = power;
    }

    // 전투력 기점으로 오름차순
    @Override
    public int compareTo(Info o) {
        return this.power - o.power;
    }
}




class WarGameReview {
	public int[] solution(String[] students){
		int n = students.length;
		int[] answer = new int[n];

        ArrayList<Info> infoList = new ArrayList<>();

        for(int i = 0 ; i < n ; i++ ) {
            Character team = students[i].split(" ")[0].charAt(0);
            int power = Integer.parseInt(students[i].split(" ")[1]);
            infoList.add(new Info(i, team, power));
        }

        // 전투력 순으로 오름차순 정렬
        // 만약, 내림차순으로 정렬하면 시간 복잡도를 줄일 수 있는 캐싱 개념을 적용하지 못 한다.
        Collections.sort(infoList);

        // i는 0부터 시작, j는 1부터 시작.
        int j = 0, total = 0;

        HashMap<Character, Integer> Tp = new HashMap<>();

        for (int i = 0; i <  n; i++) {
            for ( ; j < n ; j++) {

                if (infoList.get(i).power > infoList.get(j).power) {
                    total += infoList.get(j).power;

                    char team = infoList.get(j).team;
                    int power = infoList.get(j).power;

                    // 앞전에 같은 팀 전투력이 있다면, 함께 더해줘야 한다.
                    Tp.put(team, Tp.getOrDefault(team, 0) + power);
                }

                // 조건에 맞지 않다면, 더 이상 순회할 필요가 없다. 
                else {
                    break;
                }
            }

            // 자기와 같은 팀의 포인트를 차감
            // 중요한 부분은 total은 계속해서 지나왔던 전투 포인트를 계속 더하고 있다.
            int finalTotal = total - Tp.getOrDefault(infoList.get(i).team, 0);

            answer[infoList.get(i).idx] = finalTotal;
        }
        
       
		
		return answer;
	}

	public static void main(String[] args){
		WarGameReview T = new WarGameReview();
		System.out.println(Arrays.toString(T.solution(new String[]{"a 20", "b 12", "a 10", "c 11", "e 12"})));
		System.out.println(Arrays.toString(T.solution(new String[]{"a 17", "b 12", "a 10", "c 11", "b 24", "a 25", "b 12"})));
		System.out.println(Arrays.toString(T.solution(new String[]{"b 20", "c 15", "a 200", "b 11", "b 24", "a 25", "b 12"})));
		System.out.println(Arrays.toString(T.solution(new String[]{"a 30", "a 25", "a 25", "b 20", "b 25", "a 25", "b 30"})));
	}
}