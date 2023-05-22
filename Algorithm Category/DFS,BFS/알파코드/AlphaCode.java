import java.util.*;

class AlphaCode {
    int answer;

	public int solution(String s){
		answer = 0;

        DFS(0,s);



		return answer;
	}

    public void DFS(int start, String input) {
        if(start == input.length()) {
            answer++;
            return;
        }
        else {
            for(int i = 1 ; i <=2; i++) {

                // substring �ϱ� ���� ������ ����� break�� ���� �ش� level�� ����� ���� Ż�� ��, �������� ����
                if(start + i > input.length()) break;

                // substring�� ���� �ش� level�� ����� ���� ����
                String temp = input.substring(start, start+i);

                // �ش� level�� ����� ���� 26���� ũ�� �������� ����
                if(Integer.parseInt(temp) > 26) continue;

                // �ش� level�� ����� ���� 0�̸� �������� ����
                if(temp.charAt(0) == '0') continue;

                // �ش� level�� ����� ���� 26���� �۰�, 0�� �ƴϸ� ���� level�� ����
                DFS(start+i, input);


        

            }
        }

    }
		
	public static void main(String[] args){
		AlphaCode T = new AlphaCode();
		System.out.println(T.solution("25114"));
		System.out.println(T.solution("23251232"));
		System.out.println(T.solution("21020132"));
		System.out.println(T.solution("21350"));
		System.out.println(T.solution("120225"));
		System.out.println(T.solution("232012521"));
	}
}