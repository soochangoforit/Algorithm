import java.util.*;

class Unzip {
	public String solution(String s){
		String answer = "";

		
		Stack<String> bucket = new Stack<>();

		String tmp = "";
		for(char x : s.toCharArray()){
			

			if(String.valueOf(x).equals(")")){
				
				// stack �� peek �κ��� ")" �϶�, "(" ���� pop �ϸ鼭 ���ڿ��� tmp �� ����
				while(!bucket.peek().equals("(")) {
					tmp = bucket.pop() + tmp;
				}

				// "(" ����
				bucket.pop();

				// "(" �� �����ߴµ���, "(" �� �������� ���, ���� tmp ���� �״�� ������ä�� ���� �ݺ������� �Ѿ��. �� �ٽ� ")"�� ������ �� ���ڵ��� tmp �� �����ϱ� ����
				if(bucket.peek().equals("(")){
					continue;
				}



				// "(" ���� �տ� �ִ� ���ڸ� pop �Ͽ� num �� ����
				int num = Integer.parseInt(bucket.pop());
				
				
				// num ��ŭ tmp �� �ݺ��Ͽ� tmp �� ���� (stack �� �� ���ڿ� ��ü)
				String tmp2 = "";
				for(int i = 0; i < num; i++){
					tmp2 += tmp;
				}
				

				// ���ڿ� �� ��ü�� bucket �� push
				bucket.push(tmp2);
				// ���ο� ���ڿ��� �����ϱ� ���� tmp �ʱ�ȭ
				tmp = "";
				continue;
			}

			// ")" �� �ƴ� ���, bucket �� push
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
		System.out.println(T.solution("3(a2(b))ef"));
		System.out.println(T.solution("2(ab)k3(bc)"));
		System.out.println(T.solution("2(ab3((cd)))"));
		System.out.println(T.solution("2(2(ab)3(2(ac)))"));
		System.out.println(T.solution("3(ab2(sg))"));
	}
}