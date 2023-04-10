import java.util.*;

class UnzipSolution {
	public String solution(String s){
		String answer = "";

        Stack<String> st = new Stack<>();

        for(Character x : s.toCharArray()){

            // ")"�� ������, "(" ���� pop �ϸ鼭 String �� �����, "(" �տ� ���ڰ� ������ �� ���ڸ�ŭ �ݺ��Ͽ� String �� �����.
            if(x == ')') {

                String tmp = "";
                // stack�� ������� ����������
                while(!st.empty()) {

                    // '(' Ȥ�� ���ڸ� ������.
                    String character = st.pop();

                    // stack���� �̾ȳ� ���ڰ� "("�̸�, �ٷ� �տ� ���� Ȥ�� "("�� ���´�.
                    // if �� �ȿ����� ���ڸ� ���ؼ� ���ڸ� �ٽ� stack�� push �Ѵ�.
                    if(character.equals("(")){

                        // ��ȣ �տ� ���ڰ� ���� �Ǵ��Ѵ�.
                        String num = "";
                        // stack�� ������� �ʰ�, stack�� peek�� ���ڶ�� (���ڸ� ������ string ���ڿ��� ��ȯ)
                        while(!st.empty() && Character.isDigit(st.peek().charAt(0))){
                            num = st.pop() + num;
                        }
        
                        // �ݺ� Ƚ���� ���� ����� �� ���ڿ��� ���� ���� res
                        String res = "";
                        int cnt = 0;
        
                        // num�� ���ڰ� �ƴ϶� �� �ٸ� "("���, cnt = 1
                        if(num.equals("")) cnt = 1;
                        // ���� ���ڸ��� int�� ��ȯ
                        else cnt = Integer.parseInt(num);
        
                        // ���ڸ�ŭ tmp�� �ݺ��Ͽ� res�� ����
                        for(int i = 0; i < cnt; i++) res += tmp;
                        st.push(res);
                        break;
                    }

                    
                    // stack���� �̾ȳ� ���ڰ� "("�� �ƴϸ�, tmp�� �����ش�. (���ڰ� ����.)
                    tmp = character + tmp;
                }
            }

            // ")"�� ������ ������ char �� String���� �ٲ㼭 stack �� push
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