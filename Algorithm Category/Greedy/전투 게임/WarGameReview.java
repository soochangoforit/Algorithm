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

    // ������ �������� ��������
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

        // ������ ������ �������� ����
        // ����, ������������ �����ϸ� �ð� ���⵵�� ���� �� �ִ� ĳ�� ������ �������� �� �Ѵ�.
        Collections.sort(infoList);

        // i�� 0���� ����, j�� 1���� ����.
        int j = 0, total = 0;

        HashMap<Character, Integer> Tp = new HashMap<>();

        for (int i = 0; i <  n; i++) {
            for ( ; j < n ; j++) {

                if (infoList.get(i).power > infoList.get(j).power) {
                    total += infoList.get(j).power;

                    char team = infoList.get(j).team;
                    int power = infoList.get(j).power;

                    // ������ ���� �� �������� �ִٸ�, �Բ� ������� �Ѵ�.
                    Tp.put(team, Tp.getOrDefault(team, 0) + power);
                }

                // ���ǿ� ���� �ʴٸ�, �� �̻� ��ȸ�� �ʿ䰡 ����. 
                else {
                    break;
                }
            }

            // �ڱ�� ���� ���� ����Ʈ�� ����
            // �߿��� �κ��� total�� ����ؼ� �����Դ� ���� ����Ʈ�� ��� ���ϰ� �ִ�.
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