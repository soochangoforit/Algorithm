import java.util.*;

class TileJumpReview {

	public int solution(int[] nums){
		int answer = 0;

        int n = nums.length;

        // 이미 방문을 했는지 확인하기 위한 배열
        int[] ch = new int[n];

        Queue<Integer> Q = new LinkedList<>();
        Q.offer(0);
        ch[0] = 1;
        int L = 0;

        while(!Q.isEmpty()){
            // 현재 위치에서 이동 가능한 타일(자식 노드) 수 만큼 꺼낼 수 있다.
            int size = Q.size();
            for(int i = 0; i < size; i++) {
                // 현재 타일에서 이동 가능한 타일(자식 노드)를 하나씩 꺼낸다.
                int x = Q.poll();
                
                // 자식 노드가 이동할 수 있는 범위 값 설정
                for(int j = 1; j <= nums[x]; j++) {
                    // for문 안에서 x는 자식 노드이면서, 현재 노드로 생각한다.
                    // 현재 자식 노드에서 이동할 수 있는 범위 값 j
                    // 이동한다 가정 했을 때의 위치 : nx
                    int nx = x + j;

                    // 다음 노드의 위치 nx(index 단위)가 마지막 인덱스(전체 사이즈 - 1)일 때 한번 더 while을 돌지 않기 위해 이른 return (원래는 마지막 while 까지 다 돌고 L++ 하지만)
                    // 지금은 이른 return을 했기에 L + 1 을 한다.
                    if(nx == n - 1) return L + 1;

                    // 다음으로 이동할 수 있는 nx (index 단위) 가 전체 인덱스 범위를 벗어나지 않고, 방문하지 않은 경우에서만 방문
                    if(nx < n && ch[nx] == 0) {
                        // 방문 처리 및, 자식 노드로 입성
                        ch[nx] = 1;
                        Q.offer(nx);
                    }
                }
            }

            // 자식 노드에 대해 모두 거쳐갔기에
            L++;
        }


		// 만약 while을 다 돌고 나서도 while문 안에서 L + 1로 return 하지 못 하면, 방문하지 못 하기에 -1를 return 한다.
		return -1;
	}
		
	public static void main(String[] args){
		TileJumpReview T = new TileJumpReview();
		System.out.println(T.solution(new int[]{2, 2, 1, 2, 1, 1}));
		System.out.println(T.solution(new int[]{1, 0, 1, 1, 3, 1, 2, 1}));
		System.out.println(T.solution(new int[]{2, 3, 1, 0, 1, 1, 2, 3, 1, 5, 1, 3, 1}));
		System.out.println(T.solution(new int[]{1, 2, 1, 2, 1, 2, 1, 1, 3, 1, 2, 1}));
		System.out.println(T.solution(new int[]{1, 3, 2, 1, 1, 2, 3, 1, 3, 1, 2, 3, 5, 1, 5, 1, 2, 1, 1}));
	}
}