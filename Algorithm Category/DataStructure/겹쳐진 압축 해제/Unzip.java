import java.util.*;

class Unzip {
	public String solution(String s){
		String answer = "";

		
		Stack<String> bucket = new Stack<>();

		for(char x : s.toCharArray()){
			

			if(String.valueOf(x).equals(")")){
				
				// ")" 일때, "(" 까지 pop 하면서 tmp 에 저장
				String tmp = "";
				while(!bucket.peek().equals("(")){
					tmp = bucket.pop() + tmp;
				}

				// "(" 제거
				bucket.pop();

				if(bucket.peek().equals("(")){
					continue;
				}



				// "(" 보다 앞에 있는 숫자를 pop 하여 num 에 저장
				int num = Integer.parseInt(bucket.pop());
				
				
				// num 만큼 tmp 를 반복하여 tmp 에 저장
				String tmp2 = "";
				for(int i = 0; i < num; i++){
					tmp2 += tmp;
				}
				

				// bucket 에 push
				bucket.push(tmp2);
				continue;
			}

			bucket.push(String.valueOf(x));
		}


		// bucket 에 남아있는 문자열을 answer 에 저장
		while(!bucket.isEmpty()){
			answer = bucket.pop() + answer;
		}

		
		return answer;
	}

	public static void main(String[] args){
		Unzip T = new Unzip();
		// System.out.println(T.solution("3(a2(b))ef"));
		// System.out.println(T.solution("2(ab)k3(bc)"));
		System.out.println(T.solution("2(ab3((cd)))"));
		// System.out.println(T.solution("2(2(ab)3(2(ac)))"));
		// System.out.println(T.solution("3(ab2(sg))"));
	}
}