import java.util.*;

class Solution {
    public int solution(String[][] book_time) {

        // ���� �ð� �������� �������� ����
        Arrays.sort(book_time, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                int startTime1 = timeToMinutes(o1[0]);
                int startTime2 = timeToMinutes(o2[0]);
                return startTime1 - startTime2;
            }
        });

        // ȣ�� ������ �̿� ���� �ð��� �����ϴ� �켱���� ť (���� ����)
        PriorityQueue<Integer> endTimeList = new PriorityQueue<>();

        int roomCount = 0;

        // ������ ��� ��ȸ (�̹� �������� ���� ��)
        for(String[] timeTable : book_time) {
            // ���� ������ ���ٸ�, ���ο� ������ �߰�
            if (endTimeList.isEmpty()) {
                roomCount++;
                endTimeList.add(timeToMinutes(timeTable[1]));
            } 
            // ������ ȣ���� �̿����̶��
            else {
                // ���Ӱ� �����ϰ��� �ϴ� ȣ�� �̿� ���� �ð�
                int startTime = timeToMinutes(timeTable[0]);

                // ���� ���� �� ���� ���� ������ �ð�
                int earliestEndTime = endTimeList.peek();

                // ���� ���� ���� �� ���� ���� ������ �ð����� 10�� �ڿ� �����Ѵٸ�
                if (startTime - earliestEndTime >= 10) {
                    // ���� ������ �ð��� �̾Ƴ��� ���ο� ������ �߰�
                    endTimeList.poll();
                } else {
                    // �ٸ� ������ �̿��ؾ� �ϱ⿡ ������ �߰�
                    roomCount++;
                }
                endTimeList.add(timeToMinutes(timeTable[1]));
            }
        }

        return roomCount;
    }

    // �ð��� ������ ��ȯ
    private int timeToMinutes(String timeStr) {
        String[] timeParts = timeStr.split(":");
        return Integer.parseInt(timeParts[0]) * 60 + Integer.parseInt(timeParts[1]);
    }
}
