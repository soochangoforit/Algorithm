import java.util.*;

class Unzip {
	public String solution(String s){
		String answer = "";

		
		Stack<String> bucket = new Stack<>();

		for(char x : s.toCharArray()){
			

			if(String.valueOf(x).equals(")")){
				
				// ")" �϶�, "(" ���� pop �ϸ鼭 tmp �� ����
				String tmp = "";
				while(!bucket.peek().equals("(")){
					tmp = bucket.pop() + tmp;
				}

				// "(" ����
				bucket.pop();

				if(bucket.peek().equals("(")){
					continue;
				}



				// "(" ���� �տ� �ִ� ���ڸ� pop �Ͽ� num �� ����
				int num = Integer.parseInt(bucket.pop());
				
				
				// num ��ŭ tmp �� �ݺ��Ͽ� tmp �� ����
				String tmp2 = "";
				for(int i = 0; i < num; i++){
					tmp2 += tmp;
				}
				

				// bucket �� push
				bucket.push(tmp2);
				continue;
			}

			bucket.push(String.valueOf(x));
		}


		// bucket �� �����ִ� ���ڿ��� answer �� ����
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