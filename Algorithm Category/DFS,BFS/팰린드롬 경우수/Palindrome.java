import java.util.*;

class Palindrome {

	Deque<Character> tmp;
	ArrayList<String> res;
	HashMap<Character, Integer> sH;
	int len;

	public String[] solution(String s){
		tmp = new LinkedList<>();
		res = new ArrayList<>();
		sH = new HashMap<>();

		len = s.length();

        // s ��� ���ڿ��� HashMap�� �־ �� ������ ������ ����.
		for(char x : s.toCharArray()){
			sH.put(x, sH.getOrDefault(x, 0) + 1); 
		}

        // odd ������ ���ؼ� Ȧ���� ������ ������ ����. (���� Ȧ���� ���ڰ� 2�� �̻��̸� �Ӹ������ ���� �� ����.)
		int odd = 0;
		char mid = '#';

        // ���ڿ� ������ Ȧ������ �����Ǿ��� ���ڿ��� �ִ��� Ȯ���Ѵ�.
		for(char key : sH.keySet()){
			if(sH.get(key) % 2 == 1){
                // �ִٶ��, �ش� Key ���� mid ������ �־��ش�. �̴� ���߿� �Ӹ������ �߰� ���ڷ� ����ϱ� �����̴�.
				mid = key;
				odd++;
			}
		}

        // Ȧ���� ���ڰ� 2�� �̻��̸� �Ӹ������ ���� �� �����Ƿ� �� �迭�� �����Ѵ�.
		if(odd > 1) return new String[]{};

        // ���� Ȧ���� ���ڰ� 1�����, �ش� ���ڸ� �߰��� �־��ش�.
        // �־��ְ� ����ߴٴ� �ǹ̷� HashMap���� �ش� ������ ������ 1 ���ҽ�Ų��.
		if(mid != '#'){
			tmp.add(mid);
			sH.put(mid, sH.get(mid) - 1);
		}

        // Ȧ���� ���ڰ� �ϳ��� ���� ���� �ٷ� DFS�� �����ϸ� �ȴ�.
        // DFS�� ���ؼ� �Ӹ������ ���� �� �ִ� ��� ����� ���� ���Ѵ�.
		DFS();

        // DFS�� ���ؼ� ���� ��� ����� ���� �迭�� �־ �����Ѵ�.
		String[] answer = new String[res.size()];
		for(int i = 0; i < res.size(); i++) answer[i] = res.get(i);
		
        return answer;
	}

    public void DFS(){

        // ���� tmp�� ũ�Ⱑ len�� ���ٸ�, �� �Ӹ������ ���� �� �ִ� ����� (input���� ���� s�� ���̿� ���ٸ�)
		if(tmp.size() == len){
            // Deque �ڷᱸ�� tmp�� ����ִ� ���ڵ��� �ϳ��� ���� String���� ������ش�.
			String Ts = "";
			for(char x : tmp) {
                Ts += x;
            }

			res.add(Ts);
		}

		else{
            // Ư�� ���� ���ڿ��� ��ȸ�Ѵ�.
			// �ϴ� key ������ ������ �ִ� ��� key���� ��ȸ�ϰ��� �Ѵ�. �̹� ����� ���� for�� ���� if������ �����ϰ��� �Ѵ�.
			for(char key : sH.keySet()){
                // �󵵼��� 0�̶��, �� �� ����� ���ڶ�� continue �Ѵ�.
				if(sH.get(key) == 0) continue;

                // ����� ���ڰ� �ִٸ� �ش� ���ڸ� tmp�� �հ� �ڿ� �־��ش�.
				tmp.addFirst(key);
				tmp.addLast(key);
                // ����ߴٴ� �ǹ̷� HashMap���� �ش� ������ ������ 2 ���ҽ�Ų��.
				sH.put(key, sH.get(key) - 2);

                // DFS�� �����Ͽ� level�� �����Կ����� �Ӹ������ ���� �� �ִ� ��� ����� ���� ���Ѵ�.
				DFS();

                // DFS�� ������ �Ŀ��� �ٽ� ������� �������´�.
				tmp.pollFirst();
				tmp.pollLast();
				sH.put(key, sH.get(key) + 2);
			}
		}

	}

	public static void main(String[] args){
		Palindrome T = new Palindrome();
		System.out.println(Arrays.toString(T.solution("aaaabb")));	
		System.out.println(Arrays.toString(T.solution("abbcc")));
		System.out.println(Arrays.toString(T.solution("abbccee")));
		System.out.println(Arrays.toString(T.solution("abbcceee")));
		System.out.println(Arrays.toString(T.solution("ffeffaae")));
	}
}