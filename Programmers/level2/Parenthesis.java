package Programmers.level2;

import java.util.*;

class Solution {
    public String solution(String p) {
        String answer = "";
        answer = algorithm(p);
        return answer;
    }
    
    public String algorithm(String input) {
        // 1. input�� 2���� ���ڿ��� �и��ؾ� �Ѵ�. �պκ��� ���� ���� ���ڿ��̿��� �Ѵ�. 
        if (input.isEmpty() || isTotallyRight(input)) {
            return input;
        }
        
        int splitIndex = getSplitIndex(input);
        
        // needToBeSplit �������� ������ u, ������ v
        String u = input.substring(0, splitIndex);
        String v = input.substring(splitIndex);
        
        // 2. u ���ڿ��� �ùٸ� ��ȣ ���ڿ��� ���
        if (isTotallyRight(u)){
            return u + algorithm(v);
        }
        else {
             // 2. u ���ڿ��� �ùٸ� ��ȣ ���ڿ��� �ƴ϶�� �Ǵ��ϰ� ����
            // 2-1. v�� "(" ����
            StringBuffer vBuf = new StringBuffer();
            vBuf.append("(");
            vBuf.append(algorithm(v));
            vBuf.append(")");
            // 2-2. u�� ù��°�� �������� ������ �κп� ���� () ������
            u = u.substring(1, u.length() - 1);
            for (char ch : u.toCharArray()) {
                if (ch == '(') {
                    vBuf.append(')');
                } else {
                    vBuf.append('(');
                }
            }
            return vBuf.toString();
        }
        
        
    }
    
    private int getSplitIndex(String input) {
        int left = 0;
        int right = 0;
        int needToBeSplit = 0;
        for(int i = 0; i < input.length(); i++) {
            
            if(input.charAt(i) == ')') {
                left++;
            }
            else{
                right++;
            }
            
            if (left != 0 && right != 0 && left == right) {
                needToBeSplit = i;
                break;
            }
        }
        return needToBeSplit + 1;
    }
    
    // �ùٸ� ��ȣ ���ڿ����� Ȯ���Ѵ�. u�� ������ ���ڿ��� (, ) �� ������ �����ϴ�.
    private boolean isTotallyRight(String u) {
        int balance = 0;
        
        for(char c : u.toCharArray()) {
            if (c == '(') {
                balance++;
            }
            else {
                balance--;
            }
            
            if (balance < 0) {
                return false;
            }
        }
        
        return balance == 0;
    }
    
    
}