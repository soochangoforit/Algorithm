import java.util.*;


class IpAddressReview {
	LinkedList<String> tempList;
    ArrayList<String> resultList;
    int length;

    public String[] solution(String s){

        length = s.length();


        tempList = new LinkedList<>();
        resultList = new ArrayList<>();

        DFS(0,s);

        String[] answer = new String[resultList.size()];
        for(int i = 0; i < resultList.size() ; i++) {
            answer[i] = resultList.get(i);
        }
		
		return answer;
	}


    public void DFS(int start, String input) {
        if(tempList.size() == 4) {
            if (start == length) {
                String result = String.join(".", tempList);
                resultList.add(result);
            }

        }
        else {
            for(int i = 1; i <=3 ; i++){
                if (start + i > length) break;
                String temp = input.substring(start, start + i);

                if((temp.startsWith("0") && temp.length() >= 2) || Integer.parseInt(temp) > 255) {
                    break;
                }

                tempList.add(temp);

                DFS(start +i, input);

                tempList.pollLast();
            }
        }
    }



	public static void main(String[] args){
		IpAddressReview T = new IpAddressReview();
		System.out.println(Arrays.toString(T.solution("2025505")));	
		System.out.println(Arrays.toString(T.solution("0000")));
		System.out.println(Arrays.toString(T.solution("255003")));
		System.out.println(Arrays.toString(T.solution("155032012")));
		System.out.println(Arrays.toString(T.solution("02325123")));
		System.out.println(Arrays.toString(T.solution("121431211")));
	}
}