import java.util.*;

class MaxCapacity {
	public int solution(int n, int[][] trans, int[][] bookings){
		int answer=0;	
		// n은 역의 개수 (역은 1번부터 시작)

        // sum 은 i번 역에서 태울 수 있는 최대 인원 수
        // 여러 기차가 있더라도, 1개의 기차처럼 생각할 수 있다.
        int[] sum = new int[n+1];
        for(int[] x : trans) {
            // 승차역에 최대 수용인원 표시
            sum[x[0]] += x[2];
            // 하차역에 최대로 내릴 인원 표시
            sum[x[1]] -= x[2];
        }

        // 각기 다른 sum을 가지는 기차를 하나의 기차로 판단하고자 한다.
        // 다 합쳐진 sum은 각각의 역에서 최대로 수용 가능한 인원이 된다.
        for(int i = 1; i <=  n; i++) {
            sum[i] += sum[i - 1];
        }

        int bookingLength = bookings.length;
        
        // 승차역을 기준으로 오름차순 정렬
        Arrays.sort(bookings, (a,b) -> a[0] - b[0]);

        // nums는 여러개의 기차를 하나의 기차처럼 생각하면 된다.
        // nums에 값이 들어왔다는 의미는 기차를 탔다는 거고
        // nums에 값이 나갔다는 의미는 기차에 내렸다는 의미이다.
        LinkedList<Integer> nums = new LinkedList<>();
        
        int bookingIndex = 0;

        // i는 역번호이다. 1번 역부터 ~ N번 역까지
        for(int i = 1; i <= n; i++) {
            
            // i번 역에서 내릴 수 있는지 확인 (가차에 사람이 있고, 하차하고자 하는 사람이 i번 역과 동일하다면)
            while(!nums.isEmpty() && nums.peek() == i){
                // 내렸다는 의미는 기차를 타고 내렸기에 기차를 이용할 수 있었다는 의미이다.
                answer++;
                nums.pollFirst();
            }

            // i번 역에서 승차하겠다 하는 사람을 다 넣어준다.
            // 예약에서 승차역이 지금 i 역번호와 같은 경우는 다 태운다. (일단 태운다)
            while(bookingIndex < bookingLength && bookings[bookingIndex][0] == i){
                // 기차에 태울때는 하차역을 넣는다.
                nums.add(bookings[bookingIndex][1]);
                bookingIndex++;
            }

            // i 역에 태울 수 있는 사람을 다 태우고, 하차역을 기점으로 오름차순 정렬을 수행한다.
            // 뒤에 나오는 while에서 멀리 가는 사람은 기차 이용에 효율적이지 못 하기에 내리라고 말하기 위해서
            // 빨리 내리는 어린이가 제일 기차를 최대로 효울적으로 이용할 수 있다. 그 어린이들만 태우고자 한다.
            // 해당 부분이 그리디 영역에 속한다. (빨리 내리는 어린이가 기차의 효율을 높이는데 좋다.)
            Collections.sort(nums);

            // i번 역에서 i+1 역까지 가는데 태울 수 있는 최대 인원수를 초과하면 가장 늦게 내리는 사람을 내리라고 해야한다.
            // 가장 늦게 내리면, 그 만큼 태울 수 있는 사람도 없어 기차를 최대로 활용할 수 없다.
            while(nums.size() > sum[i]){
                nums.pollLast();
            }


        }





		return answer;
	}

	public static void main(String[] args){
		MaxCapacity T = new MaxCapacity();
		System.out.println(T.solution(5, new int[][]{{1, 4, 2}, {2, 5, 1}}, new int[][]{{1, 2}, {1, 5}, {2, 3}, {2, 4}, {2, 5}, {2, 5}, {3, 5}, {3, 4}}));
		System.out.println(T.solution(5, new int[][]{{2, 3, 1}, {1, 5, 1}}, new int[][]{{2, 5}, {1, 5}, {1, 3}, {2, 4}, {2, 5}, {2, 3}}));
		System.out.println(T.solution(8, new int[][]{{1, 8, 3}, {3, 8, 1}}, new int[][]{{1, 3}, {5, 8}, {2, 7}, {3, 8}, {2, 7}, {2, 8}, {3, 8}, {6, 8}, {7, 8}, {5, 8}, {2, 5}, {2, 7}, {3, 7}, {3, 8}}));
		System.out.println(T.solution(9, new int[][]{{1, 8, 3}, {3, 9, 2}, {1, 5, 3}}, new int[][]{{1, 9}, {5, 8}, {2, 9}, {3, 8}, {2, 9}, {1, 9}, {8, 9}, {3, 9}, {1, 8}, {6, 8}, {7, 8}, {5, 8}, {3, 5}, {3, 7}, {4, 7}, {5, 8}}));
		System.out.println(T.solution(9, new int[][]{{2, 7, 2}, {3, 9, 2}, {1, 5, 3}}, new int[][]{{1, 9}, {4, 8}, {2, 9}, {5, 9}, {3, 8}, {2, 9}, {1, 9}, {8, 9}, {3, 9}, {1, 8}, {6, 8}, {3, 6}, {7, 8}, {5, 8}, {3, 5}, {2, 7}, {1, 7}, {2, 8}}));
	}
}