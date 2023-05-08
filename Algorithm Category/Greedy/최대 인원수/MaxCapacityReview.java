import java.util.*;

class MaxCapacityReview {
	public int solution(int n, int[][] trans, int[][] bookings){
		int answer=0;	

        // ���� ���� ������ �ϳ��� ������ �����ϰ��� �ϰ�, �̶� ������ ������ �ִ�� Ż �� �ִ� ���� �ο����� ����ؾ� �Ѵ�.
        // �켱 ������ ������ �ִ�� �����ؼ� ���������� ���������� ���ٰ� �����ϰ�
        // �������� �ִ� ���� �ο����� �ް�, ���������� �ִ�� ������ �ο��� �����Ѵ�.
        int[] sum = new int[n+1];
        for(int[] tran : trans) {
            sum[tran[0]] += tran[2];
            sum[tran[1]] -= tran[2];
        }		

        // ������ �������� ǥ���ߴ� �ִ� �����ο����� �ϳ��� ������ �Ǵ��ϱ� ���� �տ������� ��� ���Ѵ�.
        // �̷��� ������ ������ �ִ� �����ο����� ���ϸ� ������ ������ �ִ�� Ż �� �ִ� ���� �ο��� ��������.
        for(int i = 1; i <= n; i++) {
            sum[i] += sum[i-1];
        }

        // ������ ����� �ȿ���, �������� �������� �������� ������ �ؾ��Ѵ�.
        Arrays.sort(bookings, (a,b) -> a[0] - b[0]);

        // ���� ������ �ϳ��� ������ �����ϱ� ���ؼ� �ʿ��� �ڷᱸ��
        LinkedList<Integer> nums = new LinkedList<>();

        int bookingLength = bookings.length;
        int bookingIndex = 0;

        // ���� ��ȸ�Ѵ�.
        for(int i = 1 ; i <= n ; i++) {

            // �ڽ��� ������ ���� �����ϸ� ������.
            while(!nums.isEmpty() && nums.peek() == i) {
                answer++;
                nums.pollFirst();
            }

            // ���� ������ ����� ��� �����Ѵ�. ���������� �ִ���� �ο����� ������� �ʴ´�.
            while(bookingIndex < bookingLength && bookings[bookingIndex][0] == i) {
                // ������ ���� ���� �������� �־��ش�.
                nums.add(bookings[bookingIndex][1]);
                bookingIndex++;
            }

            // �������� �������� �������� ����
            Collections.sort(nums);

            while(nums.size() > sum[i]) {
                nums.pollLast();
            }
        }




		return answer;
	}

	public static void main(String[] args){
		MaxCapacityReview T = new MaxCapacityReview();
		System.out.println(T.solution(5, new int[][]{{1, 4, 2}, {2, 5, 1}}, new int[][]{{1, 2}, {1, 5}, {2, 3}, {2, 4}, {2, 5}, {2, 5}, {3, 5}, {3, 4}}));
		System.out.println(T.solution(5, new int[][]{{2, 3, 1}, {1, 5, 1}}, new int[][]{{2, 5}, {1, 5}, {1, 3}, {2, 4}, {2, 5}, {2, 3}}));
		System.out.println(T.solution(8, new int[][]{{1, 8, 3}, {3, 8, 1}}, new int[][]{{1, 3}, {5, 8}, {2, 7}, {3, 8}, {2, 7}, {2, 8}, {3, 8}, {6, 8}, {7, 8}, {5, 8}, {2, 5}, {2, 7}, {3, 7}, {3, 8}}));
		System.out.println(T.solution(9, new int[][]{{1, 8, 3}, {3, 9, 2}, {1, 5, 3}}, new int[][]{{1, 9}, {5, 8}, {2, 9}, {3, 8}, {2, 9}, {1, 9}, {8, 9}, {3, 9}, {1, 8}, {6, 8}, {7, 8}, {5, 8}, {3, 5}, {3, 7}, {4, 7}, {5, 8}}));
		System.out.println(T.solution(9, new int[][]{{2, 7, 2}, {3, 9, 2}, {1, 5, 3}}, new int[][]{{1, 9}, {4, 8}, {2, 9}, {5, 9}, {3, 8}, {2, 9}, {1, 9}, {8, 9}, {3, 9}, {1, 8}, {6, 8}, {3, 6}, {7, 8}, {5, 8}, {3, 5}, {2, 7}, {1, 7}, {2, 8}}));
	}
}