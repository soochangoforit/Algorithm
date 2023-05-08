import java.util.*;

class MaxCapacityReview {
	public int solution(int n, int[][] trans, int[][] bookings){
		int answer=0;	

        // 여러 대의 기차를 하나의 기차로 관리하고자 하고, 이때 각각의 역마다 최대로 탈 수 있는 수용 인원수를 기록해야 한다.
        // 우선 각각의 기차가 최대로 수용해서 승차역에서 하차역까지 갔다고 가정하고
        // 승차역에 최대 수용 인원수를 받고, 하차역에서 최대로 수용한 인원을 빼야한다.
        int[] sum = new int[n+1];
        for(int[] tran : trans) {
            sum[tran[0]] += tran[2];
            sum[tran[1]] -= tran[2];
        }		

        // 각각의 기차별로 표시했던 최대 수용인원수를 하나의 기차로 판단하기 위해 앞에서부터 모두 더한다.
        // 이렇게 앞전의 역과의 최대 수용인원수를 더하면 각각의 역마다 최대로 탈 수 있는 수용 인원이 정해진다.
        for(int i = 1; i <= n; i++) {
            sum[i] += sum[i-1];
        }

        // 예약한 사람들 안에서, 승차역을 기준으로 오름차순 정렬을 해야한다.
        Arrays.sort(bookings, (a,b) -> a[0] - b[0]);

        // 여러 기차를 하나의 기차로 관리하기 위해서 필요한 자료구조
        LinkedList<Integer> nums = new LinkedList<>();

        int bookingLength = bookings.length;
        int bookingIndex = 0;

        // 역을 순회한다.
        for(int i = 1 ; i <= n ; i++) {

            // 자신이 하차할 역에 도착하면 내린다.
            while(!nums.isEmpty() && nums.peek() == i) {
                answer++;
                nums.pollFirst();
            }

            // 역에 승차할 사람은 모드 승차한다. 아직까지는 최대수용 인원수를 고려하지 않는다.
            while(bookingIndex < bookingLength && bookings[bookingIndex][0] == i) {
                // 기차에 넣을 때는 하차역을 넣어준다.
                nums.add(bookings[bookingIndex][1]);
                bookingIndex++;
            }

            // 하차역을 기점으로 오름차순 정렬
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