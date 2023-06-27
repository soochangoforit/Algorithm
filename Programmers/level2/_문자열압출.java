class Solution {
    public int solution(String s) {
        
        // �ʱ� �亯�� ���ڿ��� ���̷� �����մϴ�.
        int answer = s.length();
        
        // ��� ������ ���� ũ�⸦ ����մϴ� (���ڿ��� ���� ���̷�).
				// ���� 5���ڶ�� 2�ڸ������� �������� ����ϸ� �ȴ�. ���� 3�ڸ� ���ʹ� �ǹ� ����.
        for(int pattern_size = 1; pattern_size <= s.length() / 2; pattern_size++) {
            String temp_answer_string = "";
            
            // ���� ������ ���̸�ŭ �߶� ǥ�� ������ ã���ϴ�.
            String cur_pattern = s.substring(0, pattern_size);
            int count = 1;
            
            // ���� ���� ���ڿ��� �����Ͽ� ���մϴ�.
            for(int k = pattern_size; k < s.length(); k += pattern_size) {
                String sub;
                
                // �߶�� �� ���� �ε����� s�� ������ ��ġ���� ���̺��� �۰ų� ������ ������ �ڸ� �� �ֽ��ϴ�.
                if (k + pattern_size <= s.length()) {
                    sub = s.substring(k, k + pattern_size);
                } 
                // �߶�� �� �ε����� ������ ��ġ�� s�� ���̺��� ũ�� ������ ���ڿ������� �켱������ �߶���ϴ�.
                else {
                    sub = s.substring(k);
                }
                
                // ���� ǥ�� ���ϰ� ��ġ�Ѵٸ� ī��Ʈ�� ������ŵ�ϴ�.
                if (cur_pattern.equals(sub)) {
                    count++;
                } else {
                    // ǥ�� ���ϰ� ��ġ���� ������, ���� ���� ī��Ʈ�� ���� ���ڿ��� �����Ͽ� temp_answer�� �־�� �մϴ�.
                    // ��ġ���� �ʴ� sub�� ���ο� ������ �Ǿ�� �ϹǷ� ī��Ʈ�� 1�� �ٽ� �ʱ�ȭ�˴ϴ�.
                    temp_answer_string += (count > 1) ? count + cur_pattern : cur_pattern;
                    cur_pattern = sub;
                    count = 1;
                }
            }

            // ������ ������ ��ġ�ϰ� ī��Ʈ ���� �����ϰ� temp_anwer_String�� ���� ���� �� ���ų�
            // ���Ӱ� ������ �����g���� �� �̻� ���� ���ڿ��� ���� count�� 1�� ������ ���¿��� temp�� �־��ش�.
            temp_answer_string += (count > 1) ? count + cur_pattern : cur_pattern;

            // �亯�� ���� �亯�� ���� ���ڿ��� ���� �� �� ���� ������ ������Ʈ�մϴ�.
            answer = Math.min(answer, temp_answer_string.length());
        }
        
        // ���� �亯�� ��ȯ�մϴ�.
        return answer;
    }
}