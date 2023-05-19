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

        // s 라는 문자열을 HashMap에 넣어서 각 문자의 개수를 센다.
		for(char x : s.toCharArray()){
			sH.put(x, sH.getOrDefault(x, 0) + 1); 
		}

        // odd 변수를 통해서 홀수인 문자의 개수를 센다. (만약 홀수인 문자가 2개 이상이면 팰린드롬을 만들 수 없다.)
		int odd = 0;
		char mid = '#';

        // 문자열 구성이 홀수개로 구성되어진 문자열이 있는지 확인한다.
		for(char key : sH.keySet()){
			if(sH.get(key) % 2 == 1){
                // 있다라면, 해당 Key 값을 mid 변수에 넣어준다. 이는 나중에 팰린드롬의 중간 문자로 사용하기 위함이다.
				mid = key;
				odd++;
			}
		}

        // 홀수인 문자가 2개 이상이면 팰린드롬을 만들 수 없으므로 빈 배열을 리턴한다.
		if(odd > 1) return new String[]{};

        // 만약 홀수인 문자가 1개라면, 해당 문자를 중간에 넣어준다.
        // 넣어주고 사용했다는 의미로 HashMap에서 해당 문자의 개수를 1 감소시킨다.
		if(mid != '#'){
			tmp.add(mid);
			sH.put(mid, sH.get(mid) - 1);
		}

        // 홀수인 문자가 하나도 없는 경우는 바로 DFS를 수행하면 된다.
        // DFS를 통해서 팰린드롬을 만들 수 있는 모든 경우의 수를 구한다.
		DFS();

        // DFS를 통해서 구한 모든 경우의 수를 배열에 넣어서 리턴한다.
		String[] answer = new String[res.size()];
		for(int i = 0; i < res.size(); i++) answer[i] = res.get(i);
		
        return answer;
	}

    public void DFS(){

        // 만약 tmp의 크기가 len과 같다면, 즉 팰린드롬을 만들 수 있는 경우라면 (input으로 들어온 s의 길이와 같다면)
		if(tmp.size() == len){
            // Deque 자료구조 tmp에 들어있는 문자들을 하나씩 빼서 String으로 만들어준다.
			String Ts = "";
			for(char x : tmp) {
                Ts += x;
            }

			res.add(Ts);
		}

		else{
            // 특정 고유 문자열을 순회한다.
			// 일단 key 값으로 가지고 있는 모든 key값을 순회하고자 한다. 이미 사용한 경우는 for문 안의 if문에서 제어하고자 한다.
			for(char key : sH.keySet()){
                // 빈도수가 0이라면, 즉 다 사용한 문자라면 continue 한다.
				if(sH.get(key) == 0) continue;

                // 사용할 문자가 있다면 해당 문자를 tmp의 앞과 뒤에 넣어준다.
				tmp.addFirst(key);
				tmp.addLast(key);
                // 사용했다는 의미로 HashMap에서 해당 문자의 개수를 2 감소시킨다.
				sH.put(key, sH.get(key) - 2);

                // DFS를 수행하여 level이 증가함에따라 팰린드롬을 만들 수 있는 모든 경우의 수를 구한다.
				DFS();

                // DFS를 수행한 후에는 다시 원래대로 돌려놓는다.
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