import java.util.*;

class IpAddress {
    int len;
    List<String> temp;
    List<String> result;
    String[] answer;

	public String[] solution(String s){
	
        len = s.length();
        temp = new ArrayList<>();
        result = new ArrayList<>();


        for(int i = 0; i < 3; i++) {
            String num = s.substring(0, i+1);
            temp.add(num);
            DFS(i+1, s);
            temp.remove(temp.size()-1);
        }

        answer = result.toArray(new String[0]);
		
		return answer;
	}

    public void DFS(int start, String input) {
        if(temp.size() == 4) {
        
            if(start == len) {
                result.add(String.join(".", temp));
            }

        }

        else {
            // 0,1,2 순회 예정
            

                for(int j = 1 ; j <= 3; j++) {

                    if(start+j > len) {
                        break;
                    }
                    String num = input.substring(start, start+j);

                    // 0으로 시작하는 2자리 이상 숫자 제외
                    if((num.startsWith("0") && num.length() > 1) || (j == 3 && Integer.parseInt(num) > 255)) {
                        continue;
                    }

                    
                    temp.add(num);
                    DFS(start+j, input);
                    temp.remove(temp.size()-1);
                }

            
        }
    }

	public static void main(String[] args){
		IpAddress T = new IpAddress();
		System.out.println(Arrays.toString(T.solution("2025505")));	
		System.out.println(Arrays.toString(T.solution("0000")));
		System.out.println(Arrays.toString(T.solution("255003")));
		System.out.println(Arrays.toString(T.solution("155032012")));
		System.out.println(Arrays.toString(T.solution("02325123")));
		System.out.println(Arrays.toString(T.solution("121431211")));
	}
}