import java.util.*;

class AlphaCode {
    int answer;
    int length;

	public int solution(String s){
		answer = 0;

        length = s.length();

        DFS(0,s);

		return answer;
	}

    public void DFS(int start, String input) {
        if(start == length) {
            answer++;
            return;
        }
        else{
            for(int i =1; i<=2; i++) {

                if(start + i > length) break;

                String temp = input.substring(start, start + i);

                if(temp.startsWith("0") || Integer.parseInt(temp) > 26) break;

                DFS(start + i, input);
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