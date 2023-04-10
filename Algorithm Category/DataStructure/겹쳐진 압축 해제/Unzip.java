import java.util.*;

class Unzip {
	public String solution(String s){
		String answer = "";

		
		Stack<String> bucket = new Stack<>();

		String tmp = "";
		for(char x : s.toCharArray()){
			

			if(String.valueOf(x).equals(")")){
				
				// stack 의 peek 부분이 ")" 일때, "(" 까지 pop 하면서 문자열을 tmp 에 저장
				while(!bucket.peek().equals("(")) {
					tmp = bucket.pop() + tmp;
				}

				// "(" 제거
				bucket.pop();

				// "(" 를 제거했는데도, "(" 가 남아있을 경우, 현재 tmp 값을 그대로 유지한채로 다음 반복문으로 넘어간다. 또 다시 ")"가 나오기 전 문자들을 tmp 에 저장하기 위함
				if(bucket.peek().equals("(")){
					continue;
				}



				// "(" 보다 앞에 있는 숫자를 pop 하여 num 에 저장
				int num = Integer.parseInt(bucket.pop());
				
				
				// num 만큼 tmp 를 반복하여 tmp 에 저장 (stack 에 들어갈 문자열 전체)
				String tmp2 = "";
				for(int i = 0; i < num; i++){
					tmp2 += tmp;
				}
				

				// 문자열 그 자체를 bucket 에 push
				bucket.push(tmp2);
				// 새로운 문자열을 저장하기 위해 tmp 초기화
				tmp = "";
				continue;
			}

			// ")" 가 아닌 경우, bucket 에 push
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
		System.out.println(T.solution("3(a2(b))ef"));
		System.out.println(T.solution("2(ab)k3(bc)"));
		System.out.println(T.solution("2(ab3((cd)))"));
		System.out.println(T.solution("2(2(ab)3(2(ac)))"));
		System.out.println(T.solution("3(ab2(sg))"));
	}
}