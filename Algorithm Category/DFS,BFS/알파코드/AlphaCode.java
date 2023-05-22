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

                // substring 하기 위한 범위를 벗어나면 break를 통해 해당 level의 경우의 수를 탈출 후, 다음으로 진행
                if(start + i > input.length()) break;

                // substring을 통해 해당 level의 경우의 수를 구함
                String temp = input.substring(start, start+i);

                // 해당 level의 경우의 수가 26보다 크면 다음으로 진행
                if(Integer.parseInt(temp) > 26) continue;

                // 해당 level의 경우의 수가 0이면 다음으로 진행
                if(temp.charAt(0) == '0') continue;

                // 해당 level의 경우의 수가 26보다 작고, 0이 아니면 다음 level로 진행
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