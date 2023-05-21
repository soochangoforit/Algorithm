import java.util.*;

class IpAddressSec {
    int len;
    LinkedList<String> temp;
    ArrayList<String> result;

    public String[] solution(String s) {
        len = s.length();
        temp = new LinkedList<>();
        result = new ArrayList<>();

        DFS(0, s);

        return result.toArray(new String[0]);
    }

    public void DFS(int start, String input) {

        if(temp.size() == 4) {
            // 0~255 사이, 0으로 시작하지 않아서 문자열 input에 대해 전부 순회하지 않아도 size는 4로 맞춰질 수 있다.
            // 다만 input을 다 순회했다는 의미로 start는 len을 만족해야지 result에 추가할 수 있다.
            if(start == len) {
                result.add(String.join(".", temp));
            }

        } 
        
        else {
            // 2, 20, 202 이렇게 처음에는 3번 움직이고자 한다.
            for(int i = 1; i <= 3; i++) {

                // subString의 indexOutOFBound 방지
                // if문 조건 안에서는 start + i가 len과 같아질 수는 있지만, start + i가 len보다 커질 수는 없다.
                if(start + i > len) {
                    break;
                }
                String segment = input.substring(start, start + i);

                // 0으로 시작하는 2자리 이상 숫자 제외 || 3자리 숫자로 이루어지고 255 초과 숫자 제외
                //유효하지 않은 경우(예: 255보다 크거나 0으로 시작하는 여러 자리 숫자) 동일한 위치에서 시작하는 더 긴 세그먼트를 확인할 이유가 없다. (break 적절)
                // 다음으로 더 긴 세그먼트도 유효하지 않다는 것을 이미 알고 있기 때문에 continue는 계산 낭비입니다.
                if((segment.startsWith("0") && segment.length() > 1) || (Integer.parseInt(segment) > 255)) {
                    // continue 라는 의미는 현재 level에서 다음 경우의 수를 생각하는 의미이고
                    // continue로 인해서 for문의 마지막에 도달한 경우, 이전 level에서 다음 경우로 넘어간다.
                    break;
                }

                temp.add(segment);
                DFS(start + i, input);
                temp.pollLast();
            }
        }
    }


	public static void main(String[] args){
		IpAddressSec T = new IpAddressSec();
		System.out.println(Arrays.toString(T.solution("2025505")));	
		System.out.println(Arrays.toString(T.solution("0000")));
		System.out.println(Arrays.toString(T.solution("255003")));
		System.out.println(Arrays.toString(T.solution("155032012")));
		System.out.println(Arrays.toString(T.solution("02325123")));
		System.out.println(Arrays.toString(T.solution("121431211")));
	}
}