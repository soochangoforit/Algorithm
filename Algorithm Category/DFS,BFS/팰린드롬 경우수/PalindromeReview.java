import java.util.*;

class PalindromeReview {
	HashMap<Character, Integer> hm;
	Deque<Character> temp;
	ArrayList<String> result;
	int length;

	public String[] solution(String s){

		hm = new HashMap<>();
		length = s.length();

		// 1. s ���ڿ����� Ư�� ���ڿ��� ������ HashMap�� �����Ѵ�.
		for(char key : s.toCharArray()){
			hm.put(key, hm.getOrDefault(key,0) + 1);
		}

		// 2. key ���� ��ȸ�ϸ鼭 odd ������ �ľ��ϰ� �߾� mid ���ڿ��� �Ǵ��Ѵ�.
		int odd = 0;
		char mid = '#';
		for(char key : hm.keySet()){
			if(hm.get(key) % 2 == 1) {
				mid = key;
				odd++;
			}
		}

		// 3. ����, Ư�� ������ ������ 3��ó�� Ȧ������ �̷���� �ִ� ���ڰ� 2�� �̻��̸� �Ӹ���� ���� ���� �� ����.
		if (odd > 1) return new String[]{};



		// 4. �߾ӿ� ��ġ�� ���ڰ� �ִٸ�, deque �ڷᱸ���� �ְ� ������ 1�� �����Ѵ�.
		temp = new LinkedList<>();
		result = new ArrayList<>();

		if(mid != '#') {
			temp.add(mid);
			hm.put(mid, hm.get(mid) - 1);
		}

		// 5. DFS ����
		DFS();

		// 12. result �迭�� ��ȸ�ϸ鼭 answer �迭�� ���� ����ֱ�
		String[] answer = new String[result.size()];
		for(int i = 0; i < result.size(); i++){
			answer[i] = result.get(i);
		}


		return answer;
	}

	public void DFS() {

		// 11. deque �ڷῡ �ִ� ���ڿ��� ������ input���� ���� s ���ڿ��� ���̰� ������ result ����Ʈ�� �ֱ�
		if(temp.size() == length) {
			String Tx = "";
			for(char c : temp){
				Tx += c;
			}

			result.add(Tx);
		}

		else {
			// 6. ������ level ���� key ���� ���ʴ�� ������.
			for(char key : hm.keySet()){

				// 7. ���´µ� ������ 0�̶��, �� �� �����̰� continue �Ѵ�.
				if (hm.get(key) == 0) continue;

				// 8. temp �յڷ� ���ڿ� �ֱ�, 2���� �־����� 2�� ���� ����
				temp.addFirst(key);
				temp.addLast(key);
				hm.put(key, hm.get(key) - 2);

				// 9. DFS ����
				DFS();

				// 10. temp �յڷ� ���ڿ� ����, 2���� ������ 2�� ���� ����
				temp.pollFirst();
				temp.pollLast();
				hm.put(key, hm.get(key) + 2);
			}
		}
	}





	public static void main(String[] args){
		PalindromeReview T = new PalindromeReview();
		System.out.println(Arrays.toString(T.solution("aaaabb")));	
		System.out.println(Arrays.toString(T.solution("abbcc")));
		System.out.println(Arrays.toString(T.solution("abbccee")));
		System.out.println(Arrays.toString(T.solution("abbcceee")));
		System.out.println(Arrays.toString(T.solution("ffeffaae")));
	}
}