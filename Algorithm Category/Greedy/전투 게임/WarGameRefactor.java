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

    // ������ �������� �������� ����
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

        // i�� 1���� ����, j�� 0���� ����
        for(int i = 1; i < n; i++) {
            for( ; j < i; j++) {

                // �켱 ���� ��� ����, ���ݷ¸� �۴ٸ� total�� �����ش�. ���Ŀ� ���� ���� ����Ʈ�� �� ����
                if(list.get(j).power < list.get(i).power) {
                    total += list.get(j).power;
                    char team = list.get(j).team;
                    // ĳ���� ������ �ϴ� �߿��� �κ� (�ð� ���⵵�� ���� �� �ִ� �κ�)
                    // Ű : Team, �� : j �� ��ȸ�ϸ鼭 ������ ���� ���� ����Ʈ ���� ����
                    Tp.put(team, Tp.getOrDefault(team, 0) + list.get(j).power);
                }

                // ������ i ���� j�� �������� ���ų� ũ�� �� �̻� ��ȸ�� �ʿ䰡 ����.
                // �� �̻� ��ȸ�� �غ��� �̹� ������������ ���ĵǾ� �ֱ⿡ �� �̻��� ��ȸ�� �ǹ̰� ����.
                else {
                    break;
                }
            }

            // ���� �������� ���ݱ��� ���� �տ��� �ڽ��� ���� ���� ���� ���� ���ش�.
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