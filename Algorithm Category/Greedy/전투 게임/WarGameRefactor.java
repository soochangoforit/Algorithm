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

    // 전투력 기점으로 오름차순 정렬
    @Override
    public int compareTo(Info o) {
        return this.power - o.power;
    }
}




class WarGameRefactor {
	public int[] solution(String[] students){
		int n = students.length;
		int[] answer = new int[n];

		ArrayList<Info> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Character team = students[i].split(" ")[0].charAt(0);
            int power = Integer.parseInt(students[i].split(" ")[1]);
            list.add(new Info(i, team, power));
        }

        
        Collections.sort(list);

        HashMap<Character, Integer> Tp = new HashMap<>();

        int j = 0, total = 0;

        // i는 1부터 시작, j는 0부터 시작
        for(int i = 1; i < n; i++) {
            for( ; j < i; j++) {

                // 우선 팀에 상관 없이, 공격력만 작다면 total에 더해준다. 추후에 같은 팀원 포인트를 뺄 예정
                if(list.get(j).power < list.get(i).power) {
                    total += list.get(j).power;
                    char team = list.get(j).team;
                    // 캐싱의 역할을 하는 중요한 부분 (시간 복잡도를 줄일 수 있는 부분)
                    // 키 : Team, 값 : j 가 순회하면서 각각의 팀별 전투 포인트 합을 저장
                    Tp.put(team, Tp.getOrDefault(team, 0) + list.get(j).power);
                }

                // 기준점 i 보다 j가 전투력이 같거나 크면 더 이상 순회할 필요가 없다.
                // 더 이상 순회를 해봤자 이미 오름차순으로 정렬되어 있기에 더 이상의 순회는 의미가 없다.
                else {
                    break;
                }
            }

            // 최종 값에서는 지금까지 더한 합에서 자신의 팀과 같은 팀의 합을 빼준다.
            answer[list.get(i).idx] = total - Tp.getOrDefault(list.get(i).team, 0);
        }


		
		return answer;
	}

	public static void main(String[] args){
		WarGameRefactor T = new WarGameRefactor();
		System.out.println(Arrays.toString(T.solution(new String[]{"a 20", "b 12", "a 10", "c 11", "e 12"})));
		System.out.println(Arrays.toString(T.solution(new String[]{"a 17", "b 12", "a 10", "c 11", "b 24", "a 25", "b 12"})));
		System.out.println(Arrays.toString(T.solution(new String[]{"b 20", "c 15", "a 200", "b 11", "b 24", "a 25", "b 12"})));
		System.out.println(Arrays.toString(T.solution(new String[]{"a 30", "a 25", "a 25", "b 20", "b 25", "a 25", "b 30"})));
	}
}