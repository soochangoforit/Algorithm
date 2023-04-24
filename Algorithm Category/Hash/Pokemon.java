import java.util.*;

class Pokemon {
    public int solution(int[] nums) {
        int answer = 0;
        int pickNums = nums.length / 2;		//선택할 횟수
		
        //중복 제거
        Set<Integer> set = new HashSet<Integer>();

        for(int num : nums) {
            set.add(num);
        }
		
        //set의 크기가 pickNums보다 크면 pickNums리턴 작으면 set의 size를 리턴
        if(pickNums < set.size()) {
            answer = pickNums;
        }else {
            answer = set.size();
        }

        return answer;
    }
}