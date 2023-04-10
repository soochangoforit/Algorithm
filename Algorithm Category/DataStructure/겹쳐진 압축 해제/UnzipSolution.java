import java.util.*;

class UnzipSolution {
	public String solution(String s){
		String answer = "";

        Stack<String> st = new Stack<>();

        for(Character x : s.toCharArray()){

            // ")"를 만나면, "(" 까지 pop 하면서 String 을 만들고, "(" 앞에 숫자가 있으면 그 숫자만큼 반복하여 String 을 만든다.
            if(x == ')') {

                String tmp = "";
                // stack이 비어있지 않을때까지
                while(!st.empty()) {

                    // '(' 혹은 문자를 꺼낸다.
                    String character = st.pop();

                    // stack에서 뽑안낸 문자가 "("이면, 바로 앞에 숫자 혹은 "("가 나온다.
                    // if 문 안에서는 숫자를 통해서 문자를 다시 stack에 push 한다.
                    if(character.equals("(")){

                        // 괄호 앞에 숫자가 뭔지 판단한다.
                        String num = "";
                        // stack이 비어있지 않고, stack의 peek이 숫자라면 (숫자를 꺼내서 string 문자열로 변환)
                        while(!st.empty() && Character.isDigit(st.peek().charAt(0))){
                            num = st.pop() + num;
                        }
        
                        // 반복 횟수를 통해 저장된 긴 문자열을 담을 변수 res
                        String res = "";
                        int cnt = 0;
        
                        // num이 숫자가 아니라 또 다른 "("라면, cnt = 1
                        if(num.equals("")) cnt = 1;
                        // 실제 숫자면은 int로 변환
                        else cnt = Integer.parseInt(num);
        
                        // 숫자만큼 tmp를 반복하여 res에 저장
                        for(int i = 0; i < cnt; i++) res += tmp;
                        st.push(res);
                        break;
                    }

                    
                    // stack에서 뽑안낸 문자가 "("가 아니면, tmp에 더해준다. (문자가 담긴다.)
                    tmp = character + tmp;
                }
            }

            // ")"를 만나기 전까지 char 를 String으로 바꿔서 stack 에 push
            else {
                st.push(String.valueOf(x));
            } 
        }

        for(String x : st) answer += x;
		
		return answer;
	}

	public static void main(String[] args){
		UnzipSolution T = new UnzipSolution();
		System.out.println(T.solution("3(a2(b))ef"));
		System.out.println(T.solution("2(ab)k3(bc)"));
		System.out.println(T.solution("2(ab3((cd)))"));
		System.out.println(T.solution("2(2(ab)3(2(ac)))"));
		System.out.println(T.solution("3(ab2(sg))"));
	}
}