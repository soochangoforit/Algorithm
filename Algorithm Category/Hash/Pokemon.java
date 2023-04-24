import java.util.*;

class Pokemon {
    public int solution(int[] nums) {
        int answer = 0;
        int pickNums = nums.length / 2;		//������ Ƚ��
		
        //�ߺ� ����
        Set<Integer> set = new HashSet<Integer>();

        for(int num : nums) {
            set.add(num);
        }
		
        //set�� ũ�Ⱑ pickNums���� ũ�� pickNums���� ������ set�� size�� ����
        if(pickNums < set.size()) {
            answer = pickNums;
        }else {
            answer = set.size();
        }

        return answer;
    }
}