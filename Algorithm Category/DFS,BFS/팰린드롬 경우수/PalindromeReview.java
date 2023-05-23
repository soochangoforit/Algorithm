import java.util.*;

class PalindromeReview {
	HashMap<Character, Integer> hm;
	Deque<Character> temp;
	ArrayList<String> result;
	int length;

	public String[] solution(String s){

		hm = new HashMap<>();
		length = s.length();

		// 1. s 문자열에서 특정 문자열의 개수를 HashMap에 저장한다.
		for(char key : s.toCharArray()){
			hm.put(key, hm.getOrDefault(key,0) + 1);
		}

		// 2. key 값을 순회하면서 odd 개수를 파악하고 중앙 mid 문자열을 판단한다.
		int odd = 0;
		char mid = '#';
		for(char key : hm.keySet()){
			if(hm.get(key) % 2 == 1) {
				mid = key;
				odd++;
			}
		}

		// 3. 만약, 특정 문자의 개수가 3개처럼 홀수개로 이루어져 있는 문자가 2개 이상이면 팰린드롬 수를 만들 수 없다.
		if (odd > 1) return new String[]{};



		// 4. 중앙에 위치할 문자가 있다면, deque 자료구조에 넣고 개수를 1개 차감한다.
		temp = new LinkedList<>();
		result = new ArrayList<>();

		if(mid != '#') {
			temp.add(mid);
			hm.put(mid, hm.get(mid) - 1);
		}

		// 5. DFS 수행
		DFS();

		// 12. result 배열을 순회하면서 answer 배열에 값을 담아주기
		String[] answer = new String[result.size()];
		for(int i = 0; i < result.size(); i++){
			answer[i] = result.get(i);
		}


		return answer;
	}

	public void DFS() {

		// 11. deque 자료에 있는 문자열의 개수가 input으로 들어온 s 문자열의 길이가 같으면 result 리스트에 넣기
		if(temp.size() == length) {
			String Tx = "";
			for(char c : temp){
				Tx += c;
			}

			result.add(Tx);
		}

		else {
			// 6. 각각의 level 마다 key 값을 차례대로 꺼낸다.
			for(char key : hm.keySet()){

				// 7. 꺼냈는데 개수가 0이라면, 다 쓴 경우기이게 continue 한다.
				if (hm.get(key) == 0) continue;

				// 8. temp 앞뒤로 문자열 넣기, 2개를 넣었으니 2개 개수 차감
				temp.addFirst(key);
				temp.addLast(key);
				hm.put(key, hm.get(key) - 2);

				// 9. DFS 수행
				DFS();

				// 10. temp 앞뒤로 문자열 빼기, 2개를 뺐으니 2개 개수 증감
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