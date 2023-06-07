
import java.util.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int maxDuration = 0;
        
        // ã���� �ϴ� ������ #�� ���� ���� �ҹ��ڷ� ġȯ
        m = m.replace("C#", "c").replace("D#", "d").replace("F#", "f").replace("G#", "g").replace("A#", "a");
        
        for(String musicinfo : musicinfos){
            String[] info = musicinfo.split(",");
            String[] start = info[0].split(":");
            String[] end = info[1].split(":");
            
            // �� ��� �ð� (�� ����)
            int duration = (Integer.parseInt(end[0]) - Integer.parseInt(start[0])) * 60 + Integer.parseInt(end[1]) - Integer.parseInt(start[1]);
            
            // if �������� m�� ����� ��ü ���� ������ maxDuration�� ������ 0���� �ʱ�ȭ �Ǿ��ִ�.
            // ����, if �� �ȿ��� m�� ã�Ҵٸ� ����� �ð��� �� ������ �������� ǥ��ȴ�.
            // ���� �ռ� ����� ���̿� ���� ������ ������ �ְ� ���������� m�� ã�Ƽ� ������ �ĺ��� �Ǵ��� 
            // ���������δ� �� ó������ ã�� ������ ������ �Ǵ� �䱸���� �̱⿡
            // �ռ� 0 �� �ƴ� duration�� �����Ѵٸ� , ���� ������ �� �ִ� ������ ������ duration�� maxDuration���� Ŭ ���̴�.
            // duration�� ������ ����, ���������� m�� ã�Ҵ� �ϴ��� ���� ã�� ������ ������ �Ǿ�� �ϱ⿡ ������ ū ��쿡�� ������ �����Ѵ�.
            if(duration > maxDuration) {
                 // �Է����� ���� ���� #�� ���� ���� �ҹ��ڷ� ġȯ (melody�� �������� ����� ���� �ǹ�)
                String melody = info[3].replace("C#", "c").replace("D#", "d").replace("F#", "f").replace("G#", "g").replace("A#", "a");
            
                // �� ����� �ð��� ����, ��ü ����� ���� ����
                StringBuilder play = new StringBuilder();
                for(int i = 0; i < duration; i++){
                    play.append(melody.charAt(i % melody.length()));
                }
            
                // ã���� �ϴ� m �� ��ü ����� ���� ���ԵǾ� �ִٸ�, maxDuration�� �����ϰ�, answer�� ����
                if(play.toString().contains(m)){
                    maxDuration = duration;
                    answer = info[2];
                }

                // ã���� �ϴ� m�� ���ٸ� �⺻�� �״�� "(None)"�� �ȴ�.
            }
        }
        return answer;
    }
}